package com.prd.colletctions;

import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

/**
 * @author prd
 * @version V1.0
 * @Description 测试LinkedHashMap
 * @date 2020-06-22 19:51
 */
@Slf4j
public class LinkedHashMapTest {
  public static void main(String[] args) {

      LinkedHashMap<Integer,String> linkedHashMap = new LinkedHashMap<>();
      for (int i=0;i<10;i++) {
          linkedHashMap.put(i,i+"");
      }
      // 插入顺序迭代
      Set<Integer> kSet= linkedHashMap.keySet();
      Iterator<Integer> it = kSet.iterator();
      while (it.hasNext()) {
         log.info("插入顺序：{}",it.next());
      }


      // 修改迭代顺序，最小使用次数排序
      LinkedHashMap<Integer,String> linkedHashMap1 = new LinkedHashMap<Integer,String>(16,1,true);
      for (int i=0;i<10;i++) {
          linkedHashMap1.put(i,i+"");
      }
      linkedHashMap1.get(2);
      linkedHashMap1.get(3);
      Set<Integer> kSet1= linkedHashMap1.keySet();
      Iterator<Integer> it1 = kSet1.iterator();
      while (it1.hasNext()) {
          log.info("最小使用次数顺序：{}",it1.next());
      }
  }
}
