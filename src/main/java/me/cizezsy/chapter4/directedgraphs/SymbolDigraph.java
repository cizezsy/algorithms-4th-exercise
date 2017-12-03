package me.cizezsy.chapter4.directedgraphs;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;

/**
 * Copy from algs4 lib
 */
public class SymbolDigraph {
    private ST<String, Integer> st;  // string -> index
    private String[] keys;           // index  -> string
    private Digraph graph;           // the underlying digraph

    public SymbolDigraph(String filename, String delimiter) {
        st = new ST<String, Integer>();

        In in = new In(filename);
        while (in.hasNextLine()) {
            String[] a = in.readLine().split(delimiter);
            for (int i = 0; i < a.length; i++) {
                if (!st.contains(a[i]))
                    st.put(a[i], st.size());
            }
        }

        keys = new String[st.size()];
        for (String name : st.keys()) {
            keys[st.get(name)] = name;
        }

        graph = new Digraph(st.size());
        in = new In(filename);
        while (in.hasNextLine()) {
            String[] a = in.readLine().split(delimiter);
            int v = st.get(a[0]);
            for (int i = 1; i < a.length; i++) {
                int w = st.get(a[i]);
                graph.addEdge(v, w);
            }
        }
    }

    public boolean contains(String s) {
        return st.contains(s);
    }

    public int index(String s) {
        return st.get(s);
    }

    public int indexOf(String s) {
        return st.get(s);
    }

    public String name(int v) {
        return keys[v];
    }


    public String nameOf(int v) {
        return keys[v];
    }

    public Digraph G() {
        return graph;
    }
    public Digraph digraph() {
        return graph;
    }

}
