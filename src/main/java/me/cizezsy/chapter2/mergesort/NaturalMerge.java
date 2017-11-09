package me.cizezsy.chapter2.mergesort;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class NaturalMerge {

    public static void sort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        while (true) {
            int start = 0;
            while (start < a.length) {
                int first = nextNatural(a, start);
                if (first == a.length)
                    break;
                int second = nextNatural(a, first);
                merge(a, aux, start, first - 1, second - 1);
                start = second;
            }
            if (start == 0)
                break;
        }
    }

    private static int nextNatural(Comparable[] a, int start) {
        Comparable prev = a[start];
        for (int i = start; i < a.length; i++) {
            if (less(a[i], prev))
                return i;
            prev = a[i];
        }
        return a.length;
    }

    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        System.arraycopy(a, lo, aux, lo, hi + 1 - lo);

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
        Double[] a = new Double[200];
        for (int i = 0; i < a.length; i++) {
            a[i] = StdRandom.uniform();
        }
        sort(a);
        System.out.println(isSorted(a));
        show(a);
    }
}
