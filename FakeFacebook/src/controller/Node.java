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
public class Node {

    Vertex data;
    Node next;

    public Node(Vertex data) {
        this.data = data;
        this.next = null;
    }
}
