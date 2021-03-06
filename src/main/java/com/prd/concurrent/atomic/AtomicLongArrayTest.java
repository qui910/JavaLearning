package com.prd.concurrent.atomic;

import java.util.concurrent.atomic.AtomicLongArray;

/**
 * 测试原子类
 */
public class AtomicLongArrayTest {

    private static AtomicLongArray array = new AtomicLongArray(10);

    static {
        for (int i=0;i<10;i++) {
            array.set(i,(i+1)*10);
        }
    }

    public static void main(String[] args) {
//        readAndWrite();

//        bothUpdate();

        manayUpdate();
    }

    private static void readAndWrite() {
        new Thread(
                () -> {
                  for (int k = 0; k < array.length(); k++) {
                      try {
                          Thread.sleep(500);
                      } catch (InterruptedException e) {
                          e.printStackTrace();
                      }
                      System.out.println("遍历元素["+k+"]:"+array.get(k));
                  }
                }).start();
        new Thread(()->{
            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            array.set(3,110);
        }).start();
        new Thread(()->{
            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            array.set(3,120);
        }).start();
    }

    /**
     * 线程A和线程B 同时更新array数组，无法保证整体更新的完整性。
     * 结果：
     遍历元素[0]:10000
     遍历元素[1]:20000
     遍历元素[2]:30000
     遍历元素[3]:40000
     遍历元素[4]:50000
     遍历元素[5]:60000
     遍历元素[6]:70000
     遍历元素[7]:80000
     遍历元素[8]:900
     遍历元素[9]:100
       分析：所以只是单个元素的更新是原子的，整体的更新不原子
     */
    private static void bothUpdate() {
        new Thread(
            () -> {
                for (int k = 0; k < array.length(); k++) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    array.compareAndSet(k,array.get(k),array.get(k)*10);
                }
            }).start();
        new Thread(
                () -> {
                    for (int k = 0; k < array.length(); k++) {
                        try {
                            Thread.sleep(600);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        array.compareAndSet(k,array.get(k),array.get(k)*100);
                    }
                }).start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int j=0;j<array.length();j++) {
            System.out.println("遍历元素["+j+"]:"+array.get(j));
        }
    }


    /**
     * 同时开启10个线程，每个线程针对数组各自自增10000,
     * 结果：
     [10010, 10020, 10030, 10040, 10050, 10060, 10070, 10080, 10090, 10100]
       分析：结果是线程安全的
     */
    private static void manayUpdate() {

        Thread[] threads = new Thread[10];

        for (int j=0;j<10;j++) {
            Thread tmp = new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int i=0;i<10000;i++){
                        array.getAndIncrement(i%array.length());
                    }
                }
            });
            tmp.start();
            threads[j]=tmp;
        }

        for (int k=0;k<10;k++) {
            try {
                threads[k].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(array);
    }
}
