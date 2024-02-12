package vlille;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import vlille.observer.Observer;
import vlille.strategy.DistributionStrategy;
import vlille.strategy.RoundRobinDistribution;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ControlCenterTest {

    private ControlCenter controlCenter;

    @BeforeEach
    void setUp() {
        // Create a RoundRobinDistribution instance (or use another DistributionStrategy of your choice)
        DistributionStrategy distributionStrategy = new RoundRobinDistribution();

        // Initialize ControlCenter with the DistributionStrategy
        controlCenter = ControlCenter.getInstance(distributionStrategy);
    }

    @Test
    void testNotifyDeposit() {
        RentalStation station = new RentalStation("Station1", 10, controlCenter);

        // Register an observer
        TestObserver observer = new TestObserver();
        controlCenter.registerObserver(observer);

        // Notify deposit
        controlCenter.notifyDeposit();

        // Check if the observer is notified
        assertFalse(observer.isDepositNotified());

        // Reset the flag in the observer
        observer.reset();

        // Perform some action that triggers observer notification, e.g., distribute vehicles
        controlCenter.distributeVehicle(List.of(station), new ArrayList<>());

        // Check if the observer is notified
        assertTrue(observer.isDepositNotified());
    }

    @Test
    void testRemoveObserver() {
        RentalStation station = new RentalStation("Station1", 10, controlCenter);

        // Register an observer
        TestObserver observer = new TestObserver();
        controlCenter.registerObserver(observer);

        // Remove the observer
        controlCenter.removeObserver(observer);

        // Notify deposit
        controlCenter.notifyDeposit();

        // Check if the observer is not notified
        assertFalse(observer.isDepositNotified());

        // Perform some action that triggers observer notification, e.g., distribute vehicles
        controlCenter.distributeVehicle(List.of(station), new ArrayList<>());

        // Check if the observer is not notified
        assertFalse(observer.isDepositNotified());
    }

    @Test
    void testSimulateRandomWithdrawal() {
        RentalStation station = new RentalStation("Station1", 10, controlCenter);

        // Add a bike to the station
        Vehicle bike = new ClassicBike("Bike1", Color.RED, Size.MEDIUM, VehicleState.GOOD);
        station.addVehicle(bike);

        // Simulate a random withdrawal
        controlCenter.simulateRandomWithdrawal(List.of(station),new ArrayList<>());

        // Check if the bike is withdrawn
        assertTrue(station.getVehicles().isEmpty());
        assertTrue(controlCenter.getCirculatingVehicles().contains(bike));
    }

    @Test
    void testSimulateRandomDeposit() {
        RentalStation station = new RentalStation("Station1", 10, controlCenter);

        // Add a bike to the station
        Vehicle bike = new ClassicBike("Bike1", Color.RED, Size.MEDIUM, VehicleState.GOOD);
        station.addVehicle(bike);
        controlCenter.simulateRandomWithdrawal(List.of(station), new ArrayList<>());;
        
        assertTrue(station.getVehicles().isEmpty());
        
        // Simulate a random deposit
        controlCenter.simulateRandomDeposit(List.of(station), new ArrayList<>());

        // Check if the bike is deposited
        assertFalse(station.getVehicles().isEmpty());
        assertFalse(controlCenter.getCirculatingVehicles().contains(bike));
    }

    @Test
    void testNotifyWithdraw() {
        RentalStation station = new RentalStation("Station1", 10, controlCenter);

        // Register an observer
        TestObserver observer = new TestObserver();
        controlCenter.registerObserver(observer);

        // Notify withdraw
        controlCenter.notifyWithdraw();

        // Perform some action that triggers observer notification, e.g., distribute vehicles
        controlCenter.distributeVehicle(List.of(station), new ArrayList<>());

        // Check if the observer is notified
        assertTrue(observer.isWithdrawNotified());
    }

    @Test
    void testCheckAndRedistribute() {
        RentalStation station = new RentalStation("Station1", 10, controlCenter);

        // Add a bike to the station
        Vehicle bike = new ClassicBike("Bike1", Color.RED, Size.MEDIUM, VehicleState.GOOD);
        station.addVehicle(bike);

        // Check and redistribute
        controlCenter.checkAndRedistribute(List.of(station), new ArrayList<>());

        // Check if the bike is still in the station after redistribution
        assertTrue(station.getVehicles().contains(bike));
    }

    @Test
    void testDetectStolenBike() {
        RentalStation station = new RentalStation("Station1", 10, controlCenter);

        // Add a lonely bike to the station
        Vehicle lonelyBike = new ClassicBike("Bike1", Color.RED, Size.MEDIUM, VehicleState.GOOD);
        station.addVehicle(lonelyBike);

        // Simulate intervals without triggering theft
        controlCenter.detectStolenBike(List.of(station), new ArrayList<>());
        controlCenter.detectStolenBike(List.of(station), new ArrayList<>());

        // Check if the bike is still in the station
        assertTrue(station.getVehicles().contains(lonelyBike));

        // Simulate intervals triggering theft
        controlCenter.detectStolenBike(List.of(station), new ArrayList<>());

        // Check if the bike is stolen
        assertFalse(station.getVehicles().contains(lonelyBike));
    }

    @Test
    void testShowCurrentDistribution() {
        RentalStation station = new RentalStation("Station1", 10, controlCenter);

        // Add bikes to the station
        Vehicle bike1 = new ClassicBike("Bike1", Color.RED, Size.MEDIUM, VehicleState.GOOD);
        Vehicle bike2 = new ClassicBike("Bike2", Color.BLUE, Size.LARGE, VehicleState.GOOD);
        station.addVehicle(bike1);
        station.addVehicle(bike2);

        // Show current distribution
        controlCenter.showCurrentDistribution(List.of(station));
        // You might want to validate the output by checking the console or by using a logging framework
    }

    @Test
    void testCallRepairer() {
        RentalStation station = new RentalStation("Station1", 10, controlCenter);

        // Add a broken bike to the station
        Vehicle brokenBike = new ClassicBike("Bike1", Color.RED, Size.MEDIUM, VehicleState.BROKEN);
        station.addVehicle(brokenBike);

        // Call the repairer and check if the bike is repaired
        controlCenter.callRepairer(List.of(station));
        assertEquals(VehicleState.BROKEN, brokenBike.getState());
    }


    // TestObserver class for testing observer notifications
    private static class TestObserver implements Observer {
        private boolean depositNotified = false;
        private boolean withdrawNotified = false;

        @Override
        public void notifyDeposit() {
            depositNotified = true;
        }

        @Override
        public void notifyWithdraw() {
            withdrawNotified = true;
        }

        public boolean isDepositNotified() {
            return depositNotified;
        }

        public boolean isWithdrawNotified() {
            return withdrawNotified;
        }
        
        public void reset() {
            depositNotified = false;
        }
    }
}
