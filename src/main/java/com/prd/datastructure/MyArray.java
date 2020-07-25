package com.prd.datastructure;

/**
 * 自定义动态数组测试
 * 数组最大的优点，快速查询。
 *
 * 假设capacity=n,n+1次addlast，触发resize，总共进行2n+1次基本操作，得出
 * 平均，每次addLast操作，进行2次基本操作，这样均摊计算，时间复杂度是O(1)
 *
 * 复杂度震荡
 * capacity=n时，addLast --O(n)，然后马上removeLast-->O(n)
 * 出问题的原因，removelast时resize过于着急（Eager）
 * 解决方案：Lazy  修改为 当 size==capacity/4时，才将capacity减半
 */
public class MyArray<E>{

        private E[] data;

        private int size;

        /**
         * 构造函数，传入数组的容量capacity构造Array
         * @param capacity
         */
        public MyArray(int capacity) {
            data = (E[]) new Object[capacity];
            size=0;
        }

        /**
         * 无参的构造函数，默认数组的容量capacity=10
         */
        public MyArray() {
            this(10);
        }

        /**
         * 获得数组中的元素个数
         * @return
         */
        public int getSize() {
            return size;
        }

        /**
         * 获取数组的容量
         * @return
         */
        public int getCapacity() {
            return  data.length;
        }

        /**
         * 返回数组是否为空
         * @return
         */
        public boolean isEmpty() {
            return size==0;
        }

        /**
         * 向所有元素后添加一个新元素
         * O(1)
         * @param e
         */
        public void addLast(E e) {
            add(size,e);
        }

        /**
         * 向所有元素前添加一个新元素
         * O(n)
         * @param e
         */
        public void addFirst(E e) {
            add(0,e);
        }

        /**
         * 获取index索引位置的元素
         * O(1)
         * @param index
         * @return
         */
        public E get(int index) {
            if (index<0||index>=size)
                throw new IllegalArgumentException("Get failed,index is illegal");
            return data[index];
        }

        public E getLast() {
            return get(size-1);
        }

        public E getFirst(){
            return get(0);
        }

        /**
         * 修改index索引位置的元素为e
         * @param index
         * @param e
         */
        public void set(int index,E e) {
            data[index] = e;
        }

        /**
         * 查找数组中是否有元素e
         * O(n)
         * @param e
         * @return
         */
        public boolean contains(E e) {
            for (int i=0;i<size;i++) {
                if (data[i].equals(e))
                    return true;
            }
            return false;
        }

        /**
         * 查找数组中元素e所在的索引，如果不存在元素e ，则返回-1
         * O(n)
         * @param e
         * @return
         */
        public int find(E e) {
            for (int i=0;i<size;i++) {
                if (data[i].equals(e))
                    return i;
            }
            return -1;
        }

        /**
         * 从数组中删除index位置的元素，返回删除的元素
         * O(n)
         * @param index
         * @return
         */
        public E remove(int index) {
            if (index < 0 ||index > size) {
                throw new IllegalArgumentException("Remove failed,Require index >=0 and ");
            }
            E ret = data[index];
            for (int i=index+1;i<size;i++) {
                data[i-1]=data[i];
            }
            size--;

            //动态扩容
//            if (size==data.length/2) {
            if (size==data.length/4 && data.length/2 !=0) {
                resize(data.length/2);
            }
            data[size]=null;
            return ret;
        }

        /**
         * 从数组中删除第一个元素，返回删除的元素
         * O(n)
         * @return
         */
        public E removeFirst() {
            return remove(0);
        }

        /**
         * 从数组中删除最后一个元素，返回删除的元素
         * O(1)
         * @return
         */
        public E removeLast() {
            return remove(size-1);
        }

        /**
         * 从数组中删除元素e
         * @param e
         */
        public void removeElement(E e) {
            int index=find(e);
            if (index!=-1)
                remove(index);
        }

        /**
         * 在第index个位置插入一个新元素e
         * O(n/2)=O(n)
         * @param index
         * @param e
         */
        public void add(int index,E e) {

            if (index < 0 ||index > size) {
                throw new IllegalArgumentException("AddLast failed,Require index >=0 and ");
            }

            if (size==data.length) {
                resize(data.length>>2);
            }

            for (int i=size-1;i>=index;i++) {
                data[i+1]=data[i];
            }
            data[size++]=e;
        }

        private void resize(int j) {
            E[] newData = (E[]) new Object[j];
            for(int i=0;i<size;i++) {
                newData[i]=data[j];
            }
            data = newData;
        }

        @Override
        public String toString() {
            StringBuilder res = new StringBuilder();
            res.append(String.format("Array:size = %d,capacity = %d\n",size,data.length));
            res.append("[");
            for(int i=0;i<size;i++) {
                res.append(i);
                if (i!=size -1)
                    res.append(", ");
            }
            res.append("]");
            return  res.toString();
        }

}
