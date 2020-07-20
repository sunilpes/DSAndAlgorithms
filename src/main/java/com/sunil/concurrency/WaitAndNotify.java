package com.sunil.concurrency;

/**
 * https://www.baeldung.com/java-wait-notify
 */
class Data {

    // True, if receiver should wait
    // False, if sender should wait
    private boolean transfer = true;
    private String packet;

    public synchronized void send(String packet) throws InterruptedException {
        while(!transfer) {
            wait();
        }
        transfer = false;
        this.packet = packet;
        notify();
    }

    public synchronized String receive() throws InterruptedException {
        while(transfer) {
            wait();
        }
        transfer = true;
        notify();
        return packet;
    }
}


public class WaitAndNotify {

    public static void main(String[] args) {
        Data dataObject = new Data();

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
                        dataObject.send(value);
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
                try {
                    for (String value= dataObject.receive(); !"END".equals(value); value=dataObject.receive()) {
                        System.out.println(value);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
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
