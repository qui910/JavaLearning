package com.prd.concurrent.atomic;

import java.util.concurrent.atomic.AtomicLongFieldUpdater;

/**
 * 对象属性类型修改
 */
public class AtomicLongFiledUpdaterTest {

    public static void main(String[] args){
//        testNormalClass();

        testSubClass();
    }


    /**
     * 常规测试
     * 这里要注意几点：
     * 1.Person 中的id必须时volatile的
     * 2.id 字段不能设置为private ，因为private的话，AtomicLongFiledUpdaterTest中时无法直接访问的。
     *
     */
    private static void testNormalClass() {
        AtomicLongFieldUpdater<Person> fieldUpdater = AtomicLongFieldUpdater.newUpdater(Person.class,"id");

        Person person = new Person("aaaa",111);
        System.out.println("初始值："+person.toString());
        fieldUpdater.compareAndSet(person,111,222);
        System.out.println("更新后值："+person.toString());
    }


    /**
     * 测试子类
     * 结果：
     Exception in thread "main" java.lang.RuntimeException: java.lang.NoSuchFieldException: id
     at java.util.concurrent.atomic.AtomicLongFieldUpdater$CASUpdater.<init>(AtomicLongFieldUpdater.java:401)
     at java.util.concurrent.atomic.AtomicLongFieldUpdater.newUpdater(AtomicLongFieldUpdater.java:89)
     at com.prd.concurrent.atomic.AtomicLongFiledUpdaterTest.testSubClass(AtomicLongFiledUpdaterTest.java:35)
     at com.prd.concurrent.atomic.AtomicLongFiledUpdaterTest.main(AtomicLongFiledUpdaterTest.java:13)
     Caused by: java.lang.NoSuchFieldException: id
     at java.lang.Class.getDeclaredField(Class.java:2070)
     at java.util.concurrent.atomic.AtomicLongFieldUpdater$CASUpdater$1.run(AtomicLongFieldUpdater.java:388)
     at java.util.concurrent.atomic.AtomicLongFieldUpdater$CASUpdater$1.run(AtomicLongFieldUpdater.java:386)
     at java.security.AccessController.doPrivileged(Native Method)
     at java.util.concurrent.atomic.AtomicLongFieldUpdater$CASUpdater.<init>(AtomicLongFieldUpdater.java:385)
     ... 3 more

     结论：
     这里虽然Student是可以直接访问Person中的id，但是在CAS中无法反射到id属性
     */
    private static void testSubClass() {
        AtomicLongFieldUpdater<Student> fieldUpdater = AtomicLongFieldUpdater.newUpdater(Student.class,"id");

        Student person = new Student("aaaa",111);
        System.out.println("初始值："+person.toString());
        fieldUpdater.compareAndSet(person,111,222);
        System.out.println("更新后值："+person.toString());
    }
}

class Person {
    volatile long id;

    String name;

    public Person(String name,long id) {
        this.name = name;
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

class Student extends Person {

    public Student(String name, long id) {
        super(name, id);
    }
}