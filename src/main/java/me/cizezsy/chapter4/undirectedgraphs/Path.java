package me.cizezsy.chapter4.undirectedgraphs;

public abstract class Path {
    public abstract boolean hasPathTo(int v);

    public abstract Iterable<Integer> pathTo(int v);

}
