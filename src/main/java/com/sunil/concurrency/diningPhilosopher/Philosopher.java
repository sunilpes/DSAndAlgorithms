package com.sunil.concurrency.diningPhilosopher;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Philosopher implements Runnable {
    String name = "";
    ChopStick leftChopStick;
    ChopStick rightChopStick;
    private volatile boolean isFull = false;
    public AtomicInteger eatingCounter = new AtomicInteger();

    public Philosopher(String name, ChopStick leftChopStick, ChopStick rightChopStick) {
        this.name = name;
        this.leftChopStick = leftChopStick;
        this.rightChopStick = rightChopStick;
    }

    @Override
    public void run() {
        while(!isFull) {
            think();
            if (leftChopStick.pickUp(this, Stick.LEFT)) {
                if(rightChopStick.pickUp(this, Stick.RIGHT)) {
                    eat();
                    rightChopStick.drop(this, Stick.RIGHT);
                }
                leftChopStick.drop(this, Stick.LEFT);
            }

        }
    }

    public void setFull(boolean flag) {
        this.isFull = flag;
    }

    public void eat() {
        try {
            System.out.println(this + " is eating... !");
            eatingCounter.incrementAndGet();
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void think() {
        try {
            System.out.println(this + " is thinking...!");
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Philosopher: " + name;
    }
}
