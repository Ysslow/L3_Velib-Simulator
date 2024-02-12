package vlille.visitor;

import vlille.Color;
import vlille.RentalStation;

/**
 * Class PainterVisitor
 */
public class PainterVisitor implements Visitor{
    private String name;

    /**
     * Constructor of PainterVisitor
     * @param name : name of the painter
     */
    public PainterVisitor(String name) {
        this.name = name;
    }

    /**
     * fonction to visit a rental station for the repairer
     */
    @Override
    public void visitRepairer(RentalStation rentalStation, int position) {
        // Ne fait rien
    }

    /**
     * fonction to visit a rental station for the painter
     */
    @Override
    public void visitPainter(RentalStation rentalStation, int position, Color color) {
        System.out.println("The painter " + this.name + " has painted " + rentalStation.getVehicles().get(position).getId() + " in " + color);
        rentalStation.paintBike(position, color);
    }
}
