package vlille.decorator;

import vlille.Color;
import vlille.Size;
import vlille.Vehicle;

/**
 * Class Baggage
 */
public class Baggage extends Option {

    /**
     * Constructor of Baggage
     * @param vehicle : vehicle to add option
     * @param size : size of the option
     * @param color : color of the option
     */
    public Baggage(Vehicle vehicle, Size size, Color color) {
        super(vehicle, size, color);
    }

    /**
     * Add option to the vehicle
     * @return String : vehicle with option
     */
	@Override
	public String addOption() {
		return this.vehicle.addOption() + " with Baggage";
	}

}
