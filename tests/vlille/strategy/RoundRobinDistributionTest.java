package vlille.strategy;

import org.junit.jupiter.api.Test;

import vlille.ClassicBike;
import vlille.RentalStation;
import vlille.Vehicle;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoundRobinDistributionTest {

    @Test
    void testDistribute() {
        // Create an instance of RoundRobinDistribution
        RoundRobinDistribution roundRobinDistribution = new RoundRobinDistribution();

        // Create stations and vehicles for testing
        List<RentalStation> stations = createStations(5);
        List<Vehicle> vehicles = createVehicles(10);

        // Apply the distribution strategy
        roundRobinDistribution.distribute(stations, vehicles);

        // Verify if the distribution is correct (adjust based on your logic)
        int totalVehiclesInStations = stations.stream().mapToInt(station -> station.getVehicles().size()).sum();
        assertEquals(vehicles.size(), totalVehiclesInStations);
    }

    // Utility methods to create stations and vehicles for testing
    private List<RentalStation> createStations(int count) {
        List<RentalStation> stations = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            stations.add(new RentalStation("Station" + (i + 1), 10, null));
        }
        return stations;
    }

    private List<Vehicle> createVehicles(int count) {
        List<Vehicle> vehicles = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            vehicles.add(new ClassicBike("Vehicle" + (i + 1), null, null, null));
        }
        return vehicles;
    }
}
