package me.cizezsy.chapter3.symboltables;

public class SquentialSearchST<Key, Value> extends ST<Key, Value> {

    private Node first;
    private int size;

    public class Node {
        Key key;
        Value val;
        Node next;

        public Node(Key key, Value val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    @Override
    public void put(Key key, Value value) {
        if (key == null) throw new RuntimeException("can't put null key");
        if (value == null) {
            delete(key);
            return;
        }

        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x.val = value;
                return;
            }
        }
        first = new Node(key, value, first);
        size++;
    }

    @Override
    public Value get(Key key) {
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                return x.val;
            }
        }
        return null;
    }

    @Override
    public void delete(Key key) {
        if (first.key.equals(key)) {
            first = first.next;
            size--;
        } else {
            Node c = first;
            while (c.next != null) {
                if (c.next.key.equals(key)) {
                    c.next = c.next.next;
                    size--;
                    return;
                }
                c = c.next;
            }
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterable<Key> keys() {
        return null;
    }
}
