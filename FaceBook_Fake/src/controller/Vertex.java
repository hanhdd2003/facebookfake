
package controller;

import java.util.HashSet;
import java.util.Set;


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
