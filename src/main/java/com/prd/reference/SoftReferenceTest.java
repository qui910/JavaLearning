package com.prd.reference;

import lombok.extern.slf4j.Slf4j;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;

/**
 * 软引用测试
 * 运行参数 -Xmx200m -XX:+PrintGC
 * TODO 运行异常 ？？？？？？？？？
 */
@Slf4j
public class SoftReferenceTest {
  public static void main(String[] args) throws InterruptedException {
      //100M的缓存数据
      byte[] cacheData = new byte[100 * 1024 * 1024];
      ReferenceQueue reQueue = new ReferenceQueue();
      //将缓存数据用软引用持有
      SoftReference<byte[]> cacheRef = new SoftReference<>(cacheData,reQueue);
      //将缓存数据的强引用去除
      cacheData = null;
      log.info("第一次GC前{}",cacheData);
      log.info("第一次GC前{}",cacheRef.get());
      log.info("第一次GC前{}",reQueue.poll());
      //进行一次GC后查看对象的回收情况
      System.gc();
      //等待GC
      Thread.sleep(1000);
      log.info("第一次GC后{}",cacheData);
      log.info("第一次GC后{}",cacheRef.get());
      log.info("第一次GC后{}",reQueue.poll());

      //在分配一个120M的对象，看看缓存对象的回收情况
      byte[] newData = new byte[120 * 1024 * 1024];
      log.info("第二次GC后{}",cacheData);
      log.info("第二次GC后{}",cacheRef.get());
      log.info("第一次GC后{}",reQueue.poll());

  }
}
