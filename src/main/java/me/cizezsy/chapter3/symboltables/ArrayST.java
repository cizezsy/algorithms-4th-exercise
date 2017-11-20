package me.cizezsy.chapter3.symboltables;

import java.util.HashSet;
import java.util.Set;

public class ArrayST<Key extends Comparable<Key>, Value> extends ST<Key, Value> {

    private int capacity;
    private int N;
    private Item<Key, Value>[] items;

    public ArrayST() {
        capacity = 20;
        N = 0;
        items = new Item[capacity];
    }

    @Override
    public void put(Key key, Value value) {
        if (key == null) {
            throw new RuntimeException("key can't be null");
        }

        int i = getItem(key);
        if (i != -1) {
            Item<Key, Value> item = items[i];
            item.val = value;
            return;
        }
        items[N++] = new Item<>(key, value);
        if (N == capacity) {
            resize(capacity * 2);
        }

    }

    @Override
    public Value get(Key key) {
        int i = getItem(key);

        if (i == -1) return null;

        Item<Key, Value> item = items[i];
        for (int j = i; j > 0; j--) {
            items[j] = items[j - 1];
        }

        items[0] = item;
        return item.val;
    }

    private int getItem(Key key) {
        for (int i = 0; i < N; i++) {
            if (items[i].key.equals(key))
                return i;
        }
        return -1;
    }

    @Override
    public void delete(Key key) {
        for (int i = 0; i < N; i++) {
            if (items[i].key.equals(key)) {
                for (int j = i; j < N - 1; j++) {
                    items[j] = items[j + 1];
                }
                items[--N] = null;
                if (N <= capacity / 3)
                    resize(capacity / 2);
            }
        }
    }

    private void resize(int capacity) {
        Item<Key, Value>[] nItems = new Item[capacity * 2];
        System.arraycopy(items, 0, nItems, 0, N);
        items = nItems;
        this.capacity = capacity;
    }


    @Override
    public int size() {
        return N;
    }

    @Override
    public Iterable<Key> keys() {
        return keySet();
    }

    public Set<Key> keySet() {
        Set<Key> set = new HashSet<>();
        for (int i = 0; i < N; i++) {
            set.add(items[i].key);
        }
        return set;
    }

    private void exch(Item<Key, Value>[] items, int i, int j) {
        Item t = items[i];
        items[i] = items[j];
        items[j] = t;
    }


    public class Item<K extends Comparable, V> {
        K key;
        V val;

        public Item(K key, V val) {
            this.key = key;
            this.val = val;
        }

        public Item() {
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getVal() {
            return val;
        }

        public void setVal(V val) {
            this.val = val;
        }
    }
}
