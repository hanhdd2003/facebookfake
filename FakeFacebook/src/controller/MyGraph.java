/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Asus
 */
public class MyGraph {
    
    Set<Vertex> vertices = new HashSet<>();
    Deque<String> path = new ArrayDeque<>();
    
    public MyGraph() {
    }
    
    public MyGraph(Set<Vertex> vertices) {
        this.vertices = vertices;
    }
 
    public ArrayList<String> getListVerticesLabel() {
        ArrayList<String> list = new ArrayList<>();
        this.vertices.stream().forEach(vertex -> list.add(vertex.label));
        return list;
    }
    
    public Set<Vertex> getVertices() {
        return vertices;
    }
    
    public Vertex getVertex(String label) {
        return vertices.stream().filter(vertex -> vertex.label.equals(label)).findFirst().get();
    }
    
    public void addV(String label) {
        Vertex newV = new Vertex(label);
        this.vertices.add(newV);
    }
    
    public void addE(String u, String v) {
        Vertex src = vertices.stream().filter(vetex -> vetex.label.equals(u)).findFirst().get();
        src.adjList.add(this.getVertex(v));
    }
    
    public void BFS(String start) {
        Set<Vertex> visited = new HashSet<>();
        MyQueue queue = new MyQueue();
        queue.enqueue(this.getVertex(start));
        while (!queue.isEmpty()) {
            Vertex pop = queue.dequeue().data;
            
            //kiểm tra đã được duyệt qua chưa
            if (!visited.contains(pop)) {
                System.out.print(pop.label + " ");
                Set<Vertex> adj = pop.getAdjList();
                pop.getAdjList().stream().forEach(vertex -> queue.enqueue(vertex));
            }
            visited.add(pop);
        }
    }
    
    public void DFS(String start) {
        Set<Vertex> visited = new HashSet<>();
        MyStack stack = new MyStack();
        stack.push(this.getVertex(start));
        while (!stack.isEmpty()) {
            Vertex pop = stack.pop().data;
            if (!visited.contains(pop)) {
                System.out.print(pop.label + " ");
                Set<Vertex> adj = pop.getAdjList();
                List<Vertex> adjL = new ArrayList<>();
                adjL.addAll(adj);
                Collections.reverse(adjL);
                adjL.stream().forEach(vertex -> stack.push(vertex));
            }
            visited.add(pop);
        }
    }
    
//    public void DFSByWeight(String start) {
//        Set<Vertex> visited = new HashSet<>();
//        MyStack stack = new MyStack();
//        stack.push(this.getVertex(start));
//        while (!stack.isEmpty()) {
//            Vertex pop = stack.pop().data;
//            if (!visited.contains(pop)) {
//                System.out.print(pop.label + " ");
//                Set<Vertex> adj = pop.getAdjSortedByWeight();
//                adjL.stream().forEach(vertex -> stack.push(vertex));
//            }
//            visited.add(pop);
//        }
//    }
//    
//    public void DFS_recursive(Vertex vertex, Set<Vertex> visited) {
//        visited.add(vertex);
//        System.out.print(vertex.label + " ");
//        for (Map.Entry<Vertex, Integer> entry : vertex.adjList.entrySet()) {//can modify to set the rules
//            if (!visited.contains(entry.getKey())) {
//                this.DFS_recursive(entry.getKey(), visited);
//            }
//            
//        }
//    }
    
    public void displayVertex() {
        vertices.stream().forEach(vertex -> System.out.print("\nVertex: " + vertex.label));
    }
    
}
