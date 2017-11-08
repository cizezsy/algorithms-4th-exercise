package me.cizezsy.chapter2.mergesort;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * 2.2.11
 */
public class ImproveMerge {
    public static void sort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        System.arraycopy(a, 0, aux, 0, a.length);
        sort(a, aux, 0, a.length - 1);
    }

    public static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (hi - lo <= 10) {
            for (int i = lo; i < hi; i++) {
                int j = i + 1;
                Comparable origin = a[j];
                for (; j > lo && less(origin, a[j - 1]); j--) {
                    a[j] = a[j - 1];
                }
                a[j] = origin;
            }
            return;
        }

        int mid = lo + (hi - lo) / 2;

        sort(aux, a, lo, mid);
        sort(aux, a, mid + 1, hi);


        if (less(aux[mid + 1], aux[mid]))
            merge(a, aux, lo, mid, hi);
        else
            System.arraycopy(aux, lo, a, lo, hi - lo + 1);
    }


    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {

        for (int i = lo, j = mid + 1, k = lo; k <= hi; k++) {
            if (i > mid)
                a[k] = aux[j++];
            else if (j > hi)
                a[k] = aux[i++];
            else if (less(aux[i], aux[j]))
                a[k] = aux[i++];
            else
                a[k] = aux[j++];
        }

    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static void show(Comparable[] a) {
        for (Comparable c : a) {
            StdOut.print(c + " ");
        }
        StdOut.println();
    }

    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1])) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        sort(a);
        assert isSorted(a);
        show(a);
    }
}
