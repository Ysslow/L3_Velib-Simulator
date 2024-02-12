package vlille.decorator;

import vlille.Color;
import vlille.Size;
import vlille.Vehicle;
import vlille.VehicleState;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class LightTest extends OptionTest {

    @Override
    protected Option createOption(Vehicle vehicle) {
        return new Light(vehicle, Size.SMALL, Color.BLUE);
    }

    @Test
    public void testAddOptionWithLight() {
        Vehicle baseVehicle = new MockVehicle("123", Color.RED, Size.MEDIUM, VehicleState.GOOD);
        Option light = new Light(baseVehicle, Size.LARGE, Color.BLUE);

        String result = light.addOption();

        assertEquals("MockVehicle with Light", result);
    }
}
