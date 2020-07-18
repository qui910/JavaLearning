package com.prd.io;

import java.io.*;

/**
 * 对象操作流测试
 */
public class ObjectInOrOutStream {

    private static final String TMP_FILE = "box.tmp";

    private static class User implements Serializable{

        private static final long serialVersionUID = 6697098950755727916L;

        long id;
        String name;

        public User(long id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }

        private void writeObject(ObjectOutputStream out)
                throws IOException{
            System.out.println("开始序列化");
            // 使定制的writeObject()方法可以利用自动序列化中内置的逻辑。
            out.defaultWriteObject();
            out.writeLong(id+1000);
        }

        private void readObject(ObjectInputStream in)
                throws IOException, ClassNotFoundException {
            System.out.println("开始反序列化");
            in.defaultReadObject();
            id=in.readLong();
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        testWriteObject();
        testReadObject();
    }

    private static void testWriteObject() throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(TMP_FILE));
        out.writeBoolean(false);
        out.writeBytes("abcd");
        out.writeChar('e');
        out.writeChars("fgh");
        out.writeDouble(1.3);
        out.writeLong(1233423);
        out.writeObject(new User(1,"prd"));
        out.close();
    }

    private static void testReadObject() throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(TMP_FILE));
        System.out.println("1:"+in.readBoolean());
        System.out.println("2:"+new String(new byte[]{in.readByte()}));
        System.out.println("2:"+new String(new byte[]{in.readByte()}));
        System.out.println("2:"+new String(new byte[]{in.readByte()}));
        System.out.println("2:"+new String(new byte[]{in.readByte()}));
        System.out.println("3:"+in.readChar());
        System.out.println("3:"+in.readChar());
        System.out.println("3:"+in.readChar());
        System.out.println("3:"+in.readChar());
        System.out.println("4:"+in.readDouble());
        System.out.println("5:"+in.readLong());
        System.out.println("6:"+in.readObject());
        in.close();
    }
}
