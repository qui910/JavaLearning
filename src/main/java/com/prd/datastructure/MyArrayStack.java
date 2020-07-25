package com.prd.datastructure;

/**
 * 基于数组实现的栈
 */
public class MyArrayStack<E> implements Stack<E>{

    private MyArray<E> array;

    public MyArrayStack(int capacity) {
        array = new MyArray<>(capacity);
    }

    public MyArrayStack() {
        array = new MyArray<>();
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    public int getCapacity() {
        return array.getCapacity();
    }

    @Override
    public void push(E e) {
        array.addLast(e);
    }

    @Override
    public E pop() {
        return array.removeFirst();
    }

    @Override
    public E peek() {
        return array.getLast();
    }

    @Override
    public String toString() {
        return "MyArrayStack{" +
                "array=" + array +
                '}';
    }
}
