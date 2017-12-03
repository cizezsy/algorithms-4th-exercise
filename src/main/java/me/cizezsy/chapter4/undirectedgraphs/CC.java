package me.cizezsy.chapter4.undirectedgraphs;

public class CC {

    private boolean[] marked;
    private int id[];
    private int count;

    public CC(Graph g) {
        marked = new boolean[g.V()];
        id = new int[g.V()];
        count = 0;

        for (int i = 0; i < g.V(); i++) {
            if (!marked[i]) {
                dfs(g, i);
                count++;
            }
        }
    }

    private void dfs(Graph g, int v) {
        marked[v] = true;
        id[v] = count;
        for (int w : g.adj(v)) {
            if (!marked[w]) {
                dfs(g, w);
            }
        }
    }

    private boolean conntected(int v, int w) {
        return id[v] == id[w];
    }

    private int count() {
        return count;
    }

    private int id(int v) {
        return id[v];
    }
}
