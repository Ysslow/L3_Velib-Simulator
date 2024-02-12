package vlille.visitor;

import vlille.Color;
import vlille.RentalStation;

/**
 * Class RepairerVisitor
 */
public class RepairerVisitor implements Visitor {
    private static final int CONSTREPAIRINTERVALS = 2;
    private String name;

    /**
     * Constructor of RepairerVisitor
     * @param name : name of the repairer
     */
    public RepairerVisitor(String name) {
        this.name = name;
    }

    /**
     * fonction to visit a rental station for the repairer
     */
    @Override
    public void visitRepairer(RentalStation rentalStation, int position) {
        // Répare le véhicule
        if (rentalStation.getVehicles().get(position).getNumRepairIntervals() < CONSTREPAIRINTERVALS){
            rentalStation.getVehicles().get(position).incrementNumRepairIntervals();
        }
        else{
            System.out.println("The repairer " + this.name + " has repaired " + rentalStation.getVehicles().get(position).getId() + " after " + CONSTREPAIRINTERVALS + " intervals");
            rentalStation.repairBike(position);
            rentalStation.getVehicles().get(position).resetNumRepairIntervals();
        }
    }

    /**
     * fonction to visit a rental station for the painter
     */
    @Override
    public void visitPainter(RentalStation rentalStation, int position, Color color) {
        // Ne fait rien
    }
}
