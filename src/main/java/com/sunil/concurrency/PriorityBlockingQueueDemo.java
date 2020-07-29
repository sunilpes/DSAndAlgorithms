package com.sunil.concurrency;


import java.util.Random;
import java.util.concurrent.*;
import java.util.stream.IntStream;

class Emitter implements Runnable {
    BlockingQueue<Integer> queue;

    Emitter(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        IntStream.generate(() -> (new Random()).nextInt(100))
                .limit(10)
                .forEach((num) -> {
                        System.out.println("adding to queue: "+ num);
                        queue.add(num);
                });
    }
}

class Receiver implements Runnable {
    BlockingQueue<Integer> queue;

    Receiver(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                System.out.println("taking out from queue: " + queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


public class PriorityBlockingQueueDemo {

    public static void main(String[] args) throws InterruptedException {

        BlockingQueue<Integer> queue = new PriorityBlockingQueue<>();

        ExecutorService executorService = Executors.newCachedThreadPool();

        executorService.submit(new Thread(new Emitter(queue)));
        executorService.submit(new Thread(new Emitter(queue)));
        executorService.submit(new Thread(new Receiver(queue)));

        executorService.shutdown();

        try {
            if (!executorService.awaitTermination(3, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }

    }
}
