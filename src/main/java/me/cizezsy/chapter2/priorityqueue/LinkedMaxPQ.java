package me.cizezsy.chapter2.priorityqueue;

public class LinkedMaxPQ<Key extends Comparable<Key>> {

    private Node root = null;

    public LinkedMaxPQ() {
    }

    public void insert(Key v) {
        Node n = new Node(v, 1);
        if (isEmpty()) {
            root = n;
        } else {
            swam(n);
        }
    }

    public Key max() {
        return root.value;
    }

    public Key delMax() {
        Node n = sink();

        if (n == root) {
            Key v = root.value;
            root = null;
            return v;
        }

        if (n.parent.left == n) {
            n.parent.left = null;
        } else {
            n.parent.right = null;
        }
        heavyChange(n.parent, -1);
        n.parent = null;
        return n.value;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        return heavy(root);
    }

    private Node sink() {
        Node n = root;
        while (true) {
            if (n.right == null && n.left == null) {
                return n;
            }
            if (n.right == null) {
                exch(n, n.left);
                n = n.left;
            } else if (n.left == null) {
                exch(n, n.right);
                n = n.right;
            } else if (less(n.right, n.left)) {
                exch(n, n.left);
                n = n.left;
            } else {
                exch(n, n.right);
                n = n.right;
            }
        }
    }

    private void swam(Node v) {
        Node n = root;
        while (true) {
            if (less(n, v))
                exch(n, v);
            int leftHeavy = heavy(n.left);
            int rightHeavy = heavy(n.right);
            if (leftHeavy == 0) {
                n.left = v;
                v.parent = n;
                heavyChange(n, 1);
                break;
            } else if (rightHeavy == 0) {
                n.right = v;
                v.parent = n;
                heavyChange(n, 1);
                break;
            } else if (leftHeavy <= rightHeavy) {
                n = n.left;
            } else {
                n = n.right;
            }
        }
    }

    private void heavyChange(Node n, int i) {
        while (n != null) {
            n.heavy += i;
            n = n.parent;
        }
    }

    private boolean less(Node i, Node j) {
        return i.value.compareTo(j.value) < 0;
    }

    private void exch(Node i, Node j) {
        Key v = i.value;
        i.value = j.value;
        j.value = v;
    }

    private int heavy(Node n) {
        if (n == null)
            return 0;
        return n.heavy;
    }

    private class Node {
        private int heavy;
        private Node parent;
        private Node left;
        private Node right;
        private Key value;

        public Node(Key value, int heavy) {
            this.heavy = heavy;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        LinkedMaxPQ<String> pq = new LinkedMaxPQ<>();
        pq.insert("t");
        pq.insert("s");
        pq.insert("a");
        pq.insert("v");
        pq.insert("b");

        System.out.println(pq.delMax());
        System.out.println(pq.delMax());
        System.out.println(pq.delMax());
        System.out.println(pq.delMax());
        System.out.println(pq.delMax());
    }

}
