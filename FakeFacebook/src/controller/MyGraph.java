/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 *
 * @author Asus
 */
public class MyGraph<T> {

    Set<Vertex<T>> vertices = new HashSet<>();

    public MyGraph() {
    }

    public MyGraph(Set<Vertex<T>> vertices) {
        this.vertices = vertices;
    }

    public Set<T> getListVerticesLabel() {
        Set<T> list = new HashSet<>();
        this.vertices.stream().forEach(vertex -> list.add(vertex.label));
        return list;
    }

    public Vertex<T> getFirstVertexByIterator() {
        if (vertices.isEmpty())
            return null;
        
        Iterator<Vertex<T>> iterator = vertices.iterator();
        return iterator.next();
    }
    
    public Vertex<T> getFirstVertex() {
        if (vertices.isEmpty())
            return null;
        
        for (Vertex<T> vertex : vertices) {
            return vertex;
        }
        return null;
    }
    
    public Set<Vertex<T>> getVertices() {
        return vertices;
    }

    public Vertex<T> getVertex(T label) {
        return vertices.stream().filter(vertex -> vertex.label.equals(label)).findFirst().orElse(null);
    }

    public void addV(T label) {
        Vertex<T> newV = new Vertex<>(label);
        this.vertices.add(newV);
    }

    public void addE(T u, T v) {
        Vertex src = vertices.stream().filter(vetex -> vetex.label.equals(u)).findFirst().get();
        Vertex dest = vertices.stream().filter(vetex -> vetex.label.equals(v)).findFirst().get();
        src.adjList.add(this.getVertex(v));
        dest.adjList.add(this.getVertex(u));
    }

    public void removeV(T label) {
        Vertex<T> vertexToRemove = getVertex(label);
        if (vertexToRemove != null) {
            
            // xóa vertex trong vertices
            vertices.remove(vertexToRemove);
            
            //xóa tất cả cạnh liên kết với vertex
            for (Vertex<T> vertice : vertices) {
                vertice.getAdjList().remove(vertexToRemove);
            }
        }
    }
    
    public void removeE(T u, T v) {
        Vertex<T> vertexU = getVertex(u);
        Vertex<T> vertexV = getVertex(v);
        
        if (vertexU != null && vertexV != null) {
            vertexU.getAdjList().remove(vertexV);
            vertexV.getAdjList().remove(vertexU);
        }
    }
    
    public boolean containEdge(T u, T v) {
        Vertex<T> vertexU = getVertex(u);
        Vertex<T> vertexV = getVertex(v);
        
        if (vertexU == null || vertexV == null)
            return false;
        
        return vertexU.getAdjList().contains(vertexV);
    }
    
    public void BFS(T start) {
        Set<Vertex<T>> visited = new HashSet<>();
        MyQueue<Vertex<T>> queue = new MyQueue<>();
        Vertex<T> startVertex = getVertex(start);
        if (startVertex != null) {
            queue.enqueue((Vertex<Vertex<T>>) startVertex);
            while (!queue.isEmpty()) {
                Vertex<T> pop = (Vertex<T>) queue.dequeue().data;

                if (!visited.contains(pop)) {
                    System.out.print(pop.label + " ");
                    pop.getAdjList().stream().forEach(vertex -> queue.enqueue((Vertex<Vertex<T>>) vertex));
                }
                visited.add(pop);
            }
        } else {
            System.out.println("Start vertex not found");
        }
    }

    public void BFSTraversal() {
        Set<Vertex<T>> visited = new HashSet<>();
        Queue<Vertex<T>> queue = new LinkedList<>();
        Vertex<T> startVertex = getFirstVertexByIterator();
        if (startVertex != null) {
            queue.add(startVertex);
            while (!queue.isEmpty()) {
                Vertex<T> pop = queue.poll();

                if (!visited.contains(pop)) {
                    System.out.print(pop.label.toString());
                    pop.getAdjList().stream().forEach(vertex -> queue.add(vertex));
                }
                visited.add(pop);
            }
        } else {
            System.out.println("Start vertex not found");
        }
    }
    
        public void BFSTraversal(T start) {
        Set<Vertex<T>> visited = new HashSet<>();
        Queue<Vertex<T>> queue = new LinkedList<>();
        Vertex<T> startVertex = getVertex(start);
        if (startVertex != null) {
            queue.add(startVertex);
            while (!queue.isEmpty()) {
                Vertex<T> pop = queue.poll();

                if (!visited.contains(pop)) {
                    System.out.print(pop.label.toString());
                    pop.getAdjList().stream().forEach(vertex -> queue.add(vertex));
                }
                visited.add(pop);
            }
        } else {
            System.out.println("Start vertex not found");
        }
    }

    public Set<Vertex<T>> findNeighbors(T label) {
        Vertex<T> vertex = this.getVertex(label);
        if (vertex == null) {
            System.out.println("Not found");
            return new HashSet<>();
        }

        return vertex.getAdjList();
    }

    public void displayVertex() {
        vertices.stream().forEach(vertex -> System.out.print("\nVertex: " + vertex.label));
    }

}
