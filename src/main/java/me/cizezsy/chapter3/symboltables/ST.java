package me.cizezsy.chapter3.symboltables;

public abstract class ST<Key, Value> {


    public ST() {
    }


    public abstract void put(Key key, Value value);

    public abstract Value get(Key key);

    public void delete(Key key) {
        put(key, null);
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public abstract int size();

    public abstract Iterable<Key> keys();


}
