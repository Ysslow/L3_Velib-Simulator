package vlille;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import vlille.observer.Observer;
import vlille.observer.Subject;
import vlille.strategy.DistributionStrategy;
import vlille.visitor.RepairerVisitor;

/**
 * Class ControlCenter
 */
public class ControlCenter implements Subject {

    /** Attributes */
    private boolean isStationNotifying = false;
    private List<Observer> observers = new ArrayList<>();
    private static ControlCenter instance;
    private DistributionStrategy distributionStrategy;
    private List<Vehicle> circulatingVehicles = new ArrayList<>();
    private final int BROKENCONST = 5;
    private final int STOLENCONST = 2;

    /** constructor of the control center
     * @param distributionStrategy : distribution strategy
     */
    private ControlCenter(DistributionStrategy distributionStrategy) {
        this.distributionStrategy = distributionStrategy;
    }

    /** get the instance of the control center
     * @param distributionStrategy : distribution strategy
     * @return the instance of the control center
     */
    public static ControlCenter getInstance(DistributionStrategy distributionStrategy) {
        if (instance == null) {
            instance = new ControlCenter(distributionStrategy);
        }
        return instance;
    }

    /** register an observer
     * @param observer : observer to register
     */
    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    /** remove an observer
     * @param observer : observer to remove
     */
    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    /** distribute the vehicles in the stations
     * @param stations : list of stations
     * @param vehicles : list of vehicles
     */
    public void distributeVehicle(List<RentalStation> stations, List<Vehicle> vehicles) {
        for (RentalStation station : stations) {
            if (!station.isEmpty() || !station.isFull()) {
                List<Vehicle> vehiclesToRemove = new ArrayList<>();

                for (Vehicle vehicle : station.getVehicles()) {
                    vehiclesToRemove.add(vehicle);
                }

                for (Vehicle vehicle : vehiclesToRemove) {
                    station.removeVehicle(vehicle);
                }
            }
        }
        distributionStrategy.distribute(stations, vehicles);

        notifyObserversDeposit();
        notifyObserversWithdraw();
    }


    /** notify the observers of a deposit
     */
    public void notifyDeposit() {
        isStationNotifying = true;
        System.out.println("ControlCenter notified of a deposit.");
        notifyObserversDeposit();
        isStationNotifying = false;
    }

    /** notify the observers of a withdrawal
     */
    public void notifyWithdraw() {
        isStationNotifying = true;
        System.out.println("ControlCenter notified of a withdrawal.");
        notifyObserversWithdraw();
        isStationNotifying = false;
    }

    /** notify the observers of a deposit
     */
    @Override
    public void notifyObserversDeposit() {
        if (!isStationNotifying) {
            for (Observer observer : observers) {
                observer.notifyDeposit();
            }
        }
    }

    /** notify the observers of a withdrawal
     */
    @Override
    public void notifyObserversWithdraw() {
        if (!isStationNotifying) {
            for (Observer observer : observers) {
                observer.notifyWithdraw();
            }
        }
    }

    /** get the observers
     */
    public void getObservers() {
        for (Observer observer : observers) {
            System.out.println(observer);
        }
    }

    /** call the repairer if a bike is broken
     * @param stations : list of stations
     */
    public void callRepairer(List<RentalStation> stations) {
            for (RentalStation station : stations) {
                int brokenBikeIndex = station.hasBrokenBikes();
                if (brokenBikeIndex != -1 && !station.isEmpty()) {
                    Vehicle brokenBike = station.getVehicles().get(brokenBikeIndex);
                    System.out.println("\nBroken " + brokenBike.getId() + " at " + station.getId());
        
                    // Appel du réparateur pour le vélo cassé
                    station.acceptRepairer(new RepairerVisitor("Bob"), brokenBikeIndex);
                }
            }
        }

    /** simulate a random withdrawal
     * @param stations : list of stations
     * @param vehicles : list of vehicles
     */
    public void simulateRandomWithdrawal(List<RentalStation> stations, List<Vehicle> vehicles) {
        Random random = new Random();

        // Recherche des stations avec des vélos disponibles
        List<RentalStation> stationsWithAvailableBikes = new ArrayList<>();
        for (RentalStation station : stations) {
            if (!station.getAvailableVehicles().isEmpty()) {
                stationsWithAvailableBikes.add(station);
            }
        }

        if (!stationsWithAvailableBikes.isEmpty()) {
            // Sélectionne une station aléatoire avec des vélos disponibles
            RentalStation randomStation = stationsWithAvailableBikes.get(random.nextInt(stationsWithAvailableBikes.size()));

            // Vérifie que la station n'est pas vide
            if (!randomStation.isEmpty()) {
                // Sélectionne un vélo aléatoire dans la station
                List<Vehicle> availableBikes = randomStation.getAvailableVehicles();
                if (!availableBikes.isEmpty()) {
                    Vehicle randomBike = availableBikes.get(random.nextInt(availableBikes.size()));

                    randomBike.resetNumIntervalsAlone();
                    randomStation.removeVehicle(randomBike);
                    vehicles.remove(randomBike);
                    circulatingVehicles.add(randomBike);
                    randomBike.incrementNumRentals();
                    randomStation.notifyWithdraw();
                    System.out.println("Withdrawn " + randomBike.getId() + " from " + randomStation.getId());
                    return;
                }
            }
        }

        System.out.println("\nNo available bikes for withdrawal or all stations are empty.");
    }


