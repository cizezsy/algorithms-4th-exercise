package me.cizezsy.chapter4.directedgraphs;

import edu.princeton.cs.algs4.Stack;

import java.util.LinkedList;
import java.util.Queue;

public class DepthFirstOrder {
    private boolean[] marked;
    private Queue<Integer> pre;
    private Queue<Integer> post;
    private Stack<Integer> reversePost;
    private int[] height;

    public DepthFirstOrder(Digraph g) {
        marked = new boolean[g.V()];
        pre = new LinkedList<>();
        post = new LinkedList<>();
        reversePost = new Stack<>();
        height = new int[g.V()];

        for (int v = 0; v < g.V(); v++) {
            if (!marked[v])
                dfs(g, v);
        }
    }

    private void dfs(Digraph g, int v) {
        marked[v] = true;
        pre.add(v);
        for (int w : g.adj(v)) {
            if (!marked[w])
                dfs(g, w);
        }
        post.add(v);
        height[v] = reversePost.size();
        reversePost.push(v);
    }

    public Queue<Integer> pre() {
        return pre;
    }

    public Queue<Integer> post() {
        return post;
    }

    public Stack<Integer> reversePost() {
        return reversePost;
    }

    public int height(int v) {
        return height[v];
    }
}
