package me.cizezsy.chapter4.undirectedgraphs;

import java.util.Stack;

public class DeepFirstPaths extends Path {

    private boolean[] marked;
    private int edgeTo[];
    private int s;

    public DeepFirstPaths(Graph g, int s) {
        this.s = s;
        marked = new boolean[g.V()];
        edgeTo = new int[g.V()];
    }


    private void dfs(Graph g, int v) {
        marked[v] = true;
        for (int w : g.adj(v)) {
            edgeTo[w] = v;
            dfs(g, w);
        }
    }

    @Override
    public boolean hasPathTo(int v) {
        return marked[v];
    }

    @Override
    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;

        Stack<Integer> path = new Stack<>();
        for (int i = v; i != s; i = edgeTo[i]) {
            path.push(i);
        }
        path.push(s);
        return path;
    }
}
