package com.prd.io;

import java.io.*;
import java.util.zip.ZipInputStream;

public class TestIOBase {

    public static void main(String[] args) throws InterruptedException, IOException {
        testBlocking();

    }

    /**
     * 演示在read方法处，如果数据未准备好，是会造成阻塞的。这里的阻塞不是指线程状态。
     * write方法同理
     * @throws InterruptedException
     * @throws IOException
     */
    private static void testBlocking() throws InterruptedException, IOException {
        Thread test = new Thread(()->{
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("请输入一个字符，按 q 键结束");
            char c=0;
            do {
                try {
                    c = (char) bufferedReader.read();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("你输入的字符为"+c);
            } while (c != 'q');
        });
        test.start();
        Thread.sleep(1000);
        System.out.println("在未输入时，线程test的状态" + test.getState().name());
    }

    /**
     * 测试写入超过255的int时，输出结果
     */
    private static void testWriteInt() {

    }
}
