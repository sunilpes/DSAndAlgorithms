package com.sunil.concurrency;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

class Work implements  Runnable {

    CountDownLatch readyThreadCounter;
    CountDownLatch callingThreadBlocker;
    CountDownLatch completedThreadCounter;
    List<String> outputScrapper;

    public Work(CountDownLatch readyThreadCounter, CountDownLatch callingThreadBlocker, CountDownLatch completedThreadCounter, List<String> outputScrapper) {
        this.readyThreadCounter = readyThreadCounter;
        this.callingThreadBlocker = callingThreadBlocker;
        this.completedThreadCounter = completedThreadCounter;
        this.outputScrapper = outputScrapper;
    }

    @Override
    public void run() {
        readyThreadCounter.countDown();
        try {
            System.out.println("Thread is waiting to start!");
            callingThreadBlocker.await();
            outputScrapper.add("counted down");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            completedThreadCounter.countDown();
        }
    }
}

public class CountDownLatchDemo2 {

    public static void main(String[] args) {
        CountDownLatch readyThreadCounter = new CountDownLatch(5);
        CountDownLatch callingThreadBlocker = new CountDownLatch(1);
        CountDownLatch completedThreadCounter = new CountDownLatch(5);

        List<String> outputScrapper = Collections.synchronizedList(new ArrayList<>());

        List<Thread> tasks = Stream
                .generate(() -> new Thread(new Work(readyThreadCounter, callingThreadBlocker, completedThreadCounter, outputScrapper)))
                .limit(5)
                .collect(toList());

        tasks.forEach(Thread::start);

        try {
            readyThreadCounter.await(); // wait till all threads are instantiated and ready to execute
            outputScrapper.add("All threads are ready to start.. lets go !!");
            callingThreadBlocker.countDown(); // unblock Threads
            completedThreadCounter.await(); // wait till all threads are done
            outputScrapper.add("Workers complete!!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        outputScrapper.forEach(System.out::println);
    }
}
