package vlille.decorator;

import vlille.Color;
import vlille.Size;
import vlille.Vehicle;
import vlille.VehicleState;

public class MockVehicle extends Vehicle {

    public MockVehicle(String id, Color color, Size size, VehicleState state) {
        super(id, color, size, state);
    }

    @Override
    public String addOption() {
        return "MockVehicle";
    }
}
