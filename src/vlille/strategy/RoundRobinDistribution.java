package vlille.strategy;

import java.util.List;

import vlille.RentalStation;
import vlille.Vehicle;

/**
 * Class RoundRobinDistribution
 */
public class RoundRobinDistribution implements DistributionStrategy {

    /** Index of the current station */
    private int currentIndex = 0;

    /**
     * Default constructor
     */
    public RoundRobinDistribution() {
        // Aucune initialisation n'est effectuée, necessaire pour corriger le warning dans génération de docs.
    }

    /**
     * Distribute vehicles in stations with round robin algorithm
     * @param stations : list of stations
     * @param vehicles : list of vehicles
     */
    @Override
    public void distribute(List<RentalStation> stations, List<Vehicle> vehicles) {
        for (Vehicle vehicle : vehicles) {
            RentalStation selectedStation = stations.get(currentIndex);
            selectedStation.addVehicle(vehicle);
            currentIndex = (currentIndex + 1) % stations.size();
        }
    }
}
