package me.cizezsy.chapter3.binarysearchtrees;

import me.cizezsy.chapter3.symboltables.ST;

public abstract class OrderedST<Key extends Comparable<Key>, Value> extends ST<Key, Value> {

    public OrderedST() {
    }


    public abstract Key min();

    public abstract Key max();

    public abstract Key floor(Key key);

    public abstract Key ceiling(Key key);

    public abstract int rank(Key key);

    public abstract Key select(int k);

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

    public abstract Iterable<Key> keys(Key lo, Key hi);

    @Override
    public Iterable<Key> keys() {
        return keys(min(), max());
    }
}

