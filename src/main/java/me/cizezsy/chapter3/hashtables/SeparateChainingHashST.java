package me.cizezsy.chapter3.hashtables;


import me.cizezsy.chapter3.symboltables.ST;
import me.cizezsy.chapter3.symboltables.SequentialSearchST;

public class SeparateChainingHashST<Key, Value> extends ST<Key, Value> {
    private int N;
    private int M;
    private SequentialSearchST<Key, Value>[] st;

    public SeparateChainingHashST() {
        this(997);
    }


    public SeparateChainingHashST(int M) {
        this.M = M;
        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[M];
        for (int i = 0; i < M; i++) {
            st[i] = new SequentialSearchST<>();
        }
    }

    public int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    @Override
    public void put(Key key, Value value) {
        st[hash(key)].put(key, value);
    }

    @Override
    public Value get(Key key) {
        return st[hash(key)].get(key);
    }

    @Override
    public int size() {
        return N;
    }

    @Override
    public Iterable<Key> keys() {
        return null;
    }

    private void resize(int cap) {
        SeparateChainingHashST<Key, Value> t = new SeparateChainingHashST<>(cap);
        for (int i = 0; i < M; i++) {
            for (Key key : st[i].keys())
                t.put(key, st[i].get(key));
        }
        st = t.st;
        M = t.M;
    }

}
