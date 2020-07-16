package com.prd.concurrent.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *
 */
public class ReentrantReadWriteLockTest {

    public static void main(String[] args) throws InterruptedException {
        testRWOnSameCLH();
    }

    /**
     * 演示读锁和写锁使用的是同一个CLH对象
     * 且读锁和写锁不能共存，但是多个读锁可以共存
     */
    private static void testRWOnSameCLH() throws InterruptedException {
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        Lock writeLock = lock.writeLock();
        Lock readLock = lock.readLock();

    Runnable runW =
        () -> {
          writeLock.lock();
          System.out.println("线程" + Thread.currentThread().getName() + "获得锁");
          try {
            Thread.sleep(1000);
            System.out.println("CLH队列的等待线程数:"+
                    lock.getQueueLength()+",读锁:"+lock.getReadLockCount()+",写锁:"+lock.getWriteHoldCount());
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          writeLock.unlock();
          System.out.println("线程" + Thread.currentThread().getName() + "释放锁");
        };

        Runnable runR = ()->{
            readLock.lock();
            System.out.println("线程"+Thread.currentThread().getName()+"获得锁");
            try {
                Thread.sleep(1200);
                System.out.println("CLH队列的等待线程数:"+
                        lock.getQueueLength()+",读锁:"+lock.getReadLockCount()+",写锁:"+lock.getWriteHoldCount());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            readLock.unlock();
            System.out.println("线程"+Thread.currentThread().getName()+"释放锁");
        };

        new Thread(runW,"WA").start();
        Thread.sleep(100);
        new Thread(runR,"RA").start();
        Thread.sleep(100);
        new Thread(runW,"WB").start();
        Thread.sleep(100);
        new Thread(runR,"RB").start();
        new Thread(runR,"RC").start();

    }


}
