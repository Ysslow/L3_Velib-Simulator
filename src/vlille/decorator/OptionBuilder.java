package vlille.decorator;

import vlille.Color;
import vlille.Size;
import vlille.Vehicle;

/**
 * Class OptionBuilder
 */
public abstract class OptionBuilder {

    /** the vehicle to add the option */
	protected Vehicle vehicle;
    /** the size */
    protected Size size;
    /** the color */
    protected Color color;

    /**
     * Default constructor
     */
    public OptionBuilder() {
        // Aucune initialisation n'est effectuée, necessaire pour corriger le warning dans génération de docs.
    }
    
    /**
     * Constructor of OptionBuilder
     * @param vehicle : vehicle to add the option
     * @return OptionBuilder : option builder
    */
    public OptionBuilder vehicle(Vehicle vehicle) {
    	this.vehicle = vehicle;
    	return this;
    }
    
    /**
     * setter of size 
     * @param size : size of the option
     * @return OptionBuilder : option builder
    */
    public OptionBuilder size(Size size) {
    	this.size = size;
    	return this;
    }
    
    /**
     * setter of color 
     * @param color : color of the option
     * @return OptionBuilder : option builder
    */
    public OptionBuilder color(Color color) {
    	this.color = color;
    	return this;
    }
    
    /**
     * Build the option
     * @return Option : option
     */
    public abstract Option build();
}
