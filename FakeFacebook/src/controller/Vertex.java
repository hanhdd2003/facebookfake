/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Asus
 */
public class Vertex {

    String label;

    Set<Vertex> adjList = new HashSet<>();

    public Vertex(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public Set<Vertex> getAdjList() {
        return adjList;
    }

    public void addNeighbor(Vertex neighbor) {
        adjList.add(neighbor);
    }

    public void displayADJ() {
        System.out.println("Adj List: ");
        for (Vertex v : adjList) {
            System.out.println(v.label);
        }
    }
}
