package vlille;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public abstract class VehicleTest {

    protected Vehicle vehicle;

    protected abstract Vehicle createVehicle();

    @BeforeEach
    public void setUp() {
        vehicle = createVehicle();
    }

    @Test
    public void testRepair() {
        VehicleState initialState = vehicle.getState();

        vehicle.repair();

        assertEquals(VehicleState.GOOD, vehicle.getState());

        if (initialState == VehicleState.GOOD) {
            assertSame(initialState, vehicle.getState());
        }
    }

    @Test
    public void testAddOptionReturnsCorrectString() {
        assertEquals(getExpectedOptionString(), vehicle.addOption());
    }

    protected abstract Object getExpectedOptionString();

	@Test
    public void testGettersAndSetters() {
        assertEquals("123", vehicle.getId());
        assertEquals(Color.RED, vehicle.getColor());
        assertEquals(Size.LARGE, vehicle.getSize());
        assertEquals(VehicleState.BROKEN, vehicle.getState());
        assertEquals(0, vehicle.getNumIntervalsAlone());
        assertEquals(0, vehicle.getNumRentals());
        assertEquals(0, vehicle.getNumRepairIntervals());


        vehicle.setId("456");
        vehicle.setColor(Color.BLUE);
        vehicle.setSize(Size.MEDIUM);
        vehicle.setState(VehicleState.GOOD);
        vehicle.incrementNumIntervalsAlone();
        vehicle.incrementNumRentals();
        vehicle.incrementNumRepairIntervals();
        
        assertEquals("456", vehicle.getId());
        assertEquals(Color.BLUE, vehicle.getColor());
        assertEquals(Size.MEDIUM, vehicle.getSize());
        assertEquals(VehicleState.GOOD, vehicle.getState());
        assertEquals(1, vehicle.getNumIntervalsAlone());
        assertEquals(1, vehicle.getNumRentals());
        assertEquals(1, vehicle.getNumRepairIntervals());
        
        vehicle.resetNumIntervalsAlone();
        vehicle.resetNumRentals();
        vehicle.resetNumRepairIntervals();
        
        assertEquals(0, vehicle.getNumIntervalsAlone());
        assertEquals(0, vehicle.getNumRentals());
        assertEquals(0, vehicle.getNumRepairIntervals());
    }

}
