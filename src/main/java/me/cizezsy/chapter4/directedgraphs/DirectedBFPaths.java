package me.cizezsy.chapter4.directedgraphs;

import edu.princeton.cs.algs4.Stack;

import java.util.LinkedList;
import java.util.Queue;

public class DirectedBFPaths {
    private boolean[] marked;
    private int[] edgeTo;
    private int s;

    public DirectedBFPaths(Digraph g, int s) {
        marked = new boolean[g.V()];
        edgeTo = new int[g.V()];
        this.s = s;
        bfs(g, s);
    }

    private void bfs(Digraph g, int s) {
        Queue<Integer> queue = new LinkedList<>();
        marked[s] = true;
        queue.add(s);
        while (!queue.isEmpty()) {
            int v = queue.poll();
            for (int w : g.adj(v)) {
                if (!marked[w]) {
                    queue.add(w);
                    marked[w] = true;
                    edgeTo[w] = v;
                }
            }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;

        Stack<Integer> path = new Stack<>();
        for (int i = v; i != s; i = edgeTo[i]) {
            path.push(i);
        }
        path.push(s);
        return path;
    }

    public int distTo(int v) {
        if (!hasPathTo(v)) return -1;
        int count = 0;
        for (int i = v; i != s; i = edgeTo[i]) {
            count++;
        }
        return count;
    }
}
