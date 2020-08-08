package com.sunil.concurrency.diningPhilosopher;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

enum Stick {
    LEFT,
    RIGHT;
}

public class ChopStick {

    Lock lock = new ReentrantLock(true);
    int id = 0;

    public ChopStick(int id) {
        this.id = id;
    }

    public boolean pickUp(Philosopher philosopher, Stick stick) {
        try {
            if (lock.tryLock(5, TimeUnit.SECONDS)) {
                System.out.println(philosopher + " picked up stick "+ stick + ": " + id);
                return true;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean drop(Philosopher philosopher, Stick stick) {
        lock.unlock();
        System.out.println(philosopher + " dropped stick " + stick + ": " + id);
        return true;
    }

}
