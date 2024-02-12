package vlille.observer;

/**
 * Interface Subject
 */
public interface Subject {

    /**
     * Register an observer
     * @param observer : observer to register
     */
    void registerObserver(Observer observer);

    /**
     * Remove an observer
     * @param observer : observer to remove
     */
    void removeObserver(Observer observer);

    /**
     * Notify Observers for deposit
     */
    void notifyObserversDeposit();

    /**
     * Notify Observers for withdraw
     */
    void notifyObserversWithdraw();
}
