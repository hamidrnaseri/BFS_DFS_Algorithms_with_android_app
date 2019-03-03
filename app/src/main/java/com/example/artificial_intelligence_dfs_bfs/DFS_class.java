package com.example.artificial_intelligence_dfs_bfs;

import android.util.Log;

import java.util.*;

public class DFS_class {

    private final int first_node;
    private final int end_node;
    private ArrayList<Integer>[] adjList;
    private int[] result = new int[10000];
    private ArrayList<Integer> localPathList = new ArrayList<>();

    DFS_class(int first_node, int end_node) {
        this.first_node = first_node;
        this.end_node = end_node;
        adjList = new ArrayList[100000];
        for (int i = 0; i < 100000; i++)
            adjList[i] = new ArrayList<>();
    }


    private void addEdge(int node1, int node2) {
        adjList[node1].add(node2);
    }

    void DFSs(int first_node, int end_node) {
        boolean[] isVisited = new boolean[100000];
        localPathList.add(first_node);
        printAllPathsUtil(first_node, end_node, isVisited);
    }

    private void printAllPathsUtil(Integer first_node, Integer end_node, boolean[] isVisited) {

        isVisited[first_node] = true;
        if (first_node.equals(end_node)) {
            Log.i("ppaka", String.valueOf(localPathList));
            Log.i("ppaka2", String.valueOf(localPathList.size()));
            isVisited[first_node] = false;
            return;
        }
        for (Integer i : adjList[first_node]) {
            if (!isVisited[i]) {
                localPathList.add(i);
                printAllPathsUtil(i, end_node, isVisited);
                localPathList.remove(i);
            }
        }
        isVisited[first_node] = false;
    }


    int[] main(int[][] dataset) {

        for (int i = 0; i < dataset.length; i++) {
            addEdge(dataset[i][0], dataset[i][1]);
        }
        DFSs(first_node, end_node);

        return result;
    }

}
