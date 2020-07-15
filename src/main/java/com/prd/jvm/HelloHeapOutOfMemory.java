package com.prd.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * 堆内存溢出样式
 * -verbose:gc -Xms10M -Xmx10M -XX:MaxDirectMemortSize=5M
 * -Xss128k -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=./heapdump.hprof
 */
public class HelloHeapOutOfMemory {

    private static class Person{}

    public static void main(String[] args) {
        System.out.println("HelloHeapOutOfMemory");
        List<Person> personList = new ArrayList();
        int nums = 0;
        while(true) {
            personList.add(new Person());
            System.out.println("构造第"+(++nums)+"个实例");
        }
    }
}
