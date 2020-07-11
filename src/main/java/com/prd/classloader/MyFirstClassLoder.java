package com.prd.classloader;

import java.io.*;

public class MyFirstClassLoder extends ClassLoader {

    private String name;

    private final String classNameExt=".class";

    public MyFirstClassLoder(String name) {
        // 指定系统类加载器当作该类加载器的父加载器
        super();
        this.name = name;
    }

    public MyFirstClassLoder(ClassLoader parent,String name) {
        // 显示指定该类加载器的父加载器
        super(parent);
        this.name = name;
    }

    private byte[] loadClassData(String name) {
        byte[] data=null;
        InputStream in = null;
        ByteArrayOutputStream bout = null;
        try {
            System.out.println("调用自定义类加载器");
            in = new FileInputStream(new File(name+classNameExt));
            int ch=0;
            while((ch=in.read())!=-1) {
                bout.write(ch);
            }
            data = bout.toByteArray();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                bout.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return data;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] data = loadClassData(name);
        return defineClass(name,data,0,data.length);
    }


    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        MyFirstClassLoder classLoder = new MyFirstClassLoder("test1");
//        Class cls= classLoder.loadClass("com.prd.classloader.ClassInitTest1");
        Class cls= classLoder.loadClass("com.prd.colletctions.ArrayListText");
        Object obj = cls.newInstance();
        System.out.println("获得对象实例："+obj);
        System.out.println("对象实例的类加载器"+obj.getClass().getClassLoader());
    }

    /**
     * 尝试加载本项目中其他类
     * 结果：自定义类加载器不起作用，实际的类加载是由AppClassLoader完成。
     * 因为这些类都在是系统项目中，通过双亲模式最终由MyFirstClassLoder的父类加载完成
     */
    private static void testLoadLocalClass() {

    }
}
