package com.prd.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * 线程等待JOIN测试
 */
public class ThreadJoinTest {
  public static void main(String[] args) throws InterruptedException {
      ThreadB threadB = new ThreadB();
      ThreadA threadA = new ThreadA(threadB);
      threadA.start();
      System.out.println("线程A在JOIN前的状态："+threadA.getState().name());
      TimeUnit.SECONDS.sleep(3);
      System.out.println("线程A在JOIN中的状态："+threadA.getState().name());
  }
}

class ThreadA extends Thread {

    public ThreadB threadB;

    public ThreadA(ThreadB threadB) {
        this.threadB = threadB;
    }

    @Override
    public void run() {
        threadB.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程B在JOIN前的状态："+threadB.getState().name());
        try {
            threadB.join(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程A在JOIN后的状态："+getState().name());
        System.out.println("线程B在JOIN后的状态："+threadB.getState().name());
    }
}

class ThreadB extends Thread {
    @Override
    public void run() {
        for(;;) {

        }
    }
}