package vlille.decorator;

import vlille.Color;
import vlille.Size;

/**
 * Class LightBuilder
 */
public class LightBuilder extends OptionBuilder {

	/**
     * Default constructor
     */
    public LightBuilder() {
        // Aucune initialisation n'est effectuée, necessaire pour corriger le warning dans génération de docs.
    }

	/**
	 * Build the light
	 * @return Option : light
	 */
	@Override
	public Option build() {
		if (this.size == null)
			this.size = Size.MEDIUM;
		if (this.color == null)
			this.color = Color.BLACK;
		return new Light(this.vehicle,this.size, this.color);
	}
}
