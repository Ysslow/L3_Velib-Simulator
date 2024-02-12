package vlille;

import java.util.ArrayList;

import vlille.observer.Observer;
import vlille.visitor.Visitor;

/**
 * Class RentalStation
 */
public class RentalStation implements Observer {

    /** Id of the station */
    private String id;
    /** Storage of the station */
    private int storage;
    /** List of vehicles */
    private ArrayList<Vehicle> vehicles;
    /** Control center of the station */
    private ControlCenter controlCenter;
    /** True if the station is notifying the control center, false otherwise */
    private boolean isControlCenterNotifying = false;

    /**
     * Constructor of RentalStation
     * @param id : id of the station
     * @param storage : storage of the station
     * @param controlCenter : control center of the station
    */
    public RentalStation(String id, int storage, ControlCenter controlCenter) {
        this.id = id;
        if (storage >= 10 && storage <= 20)
            this.storage = storage;
        else {
            this.storage = 10; 
            System.out.println("Storage must be between 10 and 20, default value is 10");
        }
        this.vehicles = new ArrayList<Vehicle>();
        if (controlCenter != null) {
            this.controlCenter = controlCenter;
            this.controlCenter.registerObserver(this);
        }
    }

    /**
     * Notify the station that a vehicle has been deposited
     */
    @Override
    public void notifyDeposit() {
        // Ne notifie le ControlCenter que si ce n'est pas déjà notifié par lui-même
        if (!isControlCenterNotifying) {
            System.out.println("\n" + this.id + " notified of a deposit.");
            this.controlCenter.notifyDeposit();
        }
    }

    /**
     * Notify the station that a vehicle has been withdrawn
     */
    @Override
    public void notifyWithdraw() {
        // Ne notifie le ControlCenter que si ce n'est pas déjà notifié par lui-même
        if (!isControlCenterNotifying) {
            System.out.println("\n" + this.id + " notified of a withdrawal.");
            this.controlCenter.notifyWithdraw();
        }
    }

    /**
     * Notify the control center that a vehicle has been deposited
     */
    public void notifyControlCenterDeposit() {
        isControlCenterNotifying = true;
        controlCenter.notifyDeposit();
        isControlCenterNotifying = false;
    }

    /**
     * Notify the control center that a vehicle has been withdrawn
     */
    public void notifyControlCenterWithdraw() {
        isControlCenterNotifying = true;
        controlCenter.notifyWithdraw();
        isControlCenterNotifying = false;
    }

    /** 
     * add a vehicle to the list of vehicles
     * @param vehicle : vehicle to add
     * @return true if the vehicle is added, false otherwise
     */
    public boolean addVehicle(Vehicle vehicle) {
        if (this.vehicles.size() < this.storage){
            this.vehicles.add(vehicle);
            vehicle.numRentals++;
            return true;
        } else
            return false;
    }

    /**
     * remove a vehicle from the list of vehicles
     * @param vehicle : vehicle to remove
     * @return true if the vehicle is removed, false otherwise (if the vehicle is not in the list)
     */
    public boolean removeVehicle(Vehicle vehicle) {
        if (this.vehicles.contains(vehicle)) {
            this.vehicles.remove(vehicle);
            return true;
        } else
            return false;
    }
    /**
     * check if the station is empty
     * @return true if the station is empty, false otherwise
     */
    public boolean isEmpty() {
        return this.vehicles.isEmpty();
    }

    /**
     * check if the station is full
     * @return true if the station is full, false otherwise
     */
    public boolean isFull() {
        return this.vehicles.size() == this.storage;
    }

    /** 
     * paint a bike at the position
     * @param position : position of the bike to paint
     * @param color : color to paint the bike
     */
    public void paintBike(int position, Color color) {
        this.vehicles.get(position).setColor(color);
    }

    /** 
     * repair a bike at the position
     * @param position : position of the bike to repair
     */
    public void repairBike(int position) {
        this.vehicles.get(position).repair();
        this.vehicles.get(position).resetNumRentals();
    }

    /** 
     * check if there is a vehicle at the position
     * @param position : position to check
     * @return true if there is a vehicle at the position, false otherwise
     */
    public boolean checkAtPosition(int position) {
        return this.vehicles.get(position) != null;
    }

    /**
     * return the list of vehicles
     * @return list of vehicles
     */
    public ArrayList<Vehicle> getVehicles() {
        return this.vehicles;
    }

    /**
     * return the id of the station
     * @return id of the station
     */
    public String getId() {
        return this.id;
    }

    /**
     * return the storage of the station
     * @return storage of the station
     */
    public int getStorage() {
        return this.storage;
    }

    /**
     * return the list of available vehicles
     * @return the list of available vehicles
     */
    public ArrayList<Vehicle> getAvailableVehicles() {
        ArrayList<Vehicle> availableVehicles = new ArrayList<Vehicle>();
        for (Vehicle vehicle : this.vehicles) {
            if (vehicle.getState() == VehicleState.GOOD)
                availableVehicles.add(vehicle);
        }
        return availableVehicles;
    }

    /**
     * return the position of a broken bike the station
     * @return the position of the bike
     */
    public int hasBrokenBikes() {
        for (Vehicle vehicle : this.vehicles) {
            if (vehicle.getState() == VehicleState.BROKEN)
                return this.vehicles.indexOf(vehicle);
        }
        return -1;
    }

    /**
     * True if the station has available slots, false otherwise
     * @return true if the station has available slots, false otherwise
     */
    public boolean hasAvailableSlots() {
        return this.vehicles.size() < this.storage;
    }

    /**
     * method to accept a visitor (repairer)
     * @param visitor : visitor to accept
     * @param position : position of the vehicle
     */
    public void acceptRepairer(Visitor visitor, int position) {
        visitor.visitRepairer(this, position);
    }

    /**
     * method to accept a visitor (painter)
     * @param visitor : visitor to accept
     * @param position : position of the vehicle
     * @param color : color to paint the vehicle
     */
    public void acceptPainter(Visitor visitor, int position, Color color) {
        visitor.visitPainter(this, position, color);
    }
}
