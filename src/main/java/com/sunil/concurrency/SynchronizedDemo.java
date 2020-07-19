package com.sunil.concurrency;

public class SynchronizedDemo {

    public static int counter = 0;

    public static synchronized void increment() {
        ++counter;
    }

    public static void process() throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i< 100;++i) {
                    //++counter; //causes unreliable results during execution
                    increment();
                }
            }
        });


        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i< 100;++i) {
                    //++counter; //causes unreliable results during execution
                    increment();
                }
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }

    public static void main(String[] args) throws InterruptedException {
        process();
        System.out.println(SynchronizedDemo.counter);
    }
}
