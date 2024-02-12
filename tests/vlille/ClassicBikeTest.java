package vlille;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ClassicBikeTest extends VehicleTest {

    @Override
    protected Vehicle createVehicle() {
        return new ClassicBike("123", Color.RED, Size.LARGE, VehicleState.BROKEN);
    }
    
    @Override
    protected String getExpectedOptionString() {
        return "ClassicBike";
    }

    @Test
    public void testClassicBikeRepairWhenBroken() {
        vehicle.repair();
        assertEquals(VehicleState.GOOD, vehicle.getState());
    }

    @Test
    public void testClassicBikeRepairWhenGood() {
        vehicle.setState(VehicleState.GOOD);
        vehicle.repair();
        assertEquals(VehicleState.GOOD, vehicle.getState());
    }

}
