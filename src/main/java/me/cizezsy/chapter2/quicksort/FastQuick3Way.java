package me.cizezsy.chapter2.quicksort;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import me.cizezsy.chapter2.util.ArrayUtils;

public class FastQuick3Way {
    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi - lo <= 10) {
            insertSort(a, lo, hi);
            return;
        }

        int mid = lo + (hi - lo) / 2;
        if (hi - lo > 40) {
            int n = (hi - lo) / 8;
            int m1 = mdeian3(a, lo, lo + n, lo + n + n);
            int m2 = mdeian3(a, mid, mid - n, mid + n);
            int m3 = mdeian3(a, hi, hi - n, hi - n - n);
            mid = mdeian3(a, m1, m2, m3);
        }
        exch(a, lo, mid);


        int p = lo + 1, i = lo, q = hi, j = hi + 1;

        while (true) {
            do {
                if (i++ == q) break;
                int cmp = a[i].compareTo(a[lo]);
                if (cmp == 0) {
                    exch(a, i, p++);
                } else if (cmp > 0) {
                    break;
                }
            } while (true);

            do {
                if (j-- == p) break;
                int cmp = a[j].compareTo(a[lo]);
                if (cmp == 0) {
                    exch(a, j, q--);
                }
                if (cmp < 0) {
                    break;
                }
            } while (true);

            if (i >= j)
                break;
            exch(a, i, j);
        }

        for (int t = lo; t <= p - 1; t++) {
            exch(a, t, j--);
        }

        for (int t = q + 1; t <= hi; t++) {
            exch(a, t, i++);
        }

        sort(a, lo, j);
        sort(a, i, hi);


    }

    private static void insertSort(Comparable[] a, int lo, int hi) {
        for (int i = lo; i < hi; i++) {
            int j = i + 1;
            Comparable origin = a[j];
            for (; j > lo && less(origin, a[j - 1]); j--) {
                a[j] = a[j - 1];
            }
            a[j] = origin;
        }
    }

    private static int mdeian3(Comparable[] a, int i, int j, int k) {
        if (less(a[i], a[j])) {
            if (less(a[j], a[k]))
                return j;
            else if (less(a[i], a[k]))
                return k;
            else
                return i;
        } else {
            if (less(a[i], a[k]))
                return i;
            else if (less(a[j], a[k]))
                return k;
            else
                return j;
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
        for (int i = 0; i < 100; i++) {
            Double[] a = ArrayUtils.randomDoubleArray(100);
            a = org.apache.commons.lang3.ArrayUtils.addAll(a, a);
            StdRandom.shuffle(a);
            sort(a);
            System.out.println(isSorted(a));
            show(a);
        }
    }
}
