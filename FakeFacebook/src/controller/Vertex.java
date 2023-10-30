/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Asus
 */
public class Vertex<T> {

    T label;

    Set<Vertex<T>> adjList = new HashSet<>();

    public Vertex(T label) {
        this.label = label;
    }

    public T getLabel() {
        return this.label;
    }

    public Set<Vertex<T>> getAdjList() {
        return adjList;
    }

    public void addNeighbor(Vertex<T> neighbor) {
        adjList.add(neighbor);
    }

    public void displayADJ() {
        System.out.println("Adj List: ");
        for (Vertex<T> v : adjList) {
            System.out.print(v.label + " ");
        }
    }
}
