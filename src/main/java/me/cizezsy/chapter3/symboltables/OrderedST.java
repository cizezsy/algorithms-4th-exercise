package me.cizezsy.chapter3.symboltables;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Collections;
import java.util.TreeMap;

public class OrderedST<Key extends Comparable<Key>, Value> extends ST<Key, Value> {

    private TreeMap<Key, Value> map;

    public OrderedST() {
        map = new TreeMap<>();
    }

    @Override
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

    @Override
    public Value get(Key key) {
        if (key == null) throw new RuntimeException("can't get from null key");
        return map.get(key);
    }

    @Override
    public int size() {
        return map.size();
    }

    public Key min() {
        return map.firstKey();
    }

    public Key max() {
        return map.lastKey();
    }

    public Key floor(Key key) {
        return map.floorKey(key);
    }

    public Key ceiling(Key key) {
        return map.ceilingKey(key);
    }

    public int rank(Key key) {
        return map.tailMap(key).size();
    }

    public Key select(int k) {
        return (Key) Collections.singletonList((TreeMap<Key, Value>) map.keySet()).get(k);
    }

    public void deleteMin() {
        delete(min());
    }

    public void deleteMax() {
        delete(max());
    }

    public int size(Key lo, Key hi) {
        if (hi.compareTo(lo) < 0)
            return 0;
        else if (contains(hi))
            return rank(hi) - rank(lo) + 1;
        else
            return rank(hi) - rank(lo);
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        return map.subMap(lo, hi).keySet();
    }

    @Override
    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    public static void main(String[] args) {
        ST<String, Integer> st = new OrderedST<>();

        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }

        for (String s : st.keys()) {
            StdOut.println(s + " " + st.get(s));
        }
    }
}
