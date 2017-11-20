package me.cizezsy.chapter3.symboltables;

import me.cizezsy.chapter2.mergesort.Merge;
import org.apache.commons.lang3.builder.CompareToBuilder;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ItemBinarySearchST<Key extends Comparable<Key>, Value> extends ST<Key, Value> {

    private int capacity;
    private int N;
    private Item<Key, Value>[] items;

    public ItemBinarySearchST(Item<Key, Value>[] items) {
        this.items = items;
        capacity = items.length * 2;
        N = items.length;
        Merge.sort(items);
    }

    public ItemBinarySearchST() {
        capacity = 20;
        N = 0;
        items = new Item[capacity];
    }

    @Override
    public void put(Key key, Value value) {
        int i = rank(key);

        if (i < N && items[i].compareTo(key) == 0) {
            items[i].val = value;
            return;
        }
        for (int j = N++; j > i; j--) {
            items[j] = items[j - 1];
        }
        items[i] = new Item<>(key, value);

        if (N == capacity)
            resize(capacity * 2);
    }

    @Override
    public Value get(Key key) {
        if (isEmpty()) return null;
        int i = rank(key);
        if (i < N && items[i].compareTo(key) == 0)
            return items[i].val;
        else
            return null;
    }

    public int rank(Key key) {
        int lo = 0, hi = N - 1;
        while (hi >= lo) {
            int mid = lo + (hi - lo) / 2;
            int cmp = items[mid].compareTo(key);

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
        int i = rank(key);
        if (i < N && items[i].compareTo(key) == 0) {
            for (int j = i; j < N - 1; j++) {
                items[j] = items[j + 1];
            }
            items[--N] = null;
            if (N <= capacity / 3)
                resize(capacity / 2);
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

    public class Item<K extends Comparable<K>, V> implements Comparable<Item<K, V>> {
        K key;
        V val;

        public Item(K key, V val) {
            this.key = key;
            this.val = val;
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

        public int compareTo(K o) {
            return key.compareTo(o);
        }

        @Override
        public int compareTo(Item<K, V> o) {
            return CompareToBuilder.reflectionCompare(this, o, "val");
        }
    }
}
