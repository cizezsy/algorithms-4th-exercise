package me.cizezsy.chapter4.undirectedgraphs;

public class DeepFirstSearch extends Search {
    private boolean[] marked;
    private int count;

    public DeepFirstSearch(Graph g, int s) {
        marked = new boolean[g.V()];
        dfs(g, s);
    }

    public void dfs(Graph g, int v) {
        marked[v] = true;
        count++;
        for (int w : g.adj(v)) {
            if (!marked(w)) dfs(g, w);
        }
    }

    @Override
    public boolean marked(int w) {
        return marked[w];
    }

    @Override
    public int count() {
        return count;
    }
}
