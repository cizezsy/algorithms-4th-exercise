package me.cizezsy.chapter4.directedgraphs;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

public class Digraph {
    private int E;
    private int V;
    private Bag<Integer>[] adj;

    public Digraph(int v) {
        V = v;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int i = 0; i < adj.length; i++) {
            adj[i] = new Bag<>();
        }
    }

    public Digraph(In in) {
        this(in.readInt());
        int E = in.readInt();

        for (int i = 0; i < E; i++) {
            int v = in.readInt();
            int w = in.readInt();
            adj[v].add(w);
        }
    }

    public Digraph(Digraph another) {
        this(another.V());
        this.E = another.E;
        for (int v = 0; v < another.V(); v++) {
            for (int w : another.adj(v)) {
                adj[v].add(w);
            }
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
        E++;
    }

    public boolean hasEdge(int v, int w) {
        for (int i : adj(v)) {
            if(i == w) return true;
        }
        return false;
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    public Digraph reverse() {
        Digraph r = new Digraph(V);
        for (int v = 0; v < this.V; v++) {
            for (int w : adj(v)) {
                r.addEdge(w, v);
            }
        }
        return r;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
