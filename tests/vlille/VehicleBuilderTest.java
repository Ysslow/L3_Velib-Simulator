package vlille;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class VehicleBuilderTest {

    @Test
    void testClassicBikeBuilder() {
        ClassicBikeBuilder classicBikeBuilder = new ClassicBikeBuilder();
        classicBikeBuilder.id("1").color(Color.RED).size(Size.LARGE).state(VehicleState.BROKEN);

        Vehicle classicBike = classicBikeBuilder.build();

        assertEquals("1", classicBike.getId());
        assertEquals(Color.RED, classicBike.getColor());
        assertEquals(Size.LARGE, classicBike.getSize());
        assertEquals(VehicleState.BROKEN, classicBike.getState());
        assertTrue(classicBike instanceof ClassicBike);
    }

    @Test
    void testElectricBikeBuilder() {
        ElectricBikeBuilder electricBikeBuilder = new ElectricBikeBuilder();
        electricBikeBuilder.id("2").color(Color.BLUE).size(Size.SMALL).state(VehicleState.STOLEN);

        Vehicle electricBike = electricBikeBuilder.build();

        assertEquals("2", electricBike.getId());
        assertEquals(Color.BLUE, electricBike.getColor());
        assertEquals(Size.SMALL, electricBike.getSize());
        assertEquals(VehicleState.STOLEN, electricBike.getState());
        assertTrue(electricBike instanceof ElectricBike);
    }
}
