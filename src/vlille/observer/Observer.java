package vlille.observer;

/**
 * Interface Observer
 */
public interface Observer {
    /**
     * Notify the observer for deposit
     */
    void notifyDeposit();
    /**
     * Notify the observer for withdraw
     */
    void notifyWithdraw();
}