package vlille;

/**
 * Class ElectricBike
 */
public class ElectricBike extends Vehicle {

	private Boolean battery;
	
	/**
	 * Constructor of ElectricBike
	 * @param id : id of the vehicle
	 * @param color : color of the vehicle
	 * @param size : size of the vehicle
	 * @param state : state of the vehicle
	 */
	public ElectricBike(String id, Color color, Size size, VehicleState state) {
		super(id, color, size, state);
		this.battery = true;
	}

	/**
	 * Add option to the vehicle
	 * @return String : vehicle with option
	 */
	@Override
	public String addOption() {
		return "ElectricBike";
	}

	/**
	 * getter of battery 
	 * @return battery of the vehicle
	 */
	public Boolean getBattery() {
		return battery;
	}

	/**
	 * setter of battery 
	 * @param battery : battery of the vehicle
	 */
	public void setBattery(Boolean battery) {
		this.battery = battery;
	}
	
	
}