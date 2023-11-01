/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author Asus
 */
public class MyQueue<T> {

    private Node<T> head;
    private Node<T> tail;
    private int size;

    public MyQueue() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public boolean isEmpty() {
        return this.head == null;
    }

    public void clear() {
        this.head = null;
        this.size = 0;
    }

    public void enqueue(Vertex<T> x) {
        Node<T> newNode = new Node<>(x);

        if (isEmpty()) {
            this.head = this.tail = newNode;

        } else {
            this.tail.next = newNode;
            this.tail = newNode;
        }
        this.size++;
    }

    public Node<T> dequeue() {
        if (isEmpty()) {
            System.out.println("rỗng");
            return null;
        }

        Node<T> temp = this.head;
        this.head = this.head.next;
        this.size--;
        return temp;
    }

    public Node<T> first() {
        if (isEmpty()) {
            System.out.println("rỗng");
            return null;
        }

        return this.head;
    }

    public void traverse() {
        Node<T> currunt = this.head;

        while (currunt != null) {
            System.out.print(currunt.data + " ");
            currunt = currunt.next;
        }
        System.out.println("");
    }
}
