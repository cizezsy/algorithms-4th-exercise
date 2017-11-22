package me.cizezsy.chapter3.balancedsearchtree;

import me.cizezsy.chapter3.binarysearchtrees.OrderedST;

public class TopDown234Tree<Key extends Comparable<Key>, Value> extends OrderedST<Key, Value> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node root;

    @Override
    public void put(Key key, Value value) {
        root = put(root, key, value);
        root.color = BLACK;
    }

    private Node put(Node x, Key key, Value value) {
        if (x == null) {
            x = new Node(key, value, 1, RED);
            return x;
        }

        if (isRed(x.left) && isRed(x.right)) {
            flipColors(x);
        }

        int cmp = x.key.compareTo(key);
        if (cmp == 0) {
            x.value = value;
        } else if (cmp > 0) {
            x.left = put(x.left, key, value);
        } else {
            x.right = put(x.right, key, value);
        }

        if (isRed(x.right)) {
            x = rotateLeft(x);
        }
        if (isRed(x.left) && isRed(x.left.left)) {
            x = rotateRight(x);
        }

        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void putSingle(Key key, Value value) {
        if (root == null) {
            root = new Node(key, value, 1, BLACK);
            return;
        }

        Node f = getNode(key);
        if (f != null) {
            f.value = value;
            return;
        }

        Node n = root;
        Node parent = root;
        while (true) {
            if (n == null) {
                n = new Node(key, value, 1, RED);
                if (parent.key.compareTo(key) > 0) {
                    parent.left = n;
                } else {
                    parent.right = n;
                }
                break;
            }

            parent = n;

            if (isRed(parent.left) && isRed(parent.right)) {
                flipColors(parent);
            }
            if (isRed(parent.right)) {
                parent = rotateLeft(parent);
            }
            if (isRed(parent.left) && isRed(parent.left.left)) {
                parent = rotateRight(parent);
            }

            int cmp = parent.key.compareTo(key);
            if (cmp > 0) {
                n = parent.left;
            } else {
                n = parent.right;
            }

        }

    }


    @Override
    public Value get(Key key) {
        Node n = getNode(key);
        if (n == null) return null;
        else return n.value;
    }

    private Node getNode(Key key) {
        Node n = root;
        while (n != null) {
            int cmp = n.key.compareTo(key);
            if (cmp == 0) {
                return n;
            } else if (cmp > 0) {
                n = n.left;
            } else {
                n = n.right;
            }
        }
        return null;
    }


    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = size(h.left) + size(h.right) + 1;
        return x;
    }

    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = size(h.left) + size(h.right) + 1;
        return x;
    }

    private void flipColors(Node h) {
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }


    private boolean isRed(Node x) {
        return x != null && x.color == RED;
    }

    private int size(Node n) {
        if (n == null) return 0;
        else return n.N;
    }

    @Override
    public Key min() {
        return null;
    }

    @Override
    public Key max() {
        return null;
    }

    @Override
    public Key floor(Key key) {
        return null;
    }

    @Override
    public Key ceiling(Key key) {
        return null;
    }

    @Override
    public int rank(Key key) {
        return 0;
    }

    @Override
    public Key select(int k) {
        return null;
    }

    @Override
    public Iterable<Key> keys(Key lo, Key hi) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    private class Node {
        Key key;
        Value value;
        Node left, right;
        int N;
        boolean color; //color of link from its parent

        Node(Key key, Value value, int n, boolean color) {
            this.key = key;
            this.value = value;
            N = n;
            this.color = color;
        }
    }
}
