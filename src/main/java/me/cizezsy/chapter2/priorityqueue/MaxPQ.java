package me.cizezsy.chapter2.priorityqueue;

public class MaxPQ<Key extends Comparable<Key>> {
    private static final int DEFAULT_SIZE = 20;

    private int N = 0;
    private Key[] pq;

    public MaxPQ() {
        pq = (Key[]) new Comparable[DEFAULT_SIZE + 1];
    }

    public MaxPQ(int max) {
        pq = (Key[]) new Comparable[max + 1];
    }

    public MaxPQ(Key[] a) {
        pq = (Key[]) new Comparable[a.length + 1];
        System.arraycopy(a, 0, pq, 1, a.length);
    }

    public void insert(Key v) {
        pq[++N] = v;
        swim(N);
        if (N == 1 || less(N, 0)) {
            pq[0] = v;
        }
    }

    public Key max() {
        return pq[1];
    }

    private Key min() {
        return pq[0];
    }

    public Key delMax() {
        Key v = pq[1];
        exch(1, N);
        pq[N--] = null;
        sink(1);
        return v;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k / 2, k);
            k = k / 2;
        }
    }

    private void sink(int k) {
        Key origin = pq[k];
        while (k * 2 <= N) {
            int j = 2 * k;
            if (j < N && less(j, j + 1)) j++;
            if (origin.compareTo(pq[j]) >= 0) break;
            pq[k] = pq[j];
            k = j;
        }
        pq[k] = origin;
    }

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(int i, int j) {
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }


    public static void main(String[] args) {
        MaxPQ<String> pq = new MaxPQ<>();
        pq.insert("t");
        pq.insert("d");
        pq.insert("a");
        pq.insert("z");
        pq.insert("s");
        pq.insert("y");

        pq.delMax();
        System.out.println(pq.min());
    }
}
