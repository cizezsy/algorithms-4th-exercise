package me.cizezsy.chapter3.symboltables;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Collections;
import java.util.TreeMap;

public class OrderedST<Key extends Comparable<Key>, Value> extends ST<Key, Value> {

    public OrderedST() {
        map = new TreeMap<>();
    }

    public Key min() {
        return ((TreeMap<Key, Value>) map).firstKey();
    }

    public Key max() {
        return ((TreeMap<Key, Value>) map).lastKey();
    }

    public Key floor(Key key) {
        return ((TreeMap<Key, Value>) map).floorKey(key);
    }

    public Key ceiling(Key key) {
        return ((TreeMap<Key, Value>) map).ceilingKey(key);
    }

    public int rank(Key key) {
        return ((TreeMap<Key, Value>) map).tailMap(key).size();
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
        return ((TreeMap<Key, Value>) map).subMap(lo, hi).keySet();
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
