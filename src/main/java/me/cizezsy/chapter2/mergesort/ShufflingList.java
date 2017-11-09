package me.cizezsy.chapter2.mergesort;

import edu.princeton.cs.algs4.StdRandom;

import java.util.LinkedList;
import java.util.List;

/**
 * 2.2.18
 */
public class ShufflingList {

    public static void shuff(LinkedList<Comparable> a) {
        shuff(a, 0, a.size() - 1);
    }

    public static void shuff(LinkedList<Comparable> a, int lo, int hi) {
        if (hi <= lo)
            return;
        int mid = lo + (hi - lo) / 2;

        shuff(a, lo, mid);
        shuff(a, mid + 1, hi);
        merge(a, lo, mid, hi);
    }

    public static void merge(LinkedList<Comparable> a, int lo, int mid, int hi) {
        int j = hi;
        List<Comparable> result = new LinkedList<>();

        for (int k = lo; k < j; k++) {
            if (hi == mid) {
                result.add(a.remove(lo));
                mid--;
            } else if (lo - 1 == mid) {
                result.add(a.remove(mid + 1));
            } else if (StdRandom.bernoulli()) {
                result.add(a.remove(lo));
                mid--;
            } else {
                result.add(a.remove(mid + 1));
            }
            hi--;
        }
        while (!result.isEmpty()) {
            a.add(lo++, result.remove(0));
        }
    }

    public static void main(String[] args) {
        LinkedList<Comparable> comparables = new LinkedList<>();
        for (int i = 0; i < 200; i++) {
            comparables.add(StdRandom.uniform());
        }
        shuff(comparables);
    }
}
