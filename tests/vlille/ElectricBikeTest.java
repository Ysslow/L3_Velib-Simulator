package vlille;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ElectricBikeTest extends VehicleTest {

    @Override
    protected Vehicle createVehicle() {
        return new ElectricBike("123", Color.RED, Size.LARGE, VehicleState.BROKEN);
    }
    
    @Override
    protected String getExpectedOptionString() {
        return "ElectricBike";
    }

    @Test
    public void testElectricBikeWithBattery() {
        assertTrue(((ElectricBike) vehicle).getBattery());
    }

    @Test
    public void testElectricBikeWithChargedBattery() {
        assertTrue(((ElectricBike) vehicle).getBattery());
        ((ElectricBike) vehicle).setBattery(false);
        assertFalse(((ElectricBike) vehicle).getBattery());
    }
}
