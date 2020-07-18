package com.prd.io;

import java.io.*;

/**
 * 测试字符数组操作类
 */
public class CharArrayReaderOrWriterTest {
    public static void main(String[] args) {
        CharArrayWriter writer = null;

        char[] chars = {'我','是','a','b','c','d','e'};

        try(
                CharArrayReader in = new CharArrayReader(chars);
        ){
            int ch = 0;
            while((ch=in.read())!=-1){
                if (in.ready() == true) {
                    System.out.println("输入数据：" + new String(new char[]{(char) ch},0,1));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try(
                CharArrayWriter out = new CharArrayWriter(20);
        ){
            out.write("我".toCharArray());
            out.write("是你".toCharArray());
            out.write("中国人".toCharArray());
            System.out.println("输出数据(默认):"+out.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