    /** simulate a random deposit
     * @param stations : list of stations
     * @param vehicles : list of vehicles
     */
    public void simulateRandomDeposit(List<RentalStation> stations, List<Vehicle> vehicles) {
        Random random = new Random();

        // Recherche des stations avec des emplacements disponibles
        List<RentalStation> stationsWithAvailableSlots = new ArrayList<>();
        for (RentalStation station : stations) {
            if (station.hasAvailableSlots()) {
                stationsWithAvailableSlots.add(station);
            }
        }

        if (!circulatingVehicles.isEmpty() && !stationsWithAvailableSlots.isEmpty()) {
            // Sélectionne une station aléatoire avec des emplacements disponibles
            RentalStation randomStation = stationsWithAvailableSlots.get(random.nextInt(stationsWithAvailableSlots.size()));

            // Sélectionne un vélo en circulation aléatoire
            if (!circulatingVehicles.isEmpty()) {
                Vehicle randomBike = circulatingVehicles.get(random.nextInt(circulatingVehicles.size()));

                if (randomBike.getState() == VehicleState.GOOD) {
                    // Retire le vélo de la liste des vélos en circulation et l'ajoute à la station
                    circulatingVehicles.remove(randomBike);
                    vehicles.add(randomBike);
                    randomStation.addVehicle(randomBike);
                    if (randomBike.getNumRentals() >= BROKENCONST) {
                        randomBike.setState(VehicleState.BROKEN);
                    }
                    randomStation.notifyDeposit();
                    System.out.println("Deposited " + randomBike.getId() + " to " + randomStation.getId());

                    // Ne pas ajouter le vélo à la liste des vélos en circulation après le dépôt
                } else {
                    System.out.println("\nSelected bike is not in good condition. Skipping deposit.");
                }
            }
        } else {
            System.out.println("\nNo available slots for deposit or no circulating bikes.");
        }
    }


    
    /** detect if a bike can be stolen and stole it if it is the case
     * @param stations : list of stations
     * @param vehicles : list of vehicles
     */
    public void detectStolenBike(List<RentalStation> stations, List<Vehicle> vehicles) {
        for (RentalStation station : stations) {
            List<Vehicle> availableBikes = station.getAvailableVehicles();
    
            if (availableBikes.size() == 1) {
                Vehicle lonelyBike = availableBikes.get(0);
                if (lonelyBike.getNumIntervalsAlone() >= STOLENCONST && lonelyBike.getState() != VehicleState.BROKEN) {
                    // Le vélo est resté seul pendant 2 intervalles, déclarons-le comme volé
                    station.removeVehicle(lonelyBike);
                    vehicles.remove(lonelyBike);
                    System.out.println("\n" + lonelyBike.getId() + " has been stolen from " + station.getId() +
                            " after being alone for 2 intervals.");
                } else {
                    // Incrémenter le nombre d'intervalles où le vélo est resté seul
                    lonelyBike.incrementNumIntervalsAlone();
                }
            } else {
                // Réinitialiser le nombre d'intervalles si le vélo n'est plus seul
                for (Vehicle bike : availableBikes) {
                    bike.resetNumIntervalsAlone();
                }
            }
        }
    }

    /** show the current distribution of the vehicles
     * @param stations : list of stations
     */
    public void showCurrentDistribution(List<RentalStation> stations) {
        System.out.println("\nCurrent distribution:");
        for (RentalStation station : stations) {
            System.out.println(station.getId() + ": " + station.getVehicles().size() + " bikes out of " + station.getStorage() + " slots");
            for (Vehicle vehicle : station.getVehicles()) {
                System.out.println("    " + vehicle.getId() + " (" + vehicle.getState() + ")" + " " + vehicle.addOption());
            }
        }
        System.out.println();
    }

    /** check if a station is empty or full and redistribute the vehicles if it is the case
     * @param stations : list of stations
     * @param vehicles : list of vehicles
     */
    public void checkAndRedistribute(List<RentalStation> stations, List<Vehicle> vehicles) {
        for (RentalStation station : stations) {
            if (station.isEmpty() || station.isFull()) {
                this.distributeVehicle(stations, vehicles);
                System.out.println("\nRedistributed vehicles.");
                System.out.println("Check the distribution before the new withdrawals and deposits:");
                this.showCurrentDistribution(stations);
            }
        }
    }

    /** get the circulating vehicles
     * @return the circulatingVehicles
     */
    public List<Vehicle> getCirculatingVehicles() {
        return circulatingVehicles;
    }
    
    /**
     * Get the count of registered observers.
     *
     * @return The count of registered observers.
     */
    public int getObserversCount() {
        return observers.size();
    }
}