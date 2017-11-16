package me.cizezsy.chapter3.symboltables;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.HashMap;
import java.util.Map;

public class DefaultST<Key, Value> extends ST<Key, Value> {
    private Map<Key, Value> map;

    public DefaultST() {
        map = new HashMap<>();
    }


    public void put(Key key, Value value) {
        if (key == null) {
            throw new RuntimeException("can't put a null key");
        }

        if (value == null) {
            map.remove(key);
            return;
        }

        map.put(key, value);
    }

    public Value get(Key key) {
        if (key == null) throw new RuntimeException("can't get from null key");
        return map.get(key);
    }

    public void delete(Key key) {
        put(key, null);
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return map.size();
    }

    public Iterable<Key> keys() {
        return map.keySet();
    }

    public static void main(String[] args) {
        ST<String, Integer> st = new DefaultST<>();

        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }

        for (String s : st.keys()) {
            StdOut.println(s + " " + st.get(s));
        }
    }
}
