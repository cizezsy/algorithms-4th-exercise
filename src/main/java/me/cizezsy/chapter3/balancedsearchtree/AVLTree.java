package me.cizezsy.chapter3.balancedsearchtree;

import me.cizezsy.chapter3.symboltables.ST;

import java.util.LinkedList;
import java.util.Queue;

public class AVLTree<Key extends Comparable<Key>, Value> extends ST<Key, Value> {

    private Node root;

    @Override
    public void put(Key key, Value value) {
        Node t = get(root, key);
        if (t != null) {
            t.val = value;
        } else {
            root = put(root, key, value);
        }
    }

    private Node put(Node node, Key key, Value value) {
        if (node == null) {
            return new Node(key, value, 1, 0);
        }

        int cmp = node.key.compareTo(key);
        if (cmp > 0) {
            node.left = put(node.left, key, value);
        } else {
            node.right = put(node.right, key, value);
        }

        node.height++;
        if (leftHeightMore(node, 2)) {

            if (rightHeightMore(node.left, 1)) {
                node.left = rotateLeft(node.left);
            }
            if (leftHeightMore(node.left, 1)) {
                node = rotateRight(node);
            }
        } else if (rightHeightMore(node, 2)) {

            if (leftHeightMore(node.right, 1)) {
                node.right = rotateRight(node.right);
            }
            if (rightHeightMore(node.right, 1)) {
                node = rotateLeft(node);
            }
        }

        node.height = Math.max(height(node.left), height(node.right)) + 1;
        node.N = size(node.left) + size(node.right) + 1;
        return node;
    }

    private boolean leftHeightMore(Node node, int diff) {
        return height(node.left) - height(node.right) >= diff;
    }

    private boolean rightHeightMore(Node node, int diff) {
        return height(node.left) - height(node.right) <= -diff;
    }

    @Override
    public Value get(Key key) {
        Node t = get(root, key);
        if (t == null) return null;
        return t.val;
    }

    private Node get(Node node, Key key) {
        if (node == null)
            return null;

        int cmp = node.key.compareTo(key);
        if (cmp == 0) {
            return node;
        } else if (cmp < 0) {
            return get(node.right, key);
        } else {
            return get(node.left, key);
        }
    }

    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.N = h.N;
        h.N = size(h.left) + size(h.right) + 1;
        h.height = Math.max(height(h.left), height(h.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        return x;
    }

    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.N = h.N;
        h.N = size(h.left) + size(h.right) + 1;
        h.height = Math.max(height(h.left), height(h.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        return x;
    }

    public Key min() {
        return min(root).key;
    }

    private Node min(Node node) {
        if (node.left == null)
            return node;
        return min(node.left);
    }


    public Key max() {
        return max(root).key;
    }

    private Node max(Node node) {
        if (node.right == null)
            return node;
        return max(node.right);
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null) return 0;
        return node.N;
    }

    private int height(Node node) {
        if (node == null) return -1;
        return node.height;
    }

    @Override
    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> queue = new LinkedList<>();
        keys(root, queue, lo, hi);
        return queue;
    }

    private void keys(Node node, Queue<Key> queue, Key lo, Key hi) {
        if (node == null)
            return;
        int cmplo = lo.compareTo(node.key);
        int cmphi = hi.compareTo(node.key);
        if (cmplo < 0) keys(node.left, queue, lo, hi);
        if (cmplo <= 0 && cmphi >= 0) queue.add(node.key);
        if (cmphi > 0) keys(node.right, queue, lo, hi);
    }

    private class Node {
        Key key;
        Value val;
        Node left, right;
        int N;
        int height;

        public Node(Key key, Value val, int n, int height) {
            this.key = key;
            this.val = val;
            N = n;
            this.height = height;
        }
    }
}
