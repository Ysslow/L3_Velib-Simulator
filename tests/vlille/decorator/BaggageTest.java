package vlille.decorator;

import vlille.Color;
import vlille.Size;
import vlille.Vehicle;
import vlille.VehicleState;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class BaggageTest extends OptionTest {

    @Override
    protected Option createOption(Vehicle vehicle) {
        return new Baggage(vehicle, Size.SMALL, Color.BLACK);
    }

    @Test
    public void testAddOptionWithBaggage() {
        Vehicle baseVehicle = new MockVehicle("123", Color.RED, Size.MEDIUM, VehicleState.GOOD);
        Option baggage = new Baggage(baseVehicle, Size.SMALL, Color.BLACK);

        String result = baggage.addOption();

        assertEquals("MockVehicle with Baggage", result);
    }

    // Ajoutez d'autres tests au besoin
}
