package com.prd.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * 演示常量池溢出问题
 * -verbose:gc -Xms10M -Xmx10M -Xss128k -XX:+PrintGCDetails
 */
public class HelloConstantOutOfMemory {

    public static void main(String[] args) {

        List<String> stringList = new ArrayList<>();
        int item = 0;
        while (true) {
            stringList.add(String.valueOf(item++).intern());
        }
    }
}
