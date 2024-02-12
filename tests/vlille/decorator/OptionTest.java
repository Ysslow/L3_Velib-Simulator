package vlille.decorator;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import vlille.Color;
import vlille.Size;
import vlille.Vehicle;
import vlille.VehicleState;

public abstract class OptionTest {

    protected Option option;

    protected abstract Option createOption(Vehicle vehicle);

    @BeforeEach
    public void setUp() {
        Vehicle baseVehicle = new MockVehicle("123", Color.RED, Size.MEDIUM, VehicleState.GOOD);
        option = createOption(baseVehicle);
    }

    @Test
    public void testAddOption() {
        String result = option.addOption();
        assertTrue(result.contains(" with " + option.getClass().getSimpleName()));
    }


    @Test
    public void testGetSize() {
        Size size = option.getSize();
        assertEquals(Size.SMALL, size);
    }

    @Test
    public void testSetColor() {
        option.setColor(Color.BLUE);
        assertEquals(Color.BLUE, option.getColor());
    }
    
    @Test
    public void testSetSize() {
        option.setSize(Size.LARGE);
        assertEquals(Size.LARGE, option.getSize());
    }

}
