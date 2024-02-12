package vlille.decorator;

import vlille.Color;
import vlille.Size;
import vlille.Vehicle;

/**
 * Class Option
 */
public abstract class Option extends Vehicle{

    /** the vehicle to add the option */
    protected Vehicle vehicle;
    /** the size */
    protected Size size;
    /** the color */
    protected Color color;
    
    /**
     * Constructor of Option
     * @param vehicle : vehicle to add the option
     * @param size : size of the option
     * @param color : color of the option
     */
    public Option(Vehicle vehicle, Size size, Color color) {
    	super(vehicle.getId(), vehicle.getColor(), vehicle.getSize(), vehicle.getState());
        this.vehicle = vehicle;
        this.size = size;
        this.color = color;
    }

    /**
     * add option to the vehicle
     * @return the type of the vehicle with the option
     */
    public abstract String addOption();
    
    /**
     * getter of size 
     * @return size of the option
    */
    public Size getSize() {
    	return this.size;
    }
    
    /**
     * setter of size 
     * @param size : size of the option
    */
    public void setSize(Size size) {
    	this.size = size;
    }
    
    /**
     * getter of color 
     * @return color of the option
    */
    public Color getColor() {
    	return this.color;
    }
    
    /**
     * setter of color 
     * @param color : color of the option
    */
    public void setColor(Color color) {
    	this.color = color;
    }
    
}