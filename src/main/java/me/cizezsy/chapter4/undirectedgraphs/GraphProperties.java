package me.cizezsy.chapter4.undirectedgraphs;

public class GraphProperties {
    private Graph G;

    public GraphProperties(Graph G) {
        this.G = G;
    }

    public int eccentricity(int v) {
        BreadFirstPaths bfs = new BreadFirstPaths(G, v);
        int max = 0;
        for (int i = 0; i < G.V(); i++) {
            if (i != v && bfs.distTo(i) > max) {
                max = bfs.distTo(i);
            }
        }
        return max;
    }

    public int diameter() {
        int max = -1;
        for (int i = 0; i < G.V(); i++) {
            if (eccentricity(i) > max) {
                max = eccentricity(i);
            }
        }
        return max;
    }

    public int radius() {
        if (G.V() == 0) return -1;
        int min = eccentricity(0);
        for (int i = 1; i < G.V(); i++) {
            if (eccentricity(i) < min) {
                min = eccentricity(i);
            }
        }
        return min;
    }

    public int center() {
        int radius = radius();
        for (int i = 0; i < G.V(); i++) {
            if (eccentricity(i) == radius) {
                return i;
            }
        }
        return -1;
    }
}
