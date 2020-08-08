package com.sunil.concurrency.diningPhilosopher;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DiningTable {

    public static void main(String[] args) throws InterruptedException {
        ChopStick[] chopSticks = new ChopStick[Constants.NUM_OF_CHOPSTICKS];
        Philosopher[] philosophers = new Philosopher[Constants.NUM_OF_PHILOSOPHERS];
        String[] philosopher_names = { "Plato", "Aristotle", "Socrates", "Confucius", "Karl Marx" };
        ExecutorService executorService = null;
        try {

            for(int i = 0; i < Constants.NUM_OF_CHOPSTICKS; i++) {
                chopSticks[i] = new ChopStick(i);
            }

            executorService = Executors.newFixedThreadPool(Constants.NUM_OF_PHILOSOPHERS);

            for(int j = 0; j< Constants.NUM_OF_PHILOSOPHERS; j++) {
                philosophers[j] = new Philosopher(philosopher_names[j], chopSticks[j], chopSticks[ (j+1) % Constants.NUM_OF_CHOPSTICKS]);
                executorService.submit(philosophers[j]);
            }

            Thread.sleep(Constants.NUM_OF_SIMULATIONS);

            for(Philosopher p: philosophers) {
                p.setFull(true);
            }

        } finally {
            executorService.shutdown();

            while(!executorService.isTerminated()) {
                Thread.sleep(1000);
            }

            for(Philosopher p: philosophers) {
                System.out.println(p + " eats:  " + p.eatingCounter.get());
            }
        }
    }

}
