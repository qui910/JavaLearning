package com.prd.concurrent;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

/**
 * AtomicReference测试
 */
public class AtomicReferenceTest {

    public static void main(String[] args) {

//        normalString();

        beanTest();
    }

    /**
     * 常规使用测试
     */
    private static void normalString() {
        String a = new String("a");
        String b = new String("b");
        String c = new String("a");
        AtomicReference<String> reference = new AtomicReference<>(a);
        System.out.println("初始值为："+reference.get());
        // 输出为fasle，说明这里使用的引用地址比较，而非对象的equals
        System.out.println("使用c和b是否更新成功："+reference.compareAndSet(c,b));
        System.out.println("使用a和b是否更新成功："+reference.compareAndSet(a,b));
    }

    /**
     * 测试equals相等的变量，是否影响cas
     * 结果：
     初始值为：a1
     a1和c1是否相等：true
     使用c1和b1是否更新成功：false
     使用a1和b1是否更新成功：true
       分析：
     这里的cas使用的是内存中的偏移量做的判断，与equlas无关
     */
    private static void beanTest() {
        Student a1 = new AtomicReferenceTest().new Student("a1");
        Student b1 = new AtomicReferenceTest().new Student("b1");
        Student c1 = new AtomicReferenceTest().new Student("a1");

        AtomicReference<Student> reference1 = new AtomicReference<>(a1);
        System.out.println("初始值为："+reference1.get().name);
        // 输出为fasle，说明这里使用的引用地址比较，而非对象的equals
        System.out.println("a1和c1是否相等："+c1.equals(a1));
        System.out.println("使用c1和b1是否更新成功："+reference1.compareAndSet(c1,b1));
        System.out.println("使用a1和b1是否更新成功："+reference1.compareAndSet(a1,b1));
    }


    class Student {
        String name;

        public Student(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Student)) return false;
            Student student = (Student) o;
            return Objects.equals(name, student.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
    }
}
