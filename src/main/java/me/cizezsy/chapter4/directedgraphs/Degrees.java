package me.cizezsy.chapter4.directedgraphs;


import edu.princeton.cs.algs4.Bag;

public class Degrees {
    private Digraph g;

    public Degrees(Digraph g) {
        this.g = g;
    }

    public int indegree(int v) {
        int count = 0;
        for (int i = 0; i < g.V(); i++) {
            if (g.hasEdge(i, v)) count++;
        }
        return count;
    }

    public int outdegree(int v) {
        int count = 0;
        for (int ignored : g.adj(v)) {
            count++;
        }
        return count;
    }

    public Iterable<Integer> sources() {
        Bag<Integer> bag = new Bag<>();
        for (int v = 0; v < g.V(); v++) {
            if (indegree(v) == 0) bag.add(v);
        }
        return bag;
    }

    public Iterable<Integer> sinks() {
        Bag<Integer> bag = new Bag<>();
        for (int v = 0; v < g.V(); v++) {
            if (outdegree(v) == 0) bag.add(v);
        }
        return bag;
    }

    public boolean isMap() {
        for (int v = 0; v < g.V(); v++) {
            if (outdegree(v) != 1)
                return false;
        }
        return true;
    }
}
