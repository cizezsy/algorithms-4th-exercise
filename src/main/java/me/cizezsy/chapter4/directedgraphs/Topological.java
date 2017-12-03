package me.cizezsy.chapter4.directedgraphs;

import edu.princeton.cs.algs4.StdIn;

import java.util.Iterator;


public class Topological {
    private Iterable<Integer> order;

    public Topological(Digraph g) {
        DirectedCycle cycle = new DirectedCycle(g);
        if (!cycle.hasCycle()) {
            DepthFirstOrder dfs = new DepthFirstOrder(g);
            order = dfs.reversePost();
        }
    }

    public boolean isDAG() {
        return order == null;
    }

    public Iterable<Integer> order() {
        return order;
    }

    public static void main(String[] args) {
        String filename = StdIn.readLine();
        String separator = StdIn.readLine();

        SymbolDigraph sd = new SymbolDigraph(filename, separator);
        Topological top = new Topological(sd.digraph());

        for (int v : top.order) {
            System.out.println(sd.name(v));
        }
    }
}
