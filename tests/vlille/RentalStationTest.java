package vlille;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import vlille.visitor.PainterVisitor;
import vlille.visitor.RepairerVisitor;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RentalStationTest {

    private RentalStation rentalStation;
    private ControlCenter controlCenter;
    private Vehicle vehicle;

    @BeforeEach
    void setUp() {
        controlCenter = ControlCenter.getInstance(null);
        rentalStation = new RentalStation("Station1", 15, controlCenter);
        vehicle = new ElectricBike("Bike1", Color.RED, Size.MEDIUM, VehicleState.GOOD);

    }

    @Test
    void testAddVehicle() {
        assertTrue(rentalStation.addVehicle(vehicle));
        assertEquals(1, rentalStation.getVehicles().size());
    }

    @Test
    void testRemoveVehicle() {
        rentalStation.addVehicle(vehicle);
        assertTrue(rentalStation.removeVehicle(vehicle));
        assertEquals(0, rentalStation.getVehicles().size());
    }

    @Test
    void testPaintBike() {
        rentalStation.addVehicle(vehicle);
        rentalStation.paintBike(0, Color.BLUE);
        assertEquals(Color.BLUE, rentalStation.getVehicles().get(0).getColor());
    }

    @Test
    void testRepairBike() {
        rentalStation.addVehicle(vehicle);
        rentalStation.repairBike(0);
        assertEquals(VehicleState.GOOD, rentalStation.getVehicles().get(0).getState());
    }

    @Test
    void testCheckAtPosition() {
        rentalStation.addVehicle(vehicle);
        assertTrue(rentalStation.checkAtPosition(0));
    }

    @Test
    void testIsEmpty() {
        assertTrue(rentalStation.isEmpty());
    }

    @Test
    void testIsFull() {
        for (int i = 0; i < rentalStation.getStorage(); i++) {
            rentalStation.addVehicle(new ElectricBike("Bike" + i, Color.RED, Size.MEDIUM, VehicleState.GOOD));
        }
        assertTrue(rentalStation.isFull());
    }

    @Test
    void testAcceptPainter() {
        PainterVisitor painterVisitor = new PainterVisitor("John");
        rentalStation.addVehicle(vehicle);
        rentalStation.acceptPainter(painterVisitor, 0, Color.BLUE);
        assertEquals(Color.BLUE, rentalStation.getVehicles().get(0).getColor());
    }

    @Test
    void testAcceptRepairer() {
        RepairerVisitor repairerVisitor = new RepairerVisitor("Bob");
        rentalStation.addVehicle(vehicle);
        rentalStation.acceptRepairer(repairerVisitor, 0);
        assertEquals(VehicleState.GOOD, rentalStation.getVehicles().get(0).getState());
    }
}
