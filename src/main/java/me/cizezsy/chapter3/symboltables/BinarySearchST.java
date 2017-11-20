package me.cizezsy.chapter3.symboltables;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchST<Key extends Comparable<Key>, Value> extends ST<Key, Value> {

    private int capacity;
    private Key[] keys;
    private Value[] vals;
    private int N;

    private int recent = 0;


    public BinarySearchST() {
        capacity = 20;
        N = 0;
        this.keys = (Key[]) new Comparable[capacity];
        this.vals = (Value[]) new Object[capacity];
    }

    @Override
    public void put(Key key, Value value) {
        int i;
        if (recent < N && keys[recent].equals(key)) {
            i = recent;
            vals[i] = value;
            return;
        } else if (N > 0 && keys[N - 1].compareTo(key) < 0) {
            keys[N] = key;
            vals[N] = value;
            N++;
            return;
        } else {
            i = rank(key);
            if (i < N && keys[i].compareTo(key) == 0) {
                vals[i] = value;
                return;
            }
        }


        for (int j = N++; j > i; j--) {
            keys[j] = keys[j - 1];
            vals[j] = vals[j - 1];
        }
        keys[i] = key;
        vals[i] = value;

        if (N == capacity)
            resize(capacity * 2);
    }

    @Override
    public Value get(Key key) {
        if (isEmpty()) return null;
        if (recent < N && keys[recent].equals(key)) {
            return vals[recent];
        }
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) {
            return vals[i];
        } else
            return null;
    }

    @Override
    public int size() {
        return N;
    }

    public int rank(Key key) {
        int lo = 0, hi = N - 1;
        while (hi >= lo) {
            int mid = lo + (hi - lo) / 2;
            int cmp = keys[mid].compareTo(key);

            if (cmp == 0) {
                return mid;
            } else if (cmp < 0) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return lo;
    }

    @Override
    public void delete(Key key) {
        int i;
        if (recent < N && keys[recent].equals(key)) {
            i = recent;
            recent = 0;
        } else {
            i = rank(key);
        }
        if (i < N && keys[i].compareTo(key) == 0) {
            for (int j = i; j < N - 1; j++) {
                keys[j] = keys[j + 1];
                vals[j] = vals[j + 1];
            }

            N--;
            keys[N] = null;
            vals[N] = null;
            if (N <= capacity / 3)
                resize(capacity / 2);
        }
    }


    private void resize(int capacity) {
        Key[] nKeys = (Key[]) new Comparable[capacity];
        Value[] nVal = (Value[]) new Object[capacity];

        System.arraycopy(keys, 0, nKeys, 0, N);
        System.arraycopy(vals, 0, nVal, 0, N);

        keys = nKeys;
        vals = nVal;
        this.capacity = capacity;
    }

    public Key min() {
        return keys[0];
    }

    public Key max() {
        return keys[N - 1];
    }

    public Key select(int k) {
        return keys[k];
    }

    public Key ceiling(Key key) {
        int i = rank(key);
        return keys[i];
    }

    public Key floor(Key key) {
        int i = rank(key);
        if (key.compareTo(keys[i]) == 0) {
            return keys[i];
        } else {
            return keys[i - 1];
        }
    }

    @Override
    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> q = new LinkedList<>();
        q.addAll(Arrays.asList(keys).subList(rank(lo), rank(hi)));
        if (contains(hi))
            q.add(keys[rank(hi)]);
        return q;
    }

    public static void main(String[] args) {

    }
}
