package me.cizezsy.chapter4.undirectedgraphs;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

import java.util.Stack;

public class Graph {

    private int V;
    private int E;

    private Bag<Integer>[] adj;

    public Graph(int V) {
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new Bag<>();
        }
    }

    public Graph(In in) {
        this(in.readInt());
        int E = in.readInt();

        for (int i = 0; i < E; i++) {
            int v = in.readInt();
            int w = in.readInt();
            addEdge(v, w);
        }
    }

    public Graph(Graph another) {
        this(another.V);
        this.E = another.E;

        for (int i = 0; i < another.V(); i++) {
            Stack<Integer> stack = new Stack<>();
            for (int w : another.adj[i]) {
                stack.push(w);
            }
            while (!stack.isEmpty()) {
                adj[i].add(stack.pop());
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
        adj[w].add(v);
        E++;
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }


    public boolean hasEdge(int v, int w) {
        for (int va : adj[v]) {
            if (va == w) return true;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder(V + " vertices, " + E + " edges\n");
        for (int v = 0; v < V; v++) {
            s.append(v).append(": ");
            for (int w : adj(v)) {
                s.append(w).append(" ");
            }
            s.append("\n");
        }
        return s.toString();
    }

    public static int degree(Graph g, int v) {
        int degree = 0;
        for (int ignored : g.adj(v)) {
            degree++;
        }
        return degree;
    }

    public static int maxDegree(Graph g) {
        int max = 0;
        int v = g.V();
        for (int i = 0; i < v; i++) {
            int degree = degree(g, i);
            if (max < degree) {
                max = degree;
            }
        }
        return max;
    }

    public static double avgDegree(Graph g) {
        return 2.0 * g.E() / g.V();
    }

    public static int numberOfSelfLoop(Graph g) {
        int count = 0;
        int v = g.V();
        for (int i = 0; i < v; i++) {
            for (int w : g.adj(i)) {
                if (w == i) count++;
            }
        }
        return count / 2;
    }

}
