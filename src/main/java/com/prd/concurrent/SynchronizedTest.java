package com.prd.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * 同步锁测试
 */
public class SynchronizedTest {

    public static void main(String[] args) throws InterruptedException {
//        testObjectLockA();
//        testOjectLockB();
        testInterrupte();
    }

    /**
     * 测试实例锁
     * A,B,C 是同一把锁，所以每次只能执行一个
     * A 与 D 是两把锁，所以不影响，同时执行
     * A 与 E 是同步方法与非同步方法的关系，可以同时执行
     */
    private static void testObjectLockA() {
        SynchrondClass classA = new SynchrondClass();
        new Thread(()->classA.methodA()).start();
        new Thread(()->classA.methodB()).start();
        new Thread(()->classA.methodC()).start();
        new Thread(()->classA.methodD()).start();
        new Thread(()->classA.methodE()).start();
    }

    /**
     * 测试类锁
     * A 与 staticA 是两把不同的锁，可以同时执行
     * staticA 与 staticB 是同一把锁，不可以同时执行
     */
    private static void testOjectLockB() {
        SynchrondClass classA = new SynchrondClass();
        new Thread(()->classA.methodA()).start();
        new Thread(()->SynchrondClass.staticMethodA()).start();
        new Thread(()->SynchrondClass.staticMethodB()).start();
    }


    /**
     * 测试阻塞状态是否响应中断
     */
    private static void testInterrupte() throws InterruptedException {
        Object obj = new Object();
        Thread threadA = new Thread(()->{
            synchronized (obj) {
                System.out.println("testInterrupte threadA has lock");
                while(true) {

                }
            }
        });
        threadA.start();
        TimeUnit.SECONDS.sleep(4);
        Thread threadB = new Thread(()->{
            synchronized (obj) {
                System.out.println("testInterrupte threadB has lock");
                while(true) {

                }
            }
        });
        threadB.start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("ThreadA 中断前状态："+threadA.getState().name());
        System.out.println("ThreadB 中断前状态："+threadB.getState().name());
        threadB.interrupt();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("ThreadB 中断后状态："+threadB.getState().name());
    }
}

class SynchrondClass {

    /**
     * 实例同步方法A
     */
    public synchronized void methodA() {
        for (int i=0;i<10;i++) {
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("synchronized void methodA "+i);
        }
    }

    /**
     * 实例同步方法B
     */
    public synchronized void methodB() {
        for (int i=0;i<10;i++) {
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("synchronized void methodB "+i);
        }
    }

    /**
     * 实例同步块C
     */
    public void methodC() {
        // 这里要想使块与方法A，B为同一实例，要使用this，表明是使用的当前对象的内置锁
        synchronized (this) {
            for (int i=0;i<10;i++) {
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("synchronized void methodC "+i);
            }
        }
    }

    /**
     * 实例同步块D
     */
    public void methodD() {
        // 这里使用的时另外的对象实例，与A，B，C方法不是同一个锁，可以同时访问
        Object obj = new Object();
        synchronized (obj) {
            for (int i=0;i<10;i++) {
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("synchronized void methodD "+i);
            }
        }
    }

    /**
     * 实例非同步块E
     */
    public void methodE() {
        for (int i=0;i<10;i++) {
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("synchronized void methodE "+i);
        }
    }

    /**
     * 静态方法
     */
    public static synchronized void staticMethodA() {
        for (int i=0;i<10;i++) {
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("synchronized void staticMethodA "+i);
        }
    }

    /**
     * 静态块
     */
    public static void staticMethodB() {
        synchronized (SynchrondClass.class) {
            for (int i=0;i<10;i++) {
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("synchronized void staticMethodB "+i);
            }
        }
    }
}