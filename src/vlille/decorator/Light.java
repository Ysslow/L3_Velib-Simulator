package vlille.decorator;

import vlille.*;
import vlille.Vehicle;

/**
 * Class Light
 */
public class Light extends Option {
    
    
    
    /**
     * Constructor of Light
     * @param vehicle : vehicle to add option
     * @param size : size of the option
     * @param color : color of the option
     */
    public Light(Vehicle vehicle, Size size, Color color) {
        super(vehicle, size, color);
    }

    /**
     * Add option to the vehicle
     * @return String : vehicle with option
     */
	@Override
	public String addOption() {
		return this.vehicle.addOption() + " with Light";
	}

    
}
