package me.cizezsy.chapter3.balancedsearchtree;

import me.cizezsy.chapter3.binarysearchtrees.OrderedST;

import java.util.LinkedList;
import java.util.Queue;

public class TopDown234Tree<Key extends Comparable<Key>, Value> extends OrderedST<Key, Value> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node root;

    @Override
    public void put(Key key, Value value) {
        //root = put(root, key, value);
        //root.color = BLACK;
        putSingle(key, value);
    }

    //3.2.25
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

    //3.2.26
    private void putSingle(Key key, Value value) {
        if (root == null) {
            root = new Node(key, value, 1, BLACK);
            return;
        }

        Node t = getNode(key);
        if (t != null) {
            t.value = value;
            return;
        }

        if (isRed(root.left) && isRed(root.right)) {
            flipColors(root);
        }
        if (isRed(root.right)) {
            root = rotateLeft(root);
        }
        if (isRed(root.left) && isRed(root.left.left)) {
            root = rotateRight(root);
        }
        root.color = BLACK;

        Node parent = root;
        Node n = parent;
        while (true) {
            parent = n;
            parent.N = parent.N + 1;
            int cmp = parent.key.compareTo(key);
            if (cmp > 0) {
                n = parent.left;
            } else {
                n = parent.right;
            }

            if (n == null) {
                n = new Node(key, value, 1, RED);
                if (cmp > 0) {
                    parent.left = n;
                } else {
                    parent.right = n;
                }
                break;
            }

            if (isRed(n.left) && isRed(n.right)) {
                flipColors(n);
            }
            if (isRed(n.right)) {
                n = rotateLeft(n);
            }
            if (isRed(n.left) && isRed(n.left.left)) {
                n = rotateRight(n);
            }

            if (cmp > 0) {
                parent.left = n;
            } else {
                parent.right = n;
            }
        }

    }

    //3.2.27
    private Node putAllowRight(Node x, Key key, Value value) {
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

        if (isRed(x.left) && isRed(x.left.right)) {
            x.left = rotateLeft(x.left);
            x = rotateRight(x);
        } else if (isRed(x.left) && isRed(x.left.left)) {
            x = rotateRight(x);
        } else if (isRed(x.right) && isRed(x.right.right)) {
            x = rotateLeft(x);
        } else if (isRed(x.right) && isRed(x.right.left)) {
            x.right = rotateRight(x.right);
            x = rotateLeft(x);
        }

        x.N = size(x.left) + size(x.right) + 1;
        return x;
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


    @Override
    public int size() {
        return size(root);
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


    public static void main(String[] args) {
        TopDown234Tree<String, Integer> st = new TopDown234Tree<>();
        st.putSingle("6", 1);
        st.putSingle("4", 1);
        st.putSingle("1", 1);
        st.putSingle("3", 1);
        st.putSingle("7", 1);
        st.putSingle("2", 1);
    }
}
