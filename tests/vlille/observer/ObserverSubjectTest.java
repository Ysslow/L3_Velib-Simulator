package vlille.observer;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertTrue;

public class ObserverSubjectTest {

    @Test
    void testObserverSubjectInteraction() {
        // Create a concrete implementation of Subject (e.g., ControlCenter)
        ConcreteSubject concreteSubject = new ConcreteSubject();

        // Create an Observer
        TestObserver observer = new TestObserver();

        // Register the Observer with the Subject
        concreteSubject.registerObserver(observer);

        // Notify observers about a deposit
        concreteSubject.notifyObserversDeposit();

        // Check if the Observer was notified about the deposit
        assertTrue(observer.isDepositNotified());

        // Notify observers about a withdraw
        concreteSubject.notifyObserversWithdraw();

        // Check if the Observer was notified about the withdraw
        assertTrue(observer.isWithdrawNotified());
    }

    // Concrete implementation of Subject
    private static class ConcreteSubject implements Subject {
        private Observer observer;

        @Override
        public void registerObserver(Observer observer) {
            this.observer = observer;
        }

        @Override
        public void removeObserver(Observer observer) {
            // Implementation to remove the observer (if needed)
        }

        @Override
        public void notifyObserversDeposit() {
            observer.notifyDeposit();
        }

        @Override
        public void notifyObserversWithdraw() {
            observer.notifyWithdraw();
        }
    }

    // Concrete implementation of Observer for testing
    private static class TestObserver implements Observer {
        private boolean depositNotified = false;
        private boolean withdrawNotified = false;

        @Override
        public void notifyDeposit() {
            depositNotified = true;
        }

        @Override
        public void notifyWithdraw() {
            withdrawNotified = true;
        }

        public boolean isDepositNotified() {
            return depositNotified;
        }

        public boolean isWithdrawNotified() {
            return withdrawNotified;
        }
    }
}
