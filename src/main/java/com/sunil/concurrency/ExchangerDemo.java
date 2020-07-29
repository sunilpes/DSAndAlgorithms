package com.sunil.concurrency;

import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Exchanger;

public class ExchangerDemo {

    public static void main(String[] args) {
        Exchanger<Queue<String>> readerExchanger = new Exchanger<>();
        Exchanger<Queue<String>> writerExchanger = new Exchanger<>();
        final int BUFFER_SIZE = 10;

        Runnable reader = () -> {
            Queue<String> readerBuffer = new ConcurrentLinkedQueue<>();
            while(true) {
                readerBuffer.add(UUID.randomUUID().toString());
                if (readerBuffer.size() >= BUFFER_SIZE) {
                    try {
                        readerBuffer = readerExchanger.exchange(readerBuffer);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        Runnable processor = () -> {
            Queue<String> processorBuffer = new ConcurrentLinkedQueue<>();
            Queue<String> writerBuffer = new ConcurrentLinkedQueue<>();
            try {
                processorBuffer = readerExchanger.exchange(processorBuffer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            while(true) {
                writerBuffer.add("processed: " + processorBuffer.poll());
                if(processorBuffer.isEmpty()) {
                    try {
                        writerBuffer = writerExchanger.exchange(writerBuffer);
                        processorBuffer = readerExchanger.exchange(processorBuffer);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        Runnable writer = () -> {
            Queue<String> writerBuffer = new ConcurrentLinkedQueue<>();

            try {
                writerBuffer = writerExchanger.exchange(writerBuffer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while(true) {
                System.out.println(writerBuffer.poll());
                if(writerBuffer.isEmpty()) {
                    try {
                        writerBuffer = writerExchanger.exchange(writerBuffer);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        CompletableFuture.allOf(
                CompletableFuture.runAsync(reader),
                CompletableFuture.runAsync(processor),
                CompletableFuture.runAsync(writer)
        ).join();
    }
}
