package vlille.decorator;

import vlille.Color;
import vlille.Size;

/**
 * Class BaggageBuilder 
*/
public class BaggageBuilder extends OptionBuilder {

	/**
     * Default constructor
     */
    public BaggageBuilder() {
        // Aucune initialisation n'est effectuée, necessaire pour corriger le warning dans génération de docs.
    }

	/**
	 * Build the baggage
	 * @return Option : baggage
	 */
	@Override
	public Option build() {
		if (this.size == null)
			this.size = Size.MEDIUM;
		if (this.color == null)
			this.color = Color.BLACK;
		return new Baggage(this.vehicle,this.size, this.color);
	}
}