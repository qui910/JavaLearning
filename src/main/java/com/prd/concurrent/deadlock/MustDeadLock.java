package com.prd.concurrent.deadlock;

/**
 * 必定发送死锁的情况
 */
public class MustDeadLock implements Runnable{

    int flag =1;

    static Object obj1 = new Object();
    static Object obj2 = new Object();

    @Override
    public void run() {
        System.out.println("flag="+flag);
        if (flag==1) {
            synchronized (obj1) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (obj2){

                }
            }
        }
        if (flag==2) {
            synchronized (obj2) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (obj1){

                }
            }
        }
    }

    public static void main(String[] args) {
        MustDeadLock lock1 = new MustDeadLock();
        MustDeadLock lock2 = new MustDeadLock();
        lock1.flag=1;
        lock2.flag=2;
        Thread t1 = new Thread(lock1);
        Thread t2 = new Thread(lock2);
        t1.start();
        t2.start();
    }
}
