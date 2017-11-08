package me.cizezsy.chapter2.mergesort;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import me.cizezsy.chapter2.elementraysort.Insertion;
import me.cizezsy.chapter2.elementraysort.NoExchInsertion;

/**
 * 2.2.12
 */
public class SublinearMerge {
    public static void sort(Comparable[] a) {
        Comparable[] aux = new Comparable[3];
        Integer[] blocks = new Integer[(a.length + aux.length - 1) / aux.length];

        for (int i = 0; i < blocks.length; i++) {
            blocks[i] = i * aux.length;
            insertSort(a, blocks[i], aux.length);
        }
        for (int t = 0; t < blocks.length; t++) {
            for (int i = 1; i < blocks.length; i++) {
                merge(a, aux, blocks[i - 1], blocks[i]);
            }
        }
        System.out.println(isSorted(a));
    }

    private static void merge(Comparable[] a, Comparable[] aux, int lo, int hi) {
        int auxSize = (lo + aux.length > a.length) ? a.length - lo : aux.length;
        int hiArrSize = (hi + aux.length > a.length) ? a.length - hi : aux.length;

        System.arraycopy(a, lo, aux, 0, auxSize);
        int i = lo, j = hi, k = 0;
        for (; ; i++) {
            if (i - lo == auxSize)
                i = hi;
            if (i == j)
                break;
            if (j >= hi + hiArrSize)
                a[i] = aux[k++];
            else if (less(a[j], aux[k]))
                a[i] = a[j++];
            else
                a[i] = aux[k++];
        }
        System.arraycopy(aux, k, a, i, auxSize - k);
    }

    private static void insertSort(Comparable[] a, int m, int size) {
        for (int i = m; i < m + size && i < a.length; i++) {
            int j = i;
            Comparable origin = a[j];
            for (; j > m && less(origin, a[j - 1]); j--) {
                a[j] = a[j - 1];
            }
            a[j] = origin;
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
        Double[] a = new Double[50];
        for (int i = 0; i < 50; i++) {
            a[i] = StdRandom.uniform();
        }
        sort(a);
        assert isSorted(a);
        show(a);
    }
}
