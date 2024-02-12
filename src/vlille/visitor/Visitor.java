package vlille.visitor;

import vlille.Color;
import vlille.RentalStation;

/**
 * Interface Visitor
 */
public interface Visitor {
    /**
     * Visit a rental station for the repairer
     * @param rentalStation : rental station to visit
     * @param position : position of the rental station
     */
    void visitRepairer(RentalStation rentalStation, int position);

    /**
     * Visit a rental station for the painter
     * @param rentalStation : rental station to visit
     * @param position : position of the rental station
     * @param color : color to paint
     */
    void visitPainter(RentalStation rentalStation, int position, Color color);
}
