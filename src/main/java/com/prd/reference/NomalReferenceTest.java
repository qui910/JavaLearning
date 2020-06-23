package com.prd.reference;

import lombok.extern.slf4j.Slf4j;

/**
 * 强引用测试
 * 运行参数 -Xmx200m -XX:+PrintGC
 */
@Slf4j
public class NomalReferenceTest {

  public static void main(String[] args) throws InterruptedException {
      //150M的缓存数据
      byte[] cacheData = new byte[100 * 1024 * 1024];
      log.info("GC前，cacheData的大小:{}",cacheData.length);
      System.gc();

      Thread.sleep(1000);
      //在分配一个80M的对象，看看缓存对象的回收情况
      byte[] newData = new byte[80 * 1024 * 1024];
      log.info("GC后，cacheData的大小:{}",cacheData.length);
  }
}
