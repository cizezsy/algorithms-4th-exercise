package me.cizezsy.chapter3.binarysearchtrees;

import java.util.LinkedList;
import java.util.Queue;

public class BST<Key extends Comparable<Key>, Value> extends OrderedST<Key, Value> {

    private Node root;

    public BST() {
    }

    @Override
    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    private Node put(Node node, Key key, Value value) {
        if (node == null) return new Node(key, value, 1, 0);

        int cmp = node.key.compareTo(key);
        if (cmp == 0) {
            node.value = value;
        } else if (cmp < 0) {
            node.right = put(node.right, key, value);
        } else {
            node.left = put(node.left, key, value);
        }

        node.N = size(node.left) + size(node.right) + 1;
        node.height = Math.max(heightF(node.left), heightF(node.right)) + 1;
        return node;
    }

    @Override
    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node node, Key key) {
        if (node == null)
            return null;

        int cmp = node.key.compareTo(key);
        if (cmp == 0) {
            return node.value;
        } else if (cmp < 0) {
            return get(node.right, key);
        } else {
            return get(node.left, key);
        }
    }

    @Override
    public void delete(Key key) {
        root = delete(root, key);
    }

    private Node delete(Node node, Key key) {
        if (node == null) return null;

        int cmp = node.key.compareTo(key);
        if (cmp == 0) {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            } else {
                Node t = node;
                node = min(t.right);
                node.right = deleteMin(node.right);
                node.left = t.left;
            }
        } else if (cmp < 0) {
            node.right = delete(node.right, key);
        } else {
            node.left = delete(node.left, key);
        }
        node.N = size(node.right) + size(node.left) + 1;
        node.height = Math.max(heightF(node.left), heightF(node.right)) + 1;
        return node;
    }

    @Override
    public void deleteMin() {
        root = deleteMin(root);
    }

    private Node deleteMin(Node node) {
        if (node.left == null) return node.right;
        node.left = deleteMin(node.left);

        node.N = size(node.left) + size(node.right) + 1;
        node.height = Math.max(heightF(node.left), heightF(node.right)) + 1;
        return node;
    }

    @Override
    public void deleteMax() {
        root = deleteMax(root);
    }

    private Node deleteMax(Node node) {
        if (node.right == null)
            return node.left;
        node.right = deleteMax(node.right);

        node.N = size(node.left) + size(node.right) + 1;
        node.height = Math.max(heightF(node.left), heightF(node.right)) + 1;
        return node;
    }

    @Override
    public int rank(Key key) {
        return rank(root, key);
    }

    private int rank(Node node, Key key) {
        if (node == null) return 0;

        int cmp = node.key.compareTo(key);
        if (cmp == 0) {
            return size(node.left);
        } else if (cmp > 0) {
            return rank(node.left, key);
        } else {
            return size(node.left) + 1 + rank(node.right, key);
        }

    }

    @Override
    public Key select(int k) {
        Node node = select(root, k);
        if (node == null)
            return null;
        else
            return node.key;
    }

    private Node select(Node node, int k) {
        if (node == null) return null;

        int t = size(node.left);
        if (k - t < 0) {
            return select(node.left, k);
        } else if (k == t) {
            return node;
        } else {
            return select(node.right, k - t - 1);
        }
    }

    @Override
    public Key min() {
        return min(root).key;
    }

    private Node min(Node node) {
        if (node.left == null)
            return node;
        return min(node.left);
    }

    @Override
    public Key max() {
        return max(root).key;
    }

    private Node max(Node node) {
        if (node.right == null)
            return node;
        return max(node.right);
    }

    @Override
    public Key floor(Key key) {
        Node node = floor(root, key);
        if (node == null)
            return null;
        else
            return node.key;
    }

    private Node floor(Node node, Key key) {

        if (node == null) return null;

        int cmp = node.key.compareTo(key);
        if (cmp == 0)
            return node;
        else if (cmp > 0)
            return floor(node.left, key);

        Node t = floor(node.right, key);
        if (t != null)
            return t;
        else
            return node;
    }

    @Override
    public Key ceiling(Key key) {
        Node node = ceiling(root, key);
        if (node == null)
            return null;
        else
            return node.key;
    }

    private Node ceiling(Node node, Key key) {
        if (node == null)
            return null;

        int cmp = node.key.compareTo(key);
        if (cmp == 0) {
            return node;
        } else if (cmp < 0) {
            return ceiling(node.right, key);
        }

        Node t = ceiling(node.left, key);
        if (t != null)
            return t;
        else
            return node;
    }


    @Override
    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    @Override
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


    //3.2.6 recursive
    public int heightR() {
        return heightR(root);
    }

    private int heightR(Node node) {
        if (node == null) return -1;

        int leftHeight = heightR(node.left);
        int rightHeight = heightR(node.right);

        return Math.max(leftHeight, rightHeight) + 1;
    }

    //3.2.6 add field to node
    public int heightF() {
        return heightF(root);
    }

    private int heightF(Node root) {
        if (root == null) return 0;
        return root.height;
    }

    @Override
    public int size() {
        return size(root);
    }


    private int size(Node node) {
        if (node == null) return 0;
        else return node.N;
    }


    private class Node {
        private Key key;
        private Value value;
        private Node left, right;
        private int N;
        int height;

        Node(Key key, Value value, int N, int height) {
            this.key = key;
            this.value = value;
            this.N = N;
            this.height = height;
        }
    }
}
