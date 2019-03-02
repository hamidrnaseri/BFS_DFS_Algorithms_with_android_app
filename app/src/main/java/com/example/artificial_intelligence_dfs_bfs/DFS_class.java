package com.example.artificial_intelligence_dfs_bfs;

import android.util.Log;

import java.util.Iterator;
import java.util.LinkedList;

public class DFS_class {

    private final int first_node;
    private final int end_node;
    private LinkedList<Integer> Graph_list[];

    private int[] result = new int[10000];
    private int num=0;

    DFS_class(int first_node, int end_node) {
        this.first_node = first_node;
        this.end_node = end_node;


        Graph_list = new LinkedList[100000];
        for (int i = 0; i < 100000; i++)
            Graph_list[i] = new LinkedList();

    }


    private void addEdge(int node1, int node2) {
        Graph_list[node1].add(node2);
    }


    void DFSUtil(int first_node,int end_node,boolean visited[])
    {
        visited[first_node] = true;
        System.out.print(first_node+" ");

        result[num] = first_node;
        num++;
        if (first_node == end_node) {

        } else {
            Iterator<Integer> i = Graph_list[first_node].listIterator();
            while (i.hasNext())
            {
                int n = i.next();
                if (!visited[n])
                    DFSUtil(n,end_node, visited);
            }
        }

    }

    void DFS(int first_node, int end_node)
    {
        boolean visited[] = new boolean[100000];

        // Call the recursive helper function to print DFS traversal
        DFSUtil(first_node,end_node, visited);
    }



    int[] main(int[][] dataset) {


        for (int i = 0; i < dataset.length; i++) {
            addEdge(dataset[i][0], dataset[i][1]);

        }
        DFS(first_node, end_node);

        for (int i=0;i<result.length;i++){
            Log.i("mn",String.valueOf(i)+" : "+String.valueOf(result[i]));

        }

        return result;
    }

}
