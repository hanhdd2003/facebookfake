
package controller;


public class Node<T> {

    Vertex<T> data;
    Node<T> next;

    public Node(Vertex<T> data) {
        this.data = data;
        this.next = null;
    }
}
