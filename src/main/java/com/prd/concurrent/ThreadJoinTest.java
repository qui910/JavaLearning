package com.prd.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * 线程等待JOIN测试
 */
public class ThreadJoinTest {

    public static void main(String[] args) throws InterruptedException {
//        testJoinNormal();
//        testJoinInterrupt();
        testJoinSameMethod();
    }

    /**
     * 测试Join的类似实现
     * 其实Join的原理，就是用线程对象的wait实现
     * 结果：
     mainThread finished
     mainThread started
     mainThread waiting subThread finished
     SubThread finished
     */
    private static void testJoinSameMethod() {
        Thread subThread = new Thread(()->{
            try {
                Thread.sleep(4000);
                System.out.println("SubThread finished");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread mainThread = new Thread(()->{
            System.out.println("mainThread started");
            subThread.start();
            System.out.println("mainThread waiting subThread finished");

            // join的同理代码
//            subThread.join();
            synchronized (subThread) {
                try {
                    subThread.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        mainThread.start();
        System.out.println("mainThread finished");
    }

    /**
     * 测试Join的中断效果
     * 注意：这里中断其实是中断主线程，而非子线程。
     * 因为是主线程等待子线程执行完成。主线程是waigting状态
     结果：
     mainThread started
     mainThread waiting subThread finished
     mainThread interrupt
     mainThread finished
     SubThread finished
     分析：
     主线中断后，子线程继续执行完成。
     */
    private static void testJoinInterrupt() {
        Thread subThread = new Thread(()->{
            try {
                Thread.sleep(5000);
                System.out.println("SubThread finished");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread mainThread = new Thread(()->{
            System.out.println("mainThread started");
            subThread.start();
            System.out.println("mainThread waiting subThread finished");
            try {
                subThread.join();
            } catch (InterruptedException e) {
                System.out.println("mainThread interrupt");
                //e.printStackTrace();
            }
        });
        mainThread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mainThread.interrupt();
        System.out.println("mainThread finished");
    }

    /**
     * join常规用法
     */
    private static void testJoinNormal() {

      class ThreadB extends Thread {
          @Override
          public void run() {
              for(;;) {

              }
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

      ThreadB threadB = new ThreadB();
      ThreadA threadA = new ThreadA(threadB);
      threadA.start();
      System.out.println("线程A在JOIN前的状态："+threadA.getState().name());
      try {
          TimeUnit.SECONDS.sleep(3);
      } catch (InterruptedException e) {
          e.printStackTrace();
      }
      System.out.println("线程A在JOIN中的状态："+threadA.getState().name());
    }

}

