package com.prd.colletctions;

import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * 弱引用HashMap测试
 * -XX:+PrintGC
 */
@Slf4j
public class WeakHashMapTest {
  public static void main(String[] args) throws InterruptedException {

      // 初始化3个“弱键”
      String w1 = new String("one");
      String w2 = new String("two");
      String w3 = new String("three");

      WeakHashMap weakHashMap = new WeakHashMap();
      weakHashMap.put(w1,"w1");
      weakHashMap.put(w2,"w2");
      weakHashMap.put(w3,"w3");
      log.info("WeakHashMap size：{}",weakHashMap.size());

      // 将w1设置null。
      // 这意味着“弱键”w1再没有被其它对象引用，调用gc时会回收WeakHashMap中与“w1”对应的键值对
      w1=null;
      Thread.sleep(1000);
      System.gc();

      // 在GC后进行诸如迭代等操作后，执行size方法就会调用expungeStaleEntries(); 进行清除弱建。
//      Iterator iter = weakHashMap.entrySet().iterator();
//      while (iter.hasNext()) {
//          Map.Entry en = (Map.Entry)iter.next();
//          System.out.printf("next : %s - %s\n",en.getKey(),en.getValue());
//      }

      // 这里在GC后马上调用size()方法，不一定能清除到弱建，因此此时ReferenceQueue队列中还不一定存在弱建对象。
      // 等待1s后，在调用size2，则会清除弱建了。
      log.info("WeakHashMap size1：{}",weakHashMap.size());
      Thread.sleep(1000);
      log.info("WeakHashMap size2：{}",weakHashMap.size());


      // 这种添加方式，"2","3"等不是弱建，无法GC回收。只有new String("1") 才是弱建，原因：？？？
/*      WeakHashMap weakHashMap1 = new WeakHashMap();
      weakHashMap1.put(new String("1"),"w1");
      weakHashMap1.put("2","w2");
      weakHashMap1.put("3","w3");
      log.info("WeakHashMap1 size：{}",weakHashMap1.size());
      Thread.sleep(1000);
      System.gc();
      Thread.sleep(1000);
      log.info("WeakHashMap1 size：{}",weakHashMap1.size());*/
  }
}
