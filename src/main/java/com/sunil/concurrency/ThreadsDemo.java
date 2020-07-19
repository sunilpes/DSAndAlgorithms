package com.sunil.concurrency;

class Runner extends Thread {
    String name;

    Runner(String name) {
        this.name = name;
    }

    public void run() {
        int[] numbers = {1,2,3,4,5,6,7,8,9,10};
        for (int i: numbers) {
            System.out.println(name+ " ==> number :" + i);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Runner2 implements Runnable {
    String name;

    Runner2(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        int[] numbers = {1,2,3,4,5,6,7,8,9,10};
        for (int i: numbers) {
            System.out.println(name+ " ==> number :" + i);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class ThreadsDemo {

    public static void main(String[] args) throws InterruptedException {
        Runner r1 = new Runner("Thread1");
        r1.start();

        Runner r2 = new Runner("Thread2");
        r2.start();

        Thread r3 = new Thread(new Runner2("Thread3"));
        r3.start();

        Thread r4 = new Thread(new Runnable() {
            @Override
            public void run() {
                int[] numbers = {1,2,3,4,5,6,7,8,9,10};
                for (int i: numbers) {
                    System.out.println("Anonymous"+ " ==> number :" + i);

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        r4.start();

        try {
            r1.join(); // waits until the r2 thread dies!
            r2.join();
            r3.join();
            r4.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        System.out.println("waiting for all threads to finish!");


    }

}
