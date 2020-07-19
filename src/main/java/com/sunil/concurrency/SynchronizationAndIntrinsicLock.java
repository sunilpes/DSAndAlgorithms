package com.sunil.concurrency;

public class SynchronizationAndIntrinsicLock {
    public static int count1 = 0;
    public static int count2 = 0;

    public static Object lock1 = new Object();
    public static Object lock2 = new Object();

    /**
     * If synchronised block is using intrinsic lock on SynchronizationAndIntrinsicLock class then
     * only one thread can have this lock at a time and another thread need to wait till it finishes.
     * So, if we create fictitious Object then both the thread can access  incrementCount1 and incrementCount2 method
     * without waiting for each other.
     */
    public static void incrementCount1() {
        synchronized(lock1) {
            ++count1;
        }
    }

    public static void incrementCount2() {
        synchronized(lock2) {
            ++count2;
        }
    }

    public static void process() throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<10;i++) {
                    incrementCount1();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<10;i++) {
                    incrementCount2();
                }
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
        System.out.println("Finished processing");
        System.out.println("count1="+count1+"   count2="+count2);
    }


    public static void main(String[] args) throws InterruptedException {
        process();
    }

}
