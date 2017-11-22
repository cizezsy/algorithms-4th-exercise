package me.cizezsy.chapter3.binarysearchtrees;

public class NoRecursiveBST<Key extends Comparable<Key>, Value> extends OrderedST<Key, Value> {

    private Node root;


    public NoRecursiveBST() {
    }

    @Override
    public void put(Key key, Value value) {
        if (root == null) {
            root = new Node(key, value, 1);
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
                n = new Node(key, value, 1);
                if (parent.key.compareTo(key) > 0) {
                    parent.left = n;
                } else {
                    parent.right = n;
                }
                break;
            }
            parent = n;
            parent.N++;
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


    @Override
    public Key min() {
        Node n = root;
        while (n.left != null) {
            n = n.left;
        }
        return n.key;
    }

    @Override
    public Key max() {
        Node n = root;
        while (n.right != null) {
            n = n.right;
        }
        return n.key;
    }

    @Override
    public Key floor(Key key) {
        Node n = root;
        Node curr = null;

        while (n != null) {
            int cmp = n.key.compareTo(key);
            if (cmp == 0) {
                return key;
            } else if (cmp > 0) {
                n = n.left;
            } else {
                curr = n;
                n = n.right;
            }
        }
        return curr == null ? null : curr.key;
    }

    @Override
    public Key ceiling(Key key) {
        Node n = root;
        Node curr = null;

        while (n != null) {
            int cmp = n.key.compareTo(key);
            if (cmp == 0) {
                return key;
            } else if (cmp < 0) {
                n = n.right;
            } else {
                curr = n;
                n = n.left;
            }
        }
        return curr == null ? null : curr.key;
    }

    @Override
    public int rank(Key key) {
        Node n = root;
        int count = 0;
        while (n != null) {
            int cmp = n.key.compareTo(key);
            if (cmp == 0) {
                count += size(n.left);
                return count;
            } else if (cmp > 0) {
                n = n.left;
            } else {
                count = count + size(n.left) + 1;
                n = n.right;
            }
        }
        return count;
    }

    @Override
    public Key select(int k) {
        Node n = root;
        while (n != null) {
            int leftN = size(n.left);
            if (k == leftN) {
                return n.key;
            } else if (k > leftN) {
                k = k - leftN - 1;
                n = n.right;
            } else {
                n = n.left;
            }
        }
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

    private int size(Node n) {
        if (n == null) return 0;
        return n.N;
    }

    private class Node {
        private Key key;
        private Value value;
        private Node left, right;
        private int N;

        Node(Key key, Value value, int N) {
            this.key = key;
            this.value = value;
            this.N = N;
        }
    }

    public static void main(String[] args) {
        NoRecursiveBST<String, Integer> bst = new NoRecursiveBST<>();
        bst.put("f", 1);
        bst.put("b", 2);
        bst.put("a", 1);
        bst.put("e", 3);
        bst.put("h", 1);

        System.out.println(bst.select(4));
    }
}
