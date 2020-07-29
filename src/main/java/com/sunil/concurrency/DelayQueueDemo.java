package com.sunil.concurrency;

import java.util.UUID;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

class DelayedObject implements Delayed {
    public String data;
    private long startTime;

    public DelayedObject(String data, long delayInMilliseconds) {
        this.data = data;
        this.startTime = System.currentTimeMillis() + delayInMilliseconds;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        long diff = startTime - System.currentTimeMillis();
        return unit.convert(diff, TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        return Math.toIntExact(startTime - ((DelayedObject) o).startTime);
    }
}

class DelayQueueProducer implements Runnable {
    private int noOfElementsToProduce;
    private long delayOfEachProducedMessageMilliseconds;
    private BlockingQueue<DelayedObject> queue;

    DelayQueueProducer(int noOfElementsToProduce, long delayOfEachProducedMessageMilliseconds, BlockingQueue<DelayedObject> queue) {
        this.noOfElementsToProduce = noOfElementsToProduce;
        this.delayOfEachProducedMessageMilliseconds = delayOfEachProducedMessageMilliseconds;
        this.queue = queue;
    }

    @Override
    public void run() {
        IntStream
                .range(0, noOfElementsToProduce).forEach((elem) -> {
            DelayedObject obj = new DelayedObject(UUID.randomUUID().toString(), delayOfEachProducedMessageMilliseconds);
            try {
                queue.put(obj);
                System.out.println("Put object: " + obj.data);
                //Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}

class DelayQueueConsumer implements Runnable {
    private int noOfElementsToConsume;
    public AtomicInteger counter = new AtomicInteger();
    BlockingQueue<DelayedObject> queue;

    DelayQueueConsumer(int noOfElementsToConsume, BlockingQueue<DelayedObject> queue) {
        this.noOfElementsToConsume = noOfElementsToConsume;
        this.queue = queue;
    }

    @Override
    public void run() {
        IntStream.range(0, noOfElementsToConsume)
                .forEach((elem) -> {
                    try {
                        DelayedObject obj = queue.take();
                        System.out.println("take object " + obj.data);
                        counter.incrementAndGet();
                        //Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });

    }
}

public class DelayQueueDemo {


    public static void main(String[] args) {

         int noOfElementsToProduce = 10;
         long delayOfEachProducedMessageMilliseconds = 1000;
         BlockingQueue<DelayedObject> queue = new DelayQueue<>();
         int noOfElementsToConsume = 10;

        ExecutorService executorService = Executors.newCachedThreadPool();

         Thread producer = new Thread(new DelayQueueProducer(noOfElementsToProduce, delayOfEachProducedMessageMilliseconds, queue));
         Thread consumer = new Thread(new DelayQueueConsumer(noOfElementsToConsume, queue));

        executorService.submit(producer);
        executorService.submit(consumer);

        try {
            executorService.awaitTermination(50, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
    }
}
