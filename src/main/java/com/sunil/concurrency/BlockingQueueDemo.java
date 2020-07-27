package com.sunil.concurrency;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.stream.Stream;

class FirstWorker implements Runnable {

    BlockingQueue<Integer> blockingQueue;
    int counter = 0;

    FirstWorker(BlockingQueue<Integer> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        while(true) {
            try {
                blockingQueue.put(counter);
                System.out.println("putting item: " + counter);
                counter++;
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class SecondWorker implements Runnable {

    BlockingQueue<Integer> blockingQueue;

    SecondWorker(BlockingQueue<Integer> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        while(true) {
            try {
                int num = blockingQueue.take();
                System.out.println("taking item: " + num);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


public class BlockingQueueDemo {

    public static void main(String[] args) {
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(10);

        Thread firstWorker = new Thread(new FirstWorker(blockingQueue));
        Thread secondWorker = new Thread(new SecondWorker(blockingQueue));

        firstWorker.start();
        secondWorker.start();

        try {
            firstWorker.join();
            secondWorker.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
