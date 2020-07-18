package com.prd.io;

import org.junit.Test;

import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileDescriptorTest {

    public static final String FILE_NAME = "test.txt";

    /**
     * 证明了System.out,就是使用FileDescriptor.out来创建的
     * 二者的信息都在控制台输出
     */
    @Test
    public void testSystemOutBuildByOut() throws IOException {
        FileOutputStream out = new FileOutputStream(FileDescriptor.out);
        out.write("测试FileDescriptor.out输出\n".getBytes());
        System.out.println("测试System.out输出");
    }

    /**
     * 验证对同一文件建立的两个不同的流，其fd值是不同的
     */
    @Test
    public void testFileFFDNum() throws IOException {
        FileOutputStream fos1 = new FileOutputStream(FILE_NAME);
        FileOutputStream fos2 = new FileOutputStream(FILE_NAME);
        System.out.println("fos1:"+fos1.getFD());
        System.out.println("fos2:"+fos2.getFD());
    }

    /**
     * 验证"通过文件名创建FileOutputStream"与“通过文件描述符创建FileOutputStream”对象是等效的
     * 该程序会在“该源文件”所在目录新建文件"file.txt"，并且文件内容是"Aa"。
     * 并且流关闭后，FileDescriptor不可用了
     */
    @Test
    public void testConstructor() throws IOException {
        // 新建文件“file.txt”对应的FileOutputStream对象
        FileOutputStream out1 = new FileOutputStream(FILE_NAME);
        // 获取文件“file.txt”对应的“文件描述符”
        FileDescriptor fdout = out1.getFD();
        // 根据“文件描述符”创建“FileOutputStream”对象
        FileOutputStream out2 = new FileOutputStream(fdout);

        out1.write('A');    // 通过out1向“file.txt”中写入'A'
        out2.write('a');    // 通过out2向“file.txt”中写入'a'

        if (fdout!=null)
            System.out.printf("关闭前fdout(%s) is %s\n",fdout, fdout.valid());

        out1.close();
        out2.close();

        if (fdout!=null)
            System.out.printf("关闭后fdout(%s) is %s\n",fdout, fdout.valid());

    }
}
