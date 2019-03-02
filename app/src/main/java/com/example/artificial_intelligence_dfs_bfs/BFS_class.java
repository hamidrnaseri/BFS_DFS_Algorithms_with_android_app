package com.example.artificial_intelligence_dfs_bfs;

import android.util.Log;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.LinkedList;

public class BFS_class {


    private int end_node;
    private int first_node;
    private LinkedList<Integer> Graph_list[];

    private int[] result = new int[10000];
    private Integer num = 0;
    public static String str_tests;

    BFS_class(int first_node, int end_node) {
        this.first_node = first_node;
        this.end_node = end_node;


        Graph_list = new LinkedList[100000];
        for (int i = 0; i < 100000; i++)
            Graph_list[i] = new LinkedList();

    }

    private void addEdge(int node1, int node2) {
        Graph_list[node1].add(node2);
    }

    void BFS(int first_node, int end_node) {

        boolean visited[] = new boolean[100000];

        LinkedList<Integer> queue = new LinkedList<Integer>();

        visited[first_node] = true;
        queue.add(first_node);

        while (queue.size() != 0) {

            first_node = queue.poll();

            result[num] = first_node;
            num++;
            if (first_node == end_node) {
                break;
            } else {

                Iterator<Integer> i = Graph_list[first_node].listIterator();
                while (i.hasNext()) {
                    int n = i.next();
                    if (!visited[n]) {
                        visited[n] = true;
                        queue.add(n);
                    }
                }
            }
        }}



    int[] main(int[][] dataset) {


        for (int i = 0; i < dataset.length; i++) {
                addEdge(dataset[i][0], dataset[i][1]);

        }
        BFS(first_node, end_node);

        for (int i=0;i<result.length;i++){
                Log.i("mn",String.valueOf(i)+" : "+String.valueOf(result[i]));

        }

        return result;
    }

}

