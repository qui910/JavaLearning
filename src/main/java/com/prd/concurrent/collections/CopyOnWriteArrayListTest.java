package com.prd.concurrent.collections;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

/**
 * 写时复制List测试
 * 因为新增，删除，修改时都由lock控制，故而并发写的性能不好
 * 所以主要应该场景是遍历比较多的情况
 */
public class CopyOnWriteArrayListTest {

    private static CopyOnWriteArrayList<Integer> copy = new CopyOnWriteArrayList<>();

    static {
        for (int i=0;i<10;i++) {
            copy.add(i);
        }
    }

    public static void main(String[] args) {

//        iterableAndRemove();
        bothRemove();

    }

  /**
   * 线程A读取数据，线程B删除数据
   * 结果：
   * 遍历元素：0
   * 删除元素：2
   * 遍历元素：1
   * 遍历元素：2
   * 遍历元素：3
   * 遍历元素：4
   * 遍历元素：5
   * 遍历元素：6
   * 遍历元素：7
   *
   * 则线程A不受影响，因为线程B在删除数据时使用的是写时复制的数组，而
   * 线程A遍历时使用的是源数组的快照
   */
  private static void iterableAndRemove() {
      new Thread(
              () -> {
                  for (Integer i : copy) {
                      try {
                          TimeUnit.SECONDS.sleep(1);
                      } catch (InterruptedException e) {
                          e.printStackTrace();
                      }
                      System.out.println("遍历元素："+i);
                  }
              })
              .start();

      new Thread(()->{
          try {
              Thread.sleep(1500);
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
          copy.remove(2);
          System.out.println("删除元素："+2);
      }).start();
  }


    /**
     * 线程A和线程B 同时删除元素，但是由于使用了循环，在各自循环删除时还是会出现错误
     * 结果：
     B删除序列：0 元素:0
     A删除序列：0 元素:1
     B删除序列：1 元素:3
     A删除序列：1 元素:4
     B删除序列：2 元素:6
     A删除序列：2 元素:7
     B删除序列：3 元素:9
     Exception in thread "Thread-1" java.lang.ArrayIndexOutOfBoundsException: 4
     at java.util.concurrent.CopyOnWriteArrayList.get(CopyOnWriteArrayList.java:388)
     at java.util.concurrent.CopyOnWriteArrayList.remove(CopyOnWriteArrayList.java:495)
     at com.prd.concurrent.collections.CopyOnWriteArrayListTest.lambda$bothRemove$3(CopyOnWriteArrayListTest.java:88)
     at java.lang.Thread.run(Thread.java:748)
     Exception in thread "Thread-0" java.lang.ArrayIndexOutOfBoundsException: 3
     at java.util.concurrent.CopyOnWriteArrayList.get(CopyOnWriteArrayList.java:388)
     at java.util.concurrent.CopyOnWriteArrayList.remove(CopyOnWriteArrayList.java:495)
     at com.prd.concurrent.collections.CopyOnWriteArrayListTest.lambda$bothRemove$2(CopyOnWriteArrayListTest.java:76)
     at java.lang.Thread.run(Thread.java:748)
        分析： 在线程A和线程B 同时删除时，因为删除时加锁的，不会造成数据安全性问题。但是由于时同步删除，造成部分数据没删除到，同时迭代时i位置的元素不存在提示
     ArrayIndexOutOfBoundsException 异常。
     */
  private static void bothRemove() {
      new Thread(()->{
          for (int i=0;i<10;i++) {
              try {
                  Thread.sleep(2000);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
              int k=copy.remove(i);
              System.out.println("A删除序列："+i+" 元素:"+k);
          }
      }).start();

      new Thread(()->{
          for (int i=0;i<10;i++) {
              try {
                  Thread.sleep(1500);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
              int k=copy.remove(i);
              System.out.println("B删除序列："+i+" 元素:"+k);
          }
      }).start();
  }
}
