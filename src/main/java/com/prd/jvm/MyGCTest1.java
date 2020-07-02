package com.prd.jvm;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 具有基于 Map 的内存泄漏的程序
 * -XX:+PrintGC -Xmx200m
 */
public class MyGCTest1 {

    public ExecutorService exec = Executors.newFixedThreadPool(5);

    public Map<Task, TaskStatus> taskStatus
            = Collections.synchronizedMap(new HashMap<Task, TaskStatus>());

    private Random random = new Random();

    private enum TaskStatus { NOT_STARTED, STARTED, FINISHED };

    private class Task implements Runnable {

        private int[] numbers = new int[random.nextInt(200)];

        @Override
        public void run() {
            int[] temp = new int[random.nextInt(10000)];
            taskStatus.put(this, TaskStatus.STARTED);
            doSomeWork();
            taskStatus.put(this, TaskStatus.FINISHED);
        }

        private void doSomeWork() {
            try {
                TimeUnit.SECONDS.sleep(60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Task newTask() {
        Task t = new Task();
        taskStatus.put(t, TaskStatus.NOT_STARTED);
        exec.execute(t);
        return t;
    }

    public static void main(String[] args) {
        MyGCTest1 myGCTest1 = new MyGCTest1();
        for (;;) {
            myGCTest1.newTask();
        }

    }
}
