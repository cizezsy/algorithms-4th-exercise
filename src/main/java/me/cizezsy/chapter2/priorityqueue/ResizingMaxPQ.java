package me.cizezsy.chapter2.priorityqueue;

import java.util.Arrays;

public class ResizingMaxPQ<Key extends Comparable<Key>> {
    private static final int DEFAULT_LENGTH = 20;

    private int N = 0;
    private Key[] pq;

    public ResizingMaxPQ() {
        pq = (Key[]) new Comparable[DEFAULT_LENGTH];
    }

    public ResizingMaxPQ(int max) {
        pq = (Key[]) new Comparable[max + 1];
    }

    public ResizingMaxPQ(Key[] a) {
        pq = (Key[]) new Comparable[a.length + 1];
        System.arraycopy(a, 0, pq, 1, a.length);
    }

    public void insert(Key v) {
        pq[++N] = v;
        swim(N);
        if (N == pq.length - 1) {
            pq = Arrays.copyOf(pq, pq.length * 2);
        }
    }

    public Key max() {
        return pq[1];
    }

    public Key delMax() {
        Key v = pq[1];
        exch(1, N);
        pq[N--] = null;
        sink(1);
        if (N == pq.length / 3) {
            pq = Arrays.copyOf(pq, pq.length / 2);
        }
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
        while (k * 2 <= N) {
            int j = 2 * k;
            if (j < N && less(j, j + 1)) j++;
            if (!less(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(int i, int j) {
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }


}
