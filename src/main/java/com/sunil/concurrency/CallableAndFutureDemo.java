package com.sunil.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

class Processor implements Callable<String> {
    int id;

    public Processor(int id) {
        this.id = id;
    }

    @Override
    public String call() throws Exception {
        Thread.sleep(500);
        return "ID: " + id;
    }
}


public class CallableAndFutureDemo {

    public static void main(String[] args) {
        List<Future<String>> futures = new ArrayList<Future<String>>();

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        for(int i=0;i<5;i++) {
            Future<String> future = executorService.submit(new Processor(i+1));
            futures.add(future);
        }

        futures.forEach((future) -> {
            try {
                System.out.println(future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
        executorService.shutdown();
    }
}
