package vlille.strategy;

import java.util.List;
import java.util.Random;

import vlille.RentalStation;
import vlille.Vehicle;

/**
 * Class RandomDistribution
 */
public class RandomDistribution implements DistributionStrategy {

    /** Random generator */
    private Random random = new Random();

    /**
     * Default constructor
     */
    public RandomDistribution() {
        // Aucune initialisation n'est effectuée, necessaire pour corriger le warning dans génération de docs.
    }

    /**
     * Distribute vehicles randomly in stations
     * @param stations : list of stations
     * @param vehicles : list of vehicles
     */
    @Override
    public void distribute(List<RentalStation> stations, List<Vehicle> vehicles) {
        for (Vehicle vehicle : vehicles) {
            RentalStation selectedStation = stations.get(random.nextInt(stations.size()));
            selectedStation.addVehicle(vehicle);
        }
    }
}
