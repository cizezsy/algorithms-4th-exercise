package me.cizezsy.chapter4.undirectedgraphs;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class TestSearch {

    public static void main(String[] args) {

        String path = "C:\\Users\\Administrator\\Desktop\\tinyG.txt";
        Graph g = new Graph(new In(path));
        int s = 0;

        Search search = new DeepFirstSearch(g, s);
        for (int v = 0; v < g.V(); v++) {
            if (search.marked(v)) {
                StdOut.print(v + " ");
            }
        }
        System.out.println();

        if (search.count() != g.V()) {
            StdOut.print("NOT ");
        }
        StdOut.print("connected");

    }
}
