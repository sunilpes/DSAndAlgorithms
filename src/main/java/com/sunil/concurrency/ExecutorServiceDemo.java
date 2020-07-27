package com.sunil.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class job implements Runnable {
    String words[] = {"This", "is", "Executor", "Service", "demo!"};

    @Override
    public void run() {
        for(String word: words) {
            System.out.println(word);
        }
    }
}

public class ExecutorServiceDemo {

    /**
     *
     * Similarities between the execute() and submit() as well:
     *
     * 1. Both submit() and execute() methods are used to submit a task to Executor framework for asynchronous execution.
     * 2. Both submit() and execute() can accept a Runnable task.
     * 3. You can access submit() and execute() from the ExecutorService interface because it also extends the Executor interface which declares the execute() method.
     *
     * Apart from the fact that submit() method can return output and execute() cannot, following are other notable differences between these two key methods of Executor framework of Java 5.
     *
     * 1. The submit() can accept both Runnable and Callable task but execute() can only accept the Runnable task.
     * 2. The submit() method is declared in ExecutorService interface while execute() method is declared in the Executor interface.
     * 3. The return type of submit() method is a Future object but return type of execute() method is void.
     */

    public static void main(String[] args) {
//        ExecutorService executorCachedThreadPool = Executors.newCachedThreadPool();
//        for (int i=0;i<5;i++) {
//            executorCachedThreadPool.submit(new job());
//        }
//        executorCachedThreadPool.shutdown();

        ExecutorService executorFixedThreadPool = Executors.newFixedThreadPool(3);
        for (int i=0;i<5;i++) {
            executorFixedThreadPool.execute(new job());
        }
        executorFixedThreadPool.shutdown();

//        ExecutorService executorSingleThread = Executors.newSingleThreadExecutor();
//        for (int i=0;i<5;i++) {
//            executorSingleThread.submit(new job());
//        }
//        executorSingleThread.shutdown();

    }
}
