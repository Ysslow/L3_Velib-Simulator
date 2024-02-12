package vlille;

/**
 * Class ElectricBikeBuilder
 */
public class ElectricBikeBuilder extends VehicleBuilder {

    /**
     * Default constructor
     */
    public ElectricBikeBuilder() {
        // Aucune initialisation n'est effectuée, necessaire pour corriger le warning dans génération de docs.
    }

    /**
     * Build the electric bike
     * @return Vehicle : electric bike
     */
    @Override
    public Vehicle build() {
        if (this.color == null)
            this.color = Color.BLACK;
        if (this.size == null)
            this.size = Size.MEDIUM;
        if (this.state == null)
            this.state = VehicleState.GOOD;
        return new ElectricBike(this.id, this.color, this.size, this.state);
    }
}
