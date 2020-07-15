package com.prd.jvm;

/**
 * 演示栈空间溢出
 * -verbose:gc -Xms10M -Xmx10M -Xss128k -XX:+PrintGCDetails
 * Stack栈，是线程私有的
 */
public class HelloStackOverFlow {
    private int nums;

    public void count() {
        nums++;
        count();
    }

    public static void main(String[] args) {
        System.out.println("HelloStackOverFlow");
        HelloStackOverFlow flow = new HelloStackOverFlow();
        flow.count();
    }
}
