package vlille.strategy;

import java.util.List;

import vlille.RentalStation;
import vlille.Vehicle;

/**
 * Interface DistributionStrategy
 */
public interface DistributionStrategy {
    /** Distribute vehicles in stations
     * @param stations : stations to distribute
     * @param vehicles : vehicles to distribute
     */
    void distribute(List<RentalStation> stations, List<Vehicle> vehicles);
}