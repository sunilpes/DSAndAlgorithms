package com.sunil.concurrency;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrentHashMapDemo {


    public static void main(String[] args) {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();

        Runnable put = () -> {
            System.out.println("putting things into map...");
            map.put("A", "a");
            map.put("B", "b");
            map.put("C", "c");
            map.put("D", "D");
        };


        Runnable take = () -> {
            System.out.println("taking things out of map...");
            map.forEach((key, value) -> {
                System.out.println("Key: " + key + " Value: " + value);
                // concurrent modification
                map.put("K", "k");
                map.put("L", "l");
                map.put("M", "m");
            });
        };

        Thread putTask = new Thread(put);
        Thread takeTask = new Thread(take);

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(putTask);
        executorService.submit(putTask);
        executorService.submit(takeTask);

        executorService.shutdown();
    }
}
