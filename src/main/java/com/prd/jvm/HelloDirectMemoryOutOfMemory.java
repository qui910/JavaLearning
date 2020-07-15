package com.prd.jvm;

import java.nio.ByteBuffer;

/**
 * 通过NIO测试直接内存溢出
 * -verbose:gc -Xms10M -Xmx10M -XX:MaxDirectMemorySize=10M -Xss128k -XX:+PrintGCDetails
 */
public class HelloDirectMemoryOutOfMemory {
    private  static final int ONE_MB=1024*1024*1024;

    private static int count=1;

    public static void main(String[] args) {
        while(true) {
            ByteBuffer buffer = ByteBuffer.allocateDirect(ONE_MB);
            count++;
        }
    }
}
