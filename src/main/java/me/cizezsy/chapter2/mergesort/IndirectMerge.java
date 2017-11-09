package me.cizezsy.chapter2.mergesort;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 2.2.20
 */
public class IndirectMerge {

    public static int[] sort(Comparable[] a) {
        int[] perm = new int[a.length];
        for(int i = 0; i < a.length; i++) {
            perm[i] = i;
        }
        int[] aux = new int[a.length];
        sort(a, perm, aux, 0, a.length - 1);
        return perm;
    }

    private static void sort(Comparable[] a, int[] perm, int[] aux, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;

        sort(a, perm, aux, lo, mid);
        sort(a, perm, aux, mid + 1, hi);
        merge(a, perm, aux, lo, mid, hi);
    }

    private static void merge(Comparable[] a, int[] perm, int[] aux, int lo, int mid, int hi) {
        System.arraycopy(perm, lo, aux, lo, hi + 1 - lo);

        for (int i = lo, j = mid + 1, k = lo; k <= hi; k++) {
            if (i > mid)
                perm[k] = aux[j++];
            else if (j > hi)
                perm[k] = aux[i++];
            else if (less(a[aux[i]], a[aux[j]]))
                perm[k] = aux[i++];
            else
                perm[k] = aux[j++];

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

    private static void show(Comparable[] a, int[] perm) {
        for (int i = 1; i < a.length; i++) {
            StdOut.print(a[perm[i]] + " ");
        }
        StdOut.println();
    }

    public static boolean isSorted(Comparable[] a, int[] perm) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[perm[i]], a[perm[i - 1]])) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Double[] a = new Double[200];
        for (int i = 0; i < a.length; i++) {
            a[i] = StdRandom.uniform();
        }
        int[] perm = sort(a);
        System.out.println(isSorted(a, perm));
        show(a, perm);
    }
}
