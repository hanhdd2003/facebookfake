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
public class MyStack {

   Node top;
    private int size;

    public MyStack() {
        this.top = null;
        this.size = 0;
    }

    public boolean isEmpty() {
        return this.top == null;
    }

    public void clear() {
        this.top = null;
        this.size = 0;
    }

    public void push(Vertex x) {
        Node newNode = new Node(x);
        if (isEmpty()) {
            this.top = newNode;

        } else {
            newNode.next = this.top;
            this.top = newNode;

        }
        this.size++;
    }

    public Node pop() {
        if (isEmpty()) {
            return null;
        } else {
            Node temp = this.top;
            this.top = this.top.next;
            this.size--;
            return temp;
        }
    }

    public Node top() {
        return this.top;
    }

    public void traverse() {
        Node temp = this.top;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println("");
    }
}
