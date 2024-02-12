package vlille;

/**
 * Class ClassicBike
 */
public class ClassicBike extends Vehicle {

	/**
	 * Constructor of ClassicBike
	 * @param id : id of the vehicle
	 * @param color : color of the vehicle
	 * @param size : size of the vehicle
	 * @param state : state of the vehicle
	 */
	public ClassicBike(String id, Color color, Size size, VehicleState state) {
		super(id, color, size, state);
	}

	/**
	 * Add option to the vehicle
	 * @return String : vehicle with option
	 */
	@Override
	public String addOption() {
		return "ClassicBike";
	}

}