package me.cizezsy.chapter4.minimumspanningtrees;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

public class EdgeWeightedGraph {

    private int V;
    private int E;

    private Bag<Edge>[] adj;

    public EdgeWeightedGraph(int V) {
        this.V = V;
        adj = (Bag<Edge>[]) new Bag[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new Bag<>();
        }
    }

    public EdgeWeightedGraph(In in) {

    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(Edge edge) {
        int v = edge.either(), w = edge.other(v);
        adj[v].add(edge);
        adj[w].add(edge);
        E++;
    }

    public Iterable<Edge> adj(int v) {
        return adj[v];
    }

    public Iterable<Edge> edges() {
        Bag<Edge> r = new Bag<>();
        for (int v = 0; v < V; v++) {
            for (Edge e : adj[v]) {
                if (e.other(v) > v) {
                    r.add(e);
                }
            }
        }
        return r;
    }


    @Override
    public String toString() {
        return super.toString();
    }
}
