package com.prd.suanfa;



public class MyLinkList<E> {

    private Node<E> last;


    public void add(E item){
    }


   private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }
}
