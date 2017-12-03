package me.cizezsy.chapter4.directedgraphs;

import edu.princeton.cs.algs4.Stack;

public class DirectedCycle {
    private boolean[] marked;
    private int[] edgeTo;
    private Stack<Integer> cycle;
    private boolean[] onStack;

    public DirectedCycle(Digraph g) {
        marked = new boolean[g.V()];
        edgeTo = new int[g.V()];
        onStack = new boolean[g.V()];

        for (int i = 0; i < g.V(); i++) {
            if (!marked[i])
                dfs(g, i);
        }
    }

    private void dfs(Digraph g, int v) {
        marked[v] = true;
        onStack[v] = true;
        for (Integer w : g.adj(v)) {
            if (hasCycle()) return;
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(g, w);
            } else if(onStack[w]){
                cycle = new Stack<>();
                for (int i = v; i != w; i = edgeTo[w]) {
                    cycle.push(i);
                }
                cycle.push(w);
                cycle.push(v);
            }
        }
        onStack[v] = false;
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }
}
