package me.cizezsy.chapter4.directedgraphs;

import edu.princeton.cs.algs4.StdIn;

import java.util.*;

public class LCA {
    private DepthFirstOrder order;
    private Digraph g;

    public LCA(Digraph g) {
        order = new DepthFirstOrder(g);
        this.g = g.reverse();
    }


    public int ancestor(int v, int w) {
        List<Integer> va = new LinkedList<>();
        List<Integer> wa = new LinkedList<>();

        DirectedDFS vs = new DirectedDFS(g, v);
        DirectedDFS ws = new DirectedDFS(g, w);

        Set<Integer> cs = new HashSet<>();
        for (int i = 0; i < g.V(); i++) {
            if (vs.marked(i) && i != v) {
                va.add(i);
            }
            if (ws.marked(i) && i != w) {
                wa.add(i);
            }
        }

        for (int i : wa) {
            if (va.contains(i)) {
                cs.add(i);
            }
        }

        if (cs.size() == 0) return -1;

        return cs.stream().min(Comparator
                .comparingInt(o -> order.height(o)))
                .orElse(-1);
    }


    public static void main(String[] args) {
        String filename = StdIn.readLine();
        String separator = StdIn.readLine();

        SymbolDigraph sd = new SymbolDigraph(filename, separator);

        LCA lca = new LCA(sd.digraph());

        int ancestor = lca.ancestor(3, 6);
        System.out.printf("%s and %s's ancestor is %s", sd.name(3), sd.name(6), sd.name(ancestor));
    }
}
