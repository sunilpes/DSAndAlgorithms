package com.sunil.concurrency;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

class BrokenWorker implements Runnable {

    CountDownLatch latch;
    List<String> outputScrapper;

    public BrokenWorker(CountDownLatch latch, List<String> outputScrapper) {
        this.latch = latch;
        this.outputScrapper = outputScrapper;
    }

    @Override
    public void run() {
        if(true) {
            throw new RuntimeException("Oops!!! this thread is broken!");
        }
        latch.countDown();
        outputScrapper.add("counter down!");
    }
}

public class CountDownLatchWithBrokenThread {

    public static void main(String[] args) {

        CountDownLatch latch = new CountDownLatch(5);
        List<String> outputScrapper = Collections.synchronizedList(new ArrayList<>());

        List<Thread> tasks = Stream
                .generate(() -> new Thread(new BrokenWorker(latch, outputScrapper)))
                .limit(5)
                .collect(toList());

        tasks.forEach(Thread::start);
        try {
            latch.await(3L, TimeUnit.SECONDS);
            outputScrapper.add("latch released!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        outputScrapper.forEach(System.out::println);
    }
}
