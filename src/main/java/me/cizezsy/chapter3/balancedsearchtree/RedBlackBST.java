package me.cizezsy.chapter3.balancedsearchtree;

import me.cizezsy.chapter3.binarysearchtrees.OrderedST;

import java.util.LinkedList;
import java.util.Queue;

public class RedBlackBST<Key extends Comparable<Key>, Value> extends OrderedST<Key, Value> {
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
        if (isRed(x.left) && isRed(x.right)) {
            flipColors(x);
        }

        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    @Override
    public Value get(Key key) {
        Node t = get(root, key);
        if (t == null) return null;
        return t.value;
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


    @Override
    public void deleteMin() {
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;
        root = deleteMin(root);
        if (!isEmpty()) root.color = BLACK;
    }

    private Node deleteMin(Node node) {
        if (node.left == null) {
            return null;
        }
        if (!isRed(node.left) && !isRed(node.left.left)) {
            node = moveNodeLeft(node);
        }
        node.left = deleteMin(node.left);
        return balance(node);
    }

    @Override
    public void delete(Key key) {
        if (!isRed(root.left) && !isRed(root.right)) {
            root.color = RED;
        }
        root = delete(root, key);
        if (!isEmpty()) root.color = BLACK;
    }

    private Node delete(Node node, Key key) {
        if (key.compareTo(node.key) < 0) {
            if (!isRed(node.left) && !isRed(node.left.left))
                node = moveNodeLeft(node);
            node.left = delete(node.left, key);
        } else {
            if (isRed(node.left))
                node = rotateRight(node);
            if (key.compareTo(node.key) == 0 && (node.right == null))
                return null;
            if (!isRed(node.right) && !isRed(node.right.left))
                node = moveNodeRight(node);
            if (key.compareTo(node.key) == 0) {
                Node x = min(node.right);
                node.key = x.key;
                node.value = x.value;
                node.right = deleteMin(node.right);
            } else node.right = delete(node.right, key);
        }
        return balance(node);
    }

    @Override
    public void deleteMax() {
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;
        root = deleteMax(root);
        if (!isEmpty()) root.color = BLACK;
    }

    private Node deleteMax(Node node) {
        if (isRed(node.left)) {
            rotateRight(node);
        }
        if (node.right == null) {
            return null;
        }
        if (!isRed(node.right) && !isRed(node.right.left)) {
            node = moveNodeRight(node);
        }
        node.right = deleteMax(node.right);
        return node;
    }

    private Node moveNodeRight(Node node) {
        flipColors(node);
        if (isRed(node.left.left)) {
            node = rotateRight(node);
        }
        return node;
    }

    private Node moveNodeLeft(Node node) {
        flipColors(node); // make node's color be red
        if (isRed(node.right.left)) {
            node.right = rotateRight(node.right);
            node = rotateLeft(node);
        }
        return node;
    }

    private Node balance(Node node) {
        if (isRed(node.right)) {
            node = rotateLeft(node);
        }
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rotateRight(node);
        }
        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }
        node.N = size(node.left) + size(node.right) + 1;
        return node;
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
        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
    }


    private boolean isRed(Node x) {
        return x != null && x.color == RED;
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

    private int size(Node n) {
        if (n == null) return 0;
        else return n.N;
    }


    private boolean is23(Node node) {
        if (node == null) return true;
        return is23(node.left, node.color) && is23(node.right, node.color);
    }

    private boolean is23(Node node, boolean isParentRed) {
        if (node == null)
            return true;

        if (isParentRed && isRed(node)) {
            return false;
        } else if (isRed(node.right)) {
            return false;
        }

        return is23(node.left, node.color) && is23(node.right, node.color);
    }

    private boolean isBalanced(Node node) {
        return isBalanced(root, blackLinkNum(node, 0));
    }

    private boolean isBalanced(Node node, int size) {
        if (node == null) {
            return size == 0;
        }

        if (!isRed(node))
            size--;

        return isBalanced(node.left, size) && isBalanced(node.right, size);
    }


    private int blackLinkNum(Node node, int size) {
        if (node == null) return size;
        size = blackLinkNum(node.left, size);
        if (!isRed(node))
            size += 1;
        return size;
    }

    private boolean isBinaryTree(Node node) {
        return isBinaryTree(node, node.N);
    }

    private boolean isBinaryTree(Node node, int size) {
        if (node == null) {
            return size == 0;
        }

        return size > 0
                && isBinaryTree(node.left, size - 1 - size(node.right))
                && isBinaryTree(node.right, size - 1 - size(node.left));
    }

    private boolean isOrdered(Node node, Key min, Key max) {
        if (node == null)
            return true;

        int cmpMax = node.key.compareTo(max);
        int cmpMin = node.key.compareTo(min);

        return cmpMax <= 0 && cmpMin >= 0 && isOrdered(node.left, min, max) && isOrdered(node.right, min, max);

    }

    private boolean hasDuplicates(Node node) {
        if (node == null)
            return true;

        boolean cmp = true;
        if (node.left != null) {
            cmp = (node.key.compareTo(node.left.key) != 0);
        }
        if (node.right != null) {
            cmp = cmp && (node.key.compareTo(node.right.key) != 0);
        }

        cmp = cmp && hasDuplicates(node.left) && hasDuplicates(node.right);
        return cmp;
    }

    private boolean isBST(Node node) {
        return isBinaryTree(node) && isOrdered(node, min(), max()) && hasDuplicates(node);
    }

    public boolean isRedBlackBST() {
        return isBST(root) && is23(root) && isBalanced(root);
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
        RedBlackBST<String, Integer> bst = new RedBlackBST<>();
        bst.put("G", 1);
        bst.put("C", 1);
        bst.put("M", 1);
        bst.put("A", 1);
        bst.put("D", 1);
        bst.put("L", 1);
        bst.put("N", 1);
        bst.deleteMin();
    }

}
