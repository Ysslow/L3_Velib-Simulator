package vlille;

/**
 * Class ClassicBikeBuilder
 */
public class ClassicBikeBuilder extends VehicleBuilder {

	/**
     * Default constructor
     */
    public ClassicBikeBuilder() {
        // Aucune initialisation n'est effectuée, necessaire pour corriger le warning dans génération de docs.
    }

	/**
	 * Build the classic bike
	 * @return Vehicle : classic bike
	 */
	@Override
	public Vehicle build() {
		if (this.color == null) {
			this.color = Color.BLACK;
		}
		if (this.size == null) {
			this.size = Size.MEDIUM;
		}
		if (this.state == null) {
			this.state = VehicleState.GOOD;
		}
		return new ClassicBike(this.id, this.color, this.size, this.state);
	}
}

