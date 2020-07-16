package com.prd.concurrent.juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaPhoreTest {

    private static final Semaphore phe = new Semaphore(10);

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(3);
        es.execute(new MyThread("A",5));
        es.execute(new MyThread("B",4));
        es.execute(new MyThread("C",9));
        es.shutdown();
    }

    private static class MyThread extends Thread {

        private int count;

        public MyThread(String name, int count) {
            super(name);
            this.count = count;
        }

        @Override
        public void run() {
            try {
                phe.acquire(count);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程"+getName()+"获得"+count+"信号量");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally{
                phe.release(count);
                System.out.println("线程"+getName()+"释放"+count+"信号量");
            }
        }
    }
}
