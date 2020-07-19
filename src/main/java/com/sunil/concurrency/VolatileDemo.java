package com.sunil.concurrency;

class Worker implements Runnable {

    private volatile boolean isTerminated = false;

    @Override
    public void run() {
        while (!isTerminated) {
            System.out.println("hello from Runner Thread!");
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void setTerminated(boolean value) {
        this.isTerminated = value;
    }

    public boolean getTerminated() {
        return this.isTerminated;
    }
}

public class VolatileDemo {

    public static void main(String[] args) throws InterruptedException {
        Worker worker = new Worker();
        Thread t1 = new Thread(worker);
        t1.start();
        Thread.sleep(3000);
        worker.setTerminated(true);

        t1.join();
        System.out.println("Worker thread is Finished!");
    }
}
