package me.cizezsy.chapter4.directedgraphs;

import edu.princeton.cs.algs4.Bag;

public class Euler {
    private boolean isEuler;

    public Euler(Digraph g) {
        findCycle(g);
    }

    public void findCycle(Digraph g) {
        KosarajuSCC scc = new KosarajuSCC(g);
        Degrees degrees = new Degrees(g);
        isEuler = false;
        if (scc.count() == 1) {
            for (int v = 0; v < g.V(); v++) {
                if (degrees.indegree(v) != degrees.outdegree(v)) {
                    return;
                }
            }
            isEuler = true;
        }
    }

    public boolean isEuler() {
        return isEuler;
    }
}
