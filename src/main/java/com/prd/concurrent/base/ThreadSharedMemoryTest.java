package com.prd.concurrent.base;

/**
 * 内存共享测试
 */
public class ThreadSharedMemoryTest {

  public static void main(String[] args) {

//      testNormal();
//      testVolatile();
      testSynchronized();
  }

    /**
     * 针对多线程情况下，使用共享变量，不做特殊限制。
     * 最后的结果：9981
     * 说明：这对共享变量产生竞争
     * @throws InterruptedException
     */
  private static void testNormal() {
      TestBean bean = new ThreadSharedMemoryTest().new TestBean();
      for (int i=0;i<9999;i++) {
          NormalThread temp = new ThreadSharedMemoryTest().new NormalThread("thread"+i,bean);
          temp.start();
      }
  }

    /**
     * 多线程情况下，将共享变量设置为volatile
     * 最后的结果：9994
     * 说明：虽然使用了volatitle关键字，但是因为run中的操作不是原子的，所以还是会出现线程不安全
     * @throws InterruptedException
     */
    private static void testVolatile() {
        TestBean bean = new ThreadSharedMemoryTest().new TestBean();
        for (int i=0;i<9999;i++) {
            VolatileThread temp = new ThreadSharedMemoryTest().new VolatileThread("thread"+i,bean);
            temp.start();
        }
    }

    /**
     * 多线程情况下，将get和set操作通过使用锁，形成原子操作
     * 最后的结果：10000
     * 线程安全
     */
    private static void testSynchronized() {
        TestBean bean = new ThreadSharedMemoryTest().new TestBean();
        for (int i=0;i<9999;i++) {
            SynchronizedThread temp = new ThreadSharedMemoryTest().new SynchronizedThread("thread"+i,bean);
            temp.start();
        }
    }


  class TestBean {
      private int value=1;

      private volatile  int value1=1;

      public int getValue() {
          return value;
      }

      public void setValue(int value) {
          this.value = value;
      }

      public int getValue1() {
          return value1;
      }

      public void setValue1(int value1) {
          this.value1 = value1;
      }
  }

  class NormalThread extends Thread {
      TestBean bean;

      public NormalThread(String name, TestBean bean) {
          super(name);
          this.bean = bean;
      }

      @Override
      public void run() {
          bean.setValue(bean.getValue()+1);
          System.out.println(getName()+"当前结果："+bean.getValue());
      }
  }

    class VolatileThread extends Thread {
        TestBean bean;

        public VolatileThread(String name, TestBean bean) {
            super(name);
            this.bean = bean;
        }

        @Override
        public void run() {
            bean.setValue1(bean.getValue1()+1);
            System.out.println(getName()+"当前结果："+bean.getValue1());
        }
    }

    class SynchronizedThread extends Thread {
        TestBean bean;

        public SynchronizedThread(String name, TestBean bean) {
            super(name);
            this.bean = bean;
        }

        @Override
        public void run() {
            synchronized (bean) {
                bean.setValue1(bean.getValue1() + 1);
                System.out.println(getName() + "当前结果：" + bean.getValue1());
            }
        }
    }
}
