package vlille;

/**
 * Abstract class VehicleBuilder
*/
public abstract class VehicleBuilder {

    /** the id */
    protected String id;
    /** the state */
    protected VehicleState state;
    /** the color */
    protected Color color;
    /** the size */
    protected Size size;

    /**
     * Default constructor
     */
    public VehicleBuilder() {
        // Aucune initialisation n'est effectuée, necessaire pour corriger le warning dans génération de docs.
    }

    /**
     * Constructor of VehicleBuilder
     * @param id : id of the vehicle
     * @return VehicleBuilder : vehicle builder
     */
    public VehicleBuilder id(String id) {
    	this.id = id;
    	return this;
    }

    /**
     * setter of color
     * @param color : color of the vehicle
     * @return VehicleBuilder : vehicle builder
     */
    public VehicleBuilder color(Color color) {
        this.color = color;
        return this;
    }

    /**
     * setter of size
     * @param size : size of the vehicle
     * @return VehicleBuilder : vehicle builder
     */
    public VehicleBuilder size(Size size) {
        this.size = size;
        return this;
    }

    /**
     * setter of state
     * @param state : state of the vehicle
     * @return VehicleBuilder : vehicle builder
     */
    public VehicleBuilder state(VehicleState state) {
        this.state = state;
        return this;
    }

    /**
     * Build the vehicle
     * @return Vehicle : vehicle
     */
    public abstract Vehicle build();
}
