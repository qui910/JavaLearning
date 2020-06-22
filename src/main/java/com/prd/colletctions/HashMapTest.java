package com.prd.colletctions;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * @author prd
 * @version V1.0
 * @Description TODO:功能描述
 * @date 2020-06-18 11:54
 */
@Slf4j
public class HashMapTest {

  public static void main(String[] args) throws InterruptedException {
      log.info("1<<1:{}",1<<1);
      log.info("1<<4:{}",1<<4);

      int y=8;
      if (y>3) {
          log.info("y>3");
      } else if (y>5) {
          log.info("y>5");
      } else {
          log.info("else");
      }

//      log.info("int 5的hashcode：{}",new Integer(5).hashCode());
//      log.info("int 5的hashcode的二进制：{}",Integer.toBinaryString(new Integer(5).hashCode()));
//      log.info("int 13的hashcode的二进制：{}",Integer.toBinaryString(new Integer(13).hashCode()));
//      log.info("int 29的hashcode的二进制：{}",Integer.toBinaryString(new Integer(29).hashCode()));
//      log.info("int 15的二进制：{}",Integer.toBinaryString(15));
//      log.info("int 16的二进制：{}",Integer.toBinaryString(16));
      log.info("int 15&13={}，13%16={}",15 & 13,13%16);
      log.info("int 15&29={}，29%16={}",15 & 29,29%16);
      log.info("int 16&29={},16&13={}",16&29,16&13);
      log.info("int 29/16={},13/16={}",29/16,13/16);
      log.info("int 31&13={}，13%32={}",31 & 13,13%32);
      log.info("int 31&29={}，29%32={}",31 & 29,29%32);

      HashMap<Integer,String> testMap = new HashMap<Integer,String>();
      // 测试并发写 HashMap
/*      for (int i=0;i<2000;i++) {
          String name = Thread.currentThread().getName()+i;
          final  int key = i;
          new Thread(()-> {
              testMap.put(key,name);
          }).start();
      }
      Thread.sleep(20000);
      log.info("Hashmap 的大小为{}",testMap.size());*/


      // 测试 SimpleEntr


      // 测试循环
      for (int i=0;i<10;i++) {
          testMap.put(i,i+"");
      }
      // keySet循环
/*
      Set<Integer> keysets = testMap.keySet();
      for (Integer is:keysets) {
          log.info("key {},values:{}",is,testMap.get(is));
      }
*/

/*
      Iterator iter = testMap.keySet().iterator();
      while (iter.hasNext()) {
          Integer key = (Integer) iter.next();
          log.info("key {},values:{}",key,testMap.get(key));
      }
 */

/*
      testMap.keySet().forEach(key->{
          log.info("key {},values:{}",key,testMap.get(key));
      });
*/

      // entrySet循环
/*
      Set<Map.Entry<Integer,String>> entryets = testMap.entrySet();
      for (Map.Entry entry:entryets) {
          log.info("key {},values:{}",entry.getKey(),entry.getValue());
      }
 */

/*
      Iterator iter = testMap.entrySet().iterator();
      while (iter.hasNext()) {
          Map.Entry<Integer,String> entry = (Map.Entry<Integer,String>) iter.next();
          log.info("key {},values:{}",entry.getKey(),entry.getValue());
      }
*/

/*
      testMap.entrySet().forEach(entry->{
          log.info("key {},values:{}",entry.getKey(),entry.getValue());
      });
*/

      // values循环
/*
      Collection<String> vals= testMap.values();
      for (String value:vals) {
          log.info("values:{}",value);
      }
*/
/*

      Collection<String> vals= testMap.values();
      Iterator<String> iterator = vals.iterator();
      while (iterator.hasNext()) {
          log.info("values:{}",iterator.next());
      }
*/
/*

      testMap.values().forEach(value->{
          log.info("values:{}",value);
      });
*/


  }
}
