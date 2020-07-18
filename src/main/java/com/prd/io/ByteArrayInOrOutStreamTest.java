package com.prd.io;

import java.io.*;

/**
 * 测试字节数组操作流
 */
public class ByteArrayInOrOutStreamTest {

    public static void main(String[] args) {

        // 对应英文字母“abcddefghijklmnopqrsttuvwxyz”
        byte[] arrayLetters = {
                0x61, 0x62, 0x63, 0x64, 0x65, 0x66, 0x67, 0x68, 0x69, 0x6A, 0x6B, 0x6C, 0x6D, 0x6E, 0x6F,
                0x70, 0x71, 0x72, 0x73, 0x74, 0x75, 0x76, 0x77, 0x78, 0x79, 0x7A
        };

        try(
           ByteArrayInputStream in = new ByteArrayInputStream(arrayLetters);
        ){
            int ch = 0;
            while((ch=in.read())!=-1){
                System.out.println("输入数据："+new String(new byte[]{(byte) ch}));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try(
           ByteArrayOutputStream out = new ByteArrayOutputStream(10);
        ){
            out.write("我".getBytes("UTF-8"));
            out.write("是你".getBytes("GBK"));
            out.write("中国人".getBytes());
            // 将ArrayLetters数组中从“3”开始的后5个字节写入到baos中。
            // 即对应写入“0x64, 0x65, 0x66, 0x67, 0x68”，即“defgh”
            out.write(arrayLetters, 3, 5);
            System.out.println("输出数据(默认):"+out.toString());
            System.out.println("输出数据（GBK）:"+out.toString("GBK"));
            System.out.println("输出数据（UTF-8）:"+out.toString("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
