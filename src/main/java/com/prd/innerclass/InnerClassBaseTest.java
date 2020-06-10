package com.prd.innerclass;

import lombok.extern.slf4j.Slf4j;

/**
 * 内部类基本概念测试
 */
@Slf4j
public class InnerClassBaseTest {

    private String outName = "outClass";

    @Override
    public String toString() {
        return "InnerClassBaseTest{" +
                "outName='" + outName + '\'' +
                '}';
    }

    class InnerClass{
        private String innerName = "innerClass";

        @Override
        public String toString() {
            return "InnerClass{" +
                    "innerName='" + innerName + '\'' +
                    '}';
        }

        public void innerDo() {
            log.info("内部类属性{},外部类属性{}",innerName,outName);
        }

        public void innerDo1() {
            log.info("`OutClass.this {},this {}",InnerClassBaseTest.this,this);
        }
    }

    public void outDo() {
        InnerClass innerClass = new InnerClass();
        innerClass.innerDo();
    }

    public void outDo1() {
        InnerClass innerClass = new InnerClass();
        innerClass.innerDo1();
    }


    public static void main(String[] args) {
        // 可以访问外部类属性
        InnerClassBaseTest baseTest = new InnerClassBaseTest();
        baseTest.outDo();
        // 内部类持有外部类的引用
        baseTest.outDo1();

        // 构造
        InnerClassBaseTest.InnerClass innerobject = baseTest.new InnerClass();
    }

}
