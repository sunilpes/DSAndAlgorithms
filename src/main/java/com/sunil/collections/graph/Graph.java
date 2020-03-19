package com.sunil.collections.graph;

import java.util.*;

public class Graph {

    private Map<Vertex, List<Vertex>> struct = new HashMap<Vertex, List<Vertex>>();

    public void addVertex(String label) {
        struct.putIfAbsent(new Vertex(label), new LinkedList<Vertex>());
    }

    public void addEdges(String label1, String label2) {
        Vertex v1 = new Vertex(label1);
        Vertex v2 = new Vertex(label2);
        struct.get(v1).add(v2);
        struct.get(v2).add(v1);
    }

    public void hasEdge(String s, String d)
    {
        if (struct.get(new Vertex(s)).contains(new Vertex(d))) {
            System.out.println("The graph has an edge between "
                    + s + " and " + d + ".");
        }
        else {
            System.out.println("The graph has no edge between "
                    + s + " and " + d + ".");
        }
    }

    public void print() {
        for(Vertex v: struct.keySet()) {
            System.out.println("The Vertex:" + v.getLabel());
            Iterator i = struct.get(v).iterator();
            while(i.hasNext()) {
                Vertex a = (Vertex) i.next();
                System.out.print("->" + a.getLabel());
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.addVertex("a1");
        graph.addVertex("a2");
        graph.addVertex("a3");
        graph.addVertex("a4");
        graph.addVertex("a5");
        graph.addVertex("a6");
        graph.addVertex("a7");
        graph.addEdges("a1", "a2");
        graph.addEdges("a3", "a2");
        graph.addEdges("a1", "a4");
        graph.addEdges("a4", "a6");
        graph.addEdges("a4", "a7");

        graph.hasEdge("a1", "a4");
        graph.print();

    }
}
