package me.cizezsy.chapter4.undirectedgraphs;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class DegreesOfSeparation {

    public static void main(String[] args) {
        SymbolGraph sg = new SymbolGraph(StdIn.readLine(), StdIn.readLine());

        Graph G = sg.G();

        String source = StdIn.readLine();
        if (!sg.contains(source)) {
            StdOut.println(source + " not in database.");
            return;
        }

        int s = sg.index(source);
        BreadFirstPaths bfs = new BreadFirstPaths(G, s);
        while (!StdIn.isEmpty()) {
            String sink = StdIn.readLine();
            if (sg.contains(sink)) {
                int t = sg.index(sink);
                if (bfs.hasPathTo(t)) {
                    for (int v : bfs.pathTo(t)) {
                        StdOut.println("    " + sg.name(v));
                    }
                } else {
                    StdOut.println("Not connected");
                }
            } else {
                StdOut.println("Not in database.");
            }
        }
    }

}
