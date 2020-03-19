package com.sunil.collections.tree;
import java.util.*;
import java.lang.*;
import java.io.*;

public class DijkstraMST {

    private int noOfNodes = 0;

    private void run(int[][] graph, int startingNode) {
        this.noOfNodes = graph[0].length;

        int dist[] = new int[this.noOfNodes];

        Boolean sptSet[] = new Boolean[this.noOfNodes];

        for (int count = 0; count < this.noOfNodes; count++) {
            dist[count] = Integer.MAX_VALUE;
            sptSet[count] = false;
        }

        dist[startingNode] = 0;

        for(int i =0; i < this.noOfNodes-1; i++) {

            int minNode = findMinDistance(sptSet, dist);

            sptSet[minNode] = true;

            for (int j = 0; j < this.noOfNodes; j++) {
                print(dist);
                if (!sptSet[j] && graph[minNode][j] != 0 && dist[minNode] != Integer.MAX_VALUE && dist[minNode] + graph[minNode][j] < dist[j]) {
                    dist[j] = dist[minNode] + graph[minNode][j];
                }
            }
        }

        print(dist);
    }

    private int findMinDistance(Boolean[] sptSet, int[] distance) {
        int min = Integer.MAX_VALUE, min_index = -1;

        for(int i= 0;i < this.noOfNodes; i++) {
            if(sptSet[i] == false && distance[i] <= min) {
                min = distance[i];
                min_index = i;
            }
        }
        return min_index;
    }

    private void print(int[] distance) {
        System.out.println("Vertex \t\t Distance from Source");
        for (int i = 0; i < distance.length; i++)
            System.out.println(i + " \t\t " + distance[i]);
    }

    public static void main(String[] args) {
        int graph[][] = new int[][] { { 0, 4, 0, 0, 0, 0, 0, 8, 0 },
                { 4, 0, 8, 0, 0, 0, 0, 11, 0 },
                { 0, 8, 0, 7, 0, 4, 0, 0, 2 },
                { 0, 0, 7, 0, 9, 14, 0, 0, 0 },
                { 0, 0, 0, 9, 0, 10, 0, 0, 0 },
                { 0, 0, 4, 14, 10, 0, 2, 0, 0 },
                { 0, 0, 0, 0, 0, 2, 0, 1, 6 },
                { 8, 11, 0, 0, 0, 0, 1, 0, 7 },
                { 0, 0, 2, 0, 0, 0, 6, 7, 0 } };
        DijkstraMST t = new DijkstraMST();
        t.run(graph, 0);
    }
}
