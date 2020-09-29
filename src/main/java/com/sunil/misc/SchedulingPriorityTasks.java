package com.sunil.misc;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * https://www.geeksforgeeks.org/scheduling-priority-tasks-limited-time-minimizing-loss/
 */


class Task implements Comparable<Task> {
    int arrival=0;
    int units=0;
    int priority=0;
    String taskName;

    public Task(String taskName, int arrival, int units, int priority) {
        this.arrival = arrival;
        this.units = units;
        this.priority= priority;
        this.taskName=taskName;
    }


    @Override
    public int compareTo(Task task) {
        return Integer.compare(task.priority, priority);
    }

    public void setUnits(int units) {
        this.units = units;
    }
}

class SortByArrival implements Comparator<Task> {

    @Override
    public int compare(Task task1, Task task2) {
        return Integer.compare(task1.arrival, task2.arrival);
    }
}

public class SchedulingPriorityTasks {

    public static void schedule(List<Task> tasks, int TOTAL_TIME_UNIT) {
        PriorityQueue<Task> pqueue = new PriorityQueue<>(tasks);
        for(int i = 1; i <= TOTAL_TIME_UNIT; i++) {
            Task task = pqueue.poll();
            if (task != null) {
                if (task.units > 1) {
                    task.setUnits(task.units - 1);
                    pqueue.add(task);
                }
                System.out.println("executing Task :" + task.taskName);
            }
        }

        if(pqueue.isEmpty()) {
            System.out.println("remaining tasks : 0");
        } else {
            while (!pqueue.isEmpty()) {
                Task task = pqueue.poll();
                System.out.println("remaining tasks :" + (task.units * task.priority));
            }
        }
    }


    public static void main(String[] args) {
        int TOTAL_TIME_UNIT = 2;

        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Task1",1, 2, 300));
        tasks.add(new Task("Task2", 2, 2, 100));

        tasks.sort(new SortByArrival());
        schedule(tasks, TOTAL_TIME_UNIT);

    }
}
