package vlille;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import vlille.decorator.BaggageBuilder;
import vlille.decorator.LightBuilder;
import vlille.strategy.RandomDistribution;
import vlille.strategy.RoundRobinDistribution;

/**
 * Class Main
 */
public class Main {

    /**
     * Default constructor
     */
    public Main() {
        // Aucune initialisation n'est effectuée, necessaire pour corriger le warning dans génération de docs.
    }

    /**
     * Main method
     * @param args : arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ControlCenter controlCenter = null;

        System.out.print("Enter the number of stations: ");
        int numStations = scanner.nextInt();

        System.out.print("Enter the number of bikes: ");
        int numBikes = scanner.nextInt();

        System.out.print("Choose distribution mode {(1) Random, (2) Round Robin} : ");
        int distributionMode = scanner.nextInt();

        // Changer la stratégie de distribution en fonction du choix
        if (distributionMode == 1) {
            controlCenter = ControlCenter.getInstance(new RandomDistribution());
        } else if (distributionMode == 2) {
            controlCenter = ControlCenter.getInstance(new RoundRobinDistribution());
        } else {
            System.out.println("Invalid choice. Using the current distribution mode.");
        }

        scanner.nextLine();

        // Création de stations de location
        List<RentalStation> stations = createStations(numStations, controlCenter);
        // Création de vélos
        List<Vehicle> vehicles = createBikes(numBikes);

        // Enregistrement des stations de location auprès du centre de contrôle
        for (RentalStation station : stations) {
            controlCenter.registerObserver(station);
        }

        if (vehicles.isEmpty()) {
            System.out.println("You need at least 1 bike for the simulation.");
            System.exit(0);
        }


        int totalCapacity = 0;
        for (RentalStation station : stations) {
            totalCapacity += station.getStorage();
        }

        if (totalCapacity < vehicles.size()) {
            System.out.println("You need at least " + vehicles.size() + " slots between all the stations for the simulation, u currently have " + totalCapacity + " slots." );
            System.exit(0);
        }

        controlCenter.distributeVehicle(stations, vehicles);
        controlCenter.showCurrentDistribution(stations);

        // Boucle infinie pour simuler le passage du temps
        while (true) {
            System.out.println("Press enter to simulate the passage of one interval.");
            scanner.nextLine();

            // Redistribue les vélos si besoin
            controlCenter.checkAndRedistribute(stations, vehicles);

            // Génère un nombre aléatoire entre 0 et 3 pour les retraits
            int randomWithdrawals = new Random().nextInt(4);
            for (int i = 0; i < randomWithdrawals; i++) {
                controlCenter.simulateRandomWithdrawal(stations,vehicles);
            }

            // Génère un nombre aléatoire entre 0 et 3 pour les dépôts
            int randomDeposits = new Random().nextInt(4);
            for (int i = 0; i < randomDeposits; i++) {
                controlCenter.simulateRandomDeposit(stations,vehicles);
            }

            // Realise le vol de vélo si seul pendant 2 intervalles
            controlCenter.detectStolenBike(stations, vehicles);

            // Appelle le réparateur si besoin
            controlCenter.callRepairer(stations);

            // Affiche la distribution actuelle
            controlCenter.showCurrentDistribution(stations);

            // Arret si plus de vélo en circulation et en station
            if (vehicles.isEmpty() && controlCenter.getCirculatingVehicles().isEmpty()) {
                System.out.println("No more bikes in circulation or in stations, stopping simulation.");
                System.exit(0);
            }
        }
    }

    /**
     * Create bikes with random options
     * @param numBikes : number of bikes to create
     * @return a list of bikes
     */
    private static List<Vehicle> createBikes(int numBikes) {
        List<Vehicle> vehicles = new ArrayList<>();

        Random random = new Random();
        for (int j = 0; j < numBikes; j++) {
            Vehicle bike;
            if (random.nextBoolean()) {
                bike = new ClassicBikeBuilder().id("Bike" + j).build();
            } else {
                bike = new ElectricBikeBuilder().id("EBike" + j).build();
            }

                // Ajout aléatoire d'options au vélo
            int numOptions = random.nextInt(3); // Génère un nombre aléatoire entre 0 et 2
            for (int k = 0; k < numOptions; k++) {
                // Ajout d'options au vélo
                if (random.nextBoolean()) {
                    bike = new BaggageBuilder().vehicle(bike).build();
                } else {
                    bike = new LightBuilder().vehicle(bike).build();
                }
            }
            vehicles.add(bike);
        }
        return vehicles;
    }


    /**
     * Create stations with random storage capacity
     * @param numStations : number of stations to create
     * @return a list of stations
     */
    private static List<RentalStation> createStations(int numStations, ControlCenter controlCenter) {
        List<RentalStation> stations = new ArrayList<>();

        for (int i = 1; i <= numStations; i++) {
            int storageCapacity = generateRandomCapacity();
            RentalStation station = new RentalStation("Station " + i, storageCapacity, controlCenter);
            stations.add(station);
        }
        return stations;
    }

    /**
     * Generate a random capacity between 10 and 20
     * @return a random capacity
     */
    private static int generateRandomCapacity() {
        Random random = new Random();
        return random.nextInt(11) + 10; // Génère une capacité aléatoire entre 10 et 20
    }

}