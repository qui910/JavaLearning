package com.prd.colletctions;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class QueueTest {

  private static Integer[] testPriority = {3,6,9,4,8,2,1};
//  private static Integer[] testPriority = {1,2,3,4,5,6,7,8,9};

  public static void main(String[] args) {
    log.info("size:{},{},{}",127>>>1,127>>1,0<<1);

//    int[] ints = {1,2,3,4,5,6,7,8};
//    int i = 0;
//    // 先赋值，后使用
//    log.info("ints[i=1]:{}",ints[i=1]);
//    log.info("i:{}",i);
//
//    // 先使用，后赋值
//    log.info("ints[i++]:{}",ints[i++]);
//    log.info("i:{}",i);
//
//    // 先赋值，后使用
//    log.info("ints[++i]:{}",ints[++i]);
//    log.info("i:{}",i);

    // 测试heap排序算法
    List<Integer> testList = Arrays.asList(testPriority);
    PriorityQueue<Integer> testIntegers = new PriorityQueue<>(testList);
    log.info("初始化后的队列为:{}",testIntegers);

    testIntegers.offer(13);
    log.info("添加后的队列为:{}",testIntegers);
    testIntegers.offer(21);
    log.info("添加后的队列为:{}",testIntegers);
    testIntegers.poll();
    log.info("删除头后的队列为:{}",testIntegers);
  }



}

