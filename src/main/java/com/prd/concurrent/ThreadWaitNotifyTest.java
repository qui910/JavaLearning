package com.prd.concurrent;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Random;

/**
 * 线程通信测试
 */
public class ThreadWaitNotifyTest {

    // 生产者
    class ThreadA extends Thread {

        private Deque<String> queue;
        private int maxSize;

        public ThreadA(Deque<String> queue, int maxSize) {
            this.queue = queue;
            this.maxSize = maxSize;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (queue) {
                  // 队列超过100个后，停止生产，进入等待
                  while (queue.size() == maxSize) {
                    try {
                        queue.notifyAll();
                        queue.wait();
                      System.out.println("Queue is Full");
                    } catch (InterruptedException e) {
                      e.printStackTrace();
                    }
                  }

                  Random random = new Random();
                  int i = random.nextInt();
                  String msg = "test" + i;
                  queue.add(msg);
                  System.out.println("生产：" + msg);
                }
            }
        }
    }

    class ThreadB extends Thread {

        private Deque<String> queue;
        private int maxSize;

        public ThreadB(Deque<String> queue, int maxSize) {
            this.queue = queue;
            this.maxSize = maxSize;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (queue) {
                  // 队列空后，停止消费，进入等待
                  while (queue.size() == 0) {
                    try {
                        queue.notifyAll();
                        queue.wait();
                      System.out.println("Queue is Empty");
                    } catch (InterruptedException e) {
                      e.printStackTrace();
                    }
                  }

                  String msg = queue.remove();
                  System.out.println("消费：" + msg);
                }
            }
        }
    }

    public static void main(String[] args) {

        LinkedList<String> linkedList = new LinkedList<String>();
        int maxSize=2;

        ThreadA threadA = new ThreadWaitNotifyTest().new ThreadA(linkedList,maxSize);
        ThreadB threadB = new ThreadWaitNotifyTest().new ThreadB(linkedList,maxSize);
        threadA.start();
        threadB.start();
    }
}
