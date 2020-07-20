package com.sunil.concurrency;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * https://www.baeldung.com/java-concurrent-locks
 */
class Messenger {

    private String packet;
    private Lock lock = new ReentrantLock(true);
    private Condition condition = lock.newCondition();
    // True, if receiver should wait
    // False, if sender should wait
    private boolean transfer = true;

    /**
     * lock.lock() and lock.unlock() is similar to synchronized block.
     * condition.await() and condition.signal() is similar to wait() and notify()
     *
     */
    public void send(String packet) {
        lock.lock();
        try {
            while (!transfer) {
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.packet = packet;
            transfer = false;
        } finally {
            condition.signal();
            lock.unlock();
        }

    }

    public String receive() {
        lock.lock();
        try {
            while (transfer) {
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            transfer = true;
            return packet;
        } finally {
            condition.signal();
            lock.unlock();
        }
    }



}

public class LocksWithProducerConsumer {
    public static void main(String[] args) {
        Messenger messenger = new Messenger();

        Thread t1 = new Thread(new Runnable() {

            private String[] data = {
                    "First packet",
                    "Second packet",
                    "Third packet",
                    "Fourth packet",
                    "END"
            };

            @Override
            public void run() {
                try {
                    for(String value: data) {
                        messenger.send(value);
                        Thread.sleep(500); // mimic server side processing
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (String value= messenger.receive(); !"END".equals(value); value=messenger.receive()) {
                    System.out.println(value);
                }
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
