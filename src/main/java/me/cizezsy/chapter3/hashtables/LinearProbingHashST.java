package me.cizezsy.chapter3.hashtables;

import me.cizezsy.chapter3.symboltables.ST;

public class LinearProbingHashST<Key, Value> extends ST<Key, Value> {
    private int M;
    private int N;
    private Value[] values;
    private Key[] keys;

    public LinearProbingHashST() {
        this(16);
    }

    public LinearProbingHashST(int M) {
        this.M = M;
        keys = (Key[]) new Object[M];
        values = (Value[]) new Object[M];
    }

    @Override
    public void put(Key key, Value value) {
        if (N >= M / 2)
            resize(2 * M);
        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % M) {
            if (keys[i].equals(key)) {
                values[i] = value;
                return;
            }
        }

        keys[i] = key;
        values[i] = value;
        N++;
    }

    @Override
    public Value get(Key key) {
        for (int i = hash(key); keys[i] != null; i = (i + 1) % M) {
            if (keys[i].equals(key)) {
                return values[i];
            }
        }
        return null;
    }


    @Override
    public void delete(Key key) {
        if (!contains(key)) return;
        int i = hash(key);
        while (!key.equals(keys[i])) {
            i = (i + 1) % M;
        }
        keys[i] = null;
        values[i] = null;
        i = (i + 1) % M;
        while (keys[i] != null) {
            Key rePutKey = keys[i];
            Value rePutValue = values[i];
            keys[i] = null;
            values[i] = null;
            N--;
            put(rePutKey, rePutValue);
            i = (i + 1) % M;
        }
        N--;
        if (N > 0 && N == M / 8) resize(M / 2);
    }

    @Override
    public int size() {
        return N;
    }

    @Override
    public Iterable<Key> keys() {
        return null;
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    private void resize(int cap) {
        LinearProbingHashST<Key, Value> st = new LinearProbingHashST<>(cap);
        for (int i = 0; i < M; i++) {
            if (keys[i] != null) {
                st.put(keys[i], values[i]);
            }
        }
        keys = st.keys;
        values = st.values;
        M = st.M;
    }
}
