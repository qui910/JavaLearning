package com.prd.concurrent.base;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程中断状态测试
 */
public class ThreadInterruptedTest {

    public static void main(String[] args) {
        //runnableTest();

        //waitingTest();

        //blockedTest();

        blockedTest1();
    }


    /**
     * 就绪状态测试
     */
    private static void runnableTest() {
        Thread targetTh = new Thread(()->{
            for (int i=0; i<10000 ; i++) {
                System.out.println("i:"+i);
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("线程中断");
                    break;
                }
            }
        });
        targetTh.start();
        try {
            TimeUnit.MICROSECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程状态:"+targetTh.getState().name());
        targetTh.interrupt();
    }

    /**
     * 等待状态或超时等待测试
     */
    private static void waitingTest(){
        Object obj = new Object();
        Thread targetTh = new Thread(()->{
            synchronized (obj) {
                try {
                    obj.wait();
                } catch (InterruptedException e) {
                    System.out.println("线程中断状态被清除为:"+Thread.currentThread().isInterrupted());
                    Thread.currentThread().interrupt();
                    System.out.println("线程中断状态重置为:"+Thread.currentThread().isInterrupted());
                } finally {
                    // 添加死循环，以便外部检查线程中断状态重置后的值
                    for (;;){
                    }
                }
            }
        });
        targetTh.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程状态:"+targetTh.getState().name());
        targetTh.interrupt();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 线程外部检查线程中断状态时，线程不能为终止状态（即线程不能执行完成）否则会返回false
        System.out.println("线程targetTh中断状态为:"+targetTh.isInterrupted());
    }


    /**
     * 阻塞状态synchronized
     */
    private static void blockedTest() {
        Object obj = new Object();
        Thread thread1 = new Thread(()->{
            synchronized (obj) {
                for(;;);
            }
        });
        Thread targetTh = new Thread(()->{
            synchronized (obj) {
                for(;;);
            }
        });
        thread1.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        targetTh.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程thread1状态:"+thread1.getState().name());
        System.out.println("线程targetTh状态:"+targetTh.getState().name());
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        targetTh.interrupt();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 线程状态被重置为true，但是因等待同步锁，线程还是阻塞状态.
        System.out.println("线程targetTh中断状态为:"+targetTh.isInterrupted());
        System.out.println("线程targetTh状态:"+targetTh.getState().name());
    }

    /**
     * 阻塞状态lock
     */
    private static void blockedTest1() {
        Object obj = new Object();
        Lock lock = new ReentrantLock();
        Thread thread1 = new Thread(()->{
            lock.lock();
            for(;;);
        });
        Thread targetTh = new Thread(()->{
            try {
                lock.lockInterruptibly();
            } catch (InterruptedException e) {
                System.out.println("线程内部中断状态:"+Thread.currentThread().isInterrupted());
            }
            for(;;);
        });
        thread1.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        targetTh.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程thread1状态:"+thread1.getState().name());
        System.out.println("线程targetTh状态:"+targetTh.getState().name());
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        targetTh.interrupt();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程targetTh中断状态为:"+targetTh.isInterrupted());
        System.out.println("线程targetTh状态:"+targetTh.getState().name());
    }
}
