package com.prd.concurrent.base;

/**
 * 线程状态测试
 */
public class ThreadStateTest {

  public static void main(String[] args) throws InterruptedException {
      // testState1();
      testState2();
  }


    /**
     * 测试从 NEW-》RUNNABLE-》TIMED_WAITING-》RUNNABLE-》TERMINATED
     */
  private static void testState1() throws InterruptedException {
      Thread thread1 = new TestThreadState();
      thread1.start();
      Thread.sleep(1000);
      // 该状态不同于WAITING，它可以在指定的时间后自行返回。
      System.out.println("线程超时等待(TIMED_WAITING)状态：" + thread1.getState().name());

      Thread.sleep(10000);
      // 表示该线程已经执行完毕
      System.out.println("线程终止(TERMINATED)状态：" + thread1.getState().name());
  }

    /**
     * 测试从 NEW-》RUNNABLE-》WAITING-》RUNNABLE-》TERMINATED
     */
  private static void testState2() throws InterruptedException {
      Thread thread1 = new TestThreadStateA();
      thread1.start();
      Thread.sleep(1000);
      // 该状态不同于WAITING，它可以在指定的时间后自行返回。
      System.out.println("线程等待(WAITING)状态：" + thread1.getState().name());
      Thread.sleep(2000);
      thread1.interrupt();
      Thread.sleep(10000);
      // 表示该线程已经执行完毕
      System.out.println("线程终止(TERMINATED)状态：" + thread1.getState().name());
  }
}

class TestThreadState extends Thread {

    public TestThreadState() {
        System.out.println("线程编号为：："+this.getId());
        // 新创建了一个线程对象，但还没有调用start()方法。
        System.out.println("线程初始（NEW）状态："+this.getState().name());
    }

    @Override
    public void run() {
        // Java线程中将就绪（ready）和运行中（running）两种状态笼统的称为“运行”。
        // 线程对象创建后，其他线程(比如main线程）调用了该对象的start()方法。该状态的线程位于可运行线程池中，
        // 等待被线程调度选中，获取CPU的使用权，此时处于就绪状态（ready）。就绪状态的线程在获得CPU时间片后变为运行中状态（running）。
        System.out.println("线程运行(RUNNABLE)状态：" + this.getState().name());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 线程从超时等待返回后，状态变更为运行中
        System.out.println("线程运行(RUNNABLE)状态状态：" + this.getState().name());
    }
}

class TestThreadStateA extends Thread {

    public TestThreadStateA() {
        System.out.println("线程编号为：："+this.getId());
        // 新创建了一个线程对象，但还没有调用start()方法。
        System.out.println("线程初始（NEW）状态："+this.getState().name());
    }

    @Override
    public void run() {
        // Java线程中将就绪（ready）和运行中（running）两种状态笼统的称为“运行”。
        // 线程对象创建后，其他线程(比如main线程）调用了该对象的start()方法。该状态的线程位于可运行线程池中，
        // 等待被线程调度选中，获取CPU的使用权，此时处于就绪状态（ready）。就绪状态的线程在获得CPU时间片后变为运行中状态（running）。
        System.out.println("线程运行(RUNNABLE)状态：" + this.getState().name());

        try {
            synchronized (this) {
                this.wait();
            }
        } catch (InterruptedException e) {
            System.out.println("线程运行状态状态：" + this.getState().name());
        }

        // 线程从超时等待返回后，状态变更为运行中
        System.out.println("线程运行(RUNNABLE)状态状态：" + this.getState().name());
    }
}