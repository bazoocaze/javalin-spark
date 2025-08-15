package spark.internal;

public class ResettableBarrier {
    private boolean blocked = false;

    public synchronized void pass() {
        while (blocked) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        }
    }

    public synchronized void block() {
        blocked = true;
    }

    public synchronized void unblock() {
        blocked = false;
        this.notifyAll();
    }
}
