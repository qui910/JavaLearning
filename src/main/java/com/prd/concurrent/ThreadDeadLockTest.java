package com.prd.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * 线程死锁测试
 */
public class ThreadDeadLockTest {

  public static void main(String[] args) {
      Object resoureA = new Object();
      Object resoureB = new Object();

      new Thread(()->{
          synchronized (resoureA) {
              System.out.println(Thread.currentThread().getName()+" get resoureA");
              try {
                  TimeUnit.SECONDS.sleep(1);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }

              synchronized (resoureB){
                  System.out.println(Thread.currentThread().getName()+" get resoureB");
              }
          }
      }).start();

      new Thread(()->{
          synchronized (resoureB) {
              System.out.println(Thread.currentThread().getName()+" get resoureB");
              try {
                  TimeUnit.SECONDS.sleep(1);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }

              synchronized (resoureA){
                  System.out.println(Thread.currentThread().getName()+" get resoureA");
              }
          }
      }).start();
  }
}
