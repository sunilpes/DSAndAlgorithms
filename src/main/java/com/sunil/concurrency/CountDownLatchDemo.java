package com.sunil.concurrency;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

class Task implements Runnable {
    CountDownLatch latch;
    List<String> outputScrapper;

    public Task(CountDownLatch latch, List<String> outputScrapper) {
        this.latch = latch;
        this.outputScrapper = outputScrapper;
    }

    @Override
    public void run() {
        outputScrapper.add("counter down");
        latch.countDown();
    }
}

public class CountDownLatchDemo {

    public static void main(String[] args) {

        CountDownLatch latch = new CountDownLatch(5);
        List<String> outputScrapper = Collections.synchronizedList(new ArrayList<>());

        List<Thread> tasks = Stream
                .generate(() -> new Thread(new Task(latch, outputScrapper)))
                .limit(5)
                .collect(toList());

        tasks.forEach(Thread::start);
        try {
            latch.await();
            outputScrapper.add("latch released!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        outputScrapper.forEach(System.out::println);
    }
}
