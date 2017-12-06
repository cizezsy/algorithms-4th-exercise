package me.cizezsy.chapter4.minimumspanningtrees;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class MST {
    private EdgeWeightedGraph G;

    public MST(EdgeWeightedGraph G) {
        this.G = G;
    }

    private Iterable<Edge> edges() {

    }

    private int weight() {

    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        EdgeWeightedGraph G = new EdgeWeightedGraph(in);

        MST mst = new MST(G);
        for (Edge e : mst.edges()) {
            StdOut.println(e);
        }

        StdOut.println(mst.weight());
    }

}
