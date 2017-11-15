package me.cizezsy.chapter2.priorityqueue;

import edu.princeton.cs.algs4.StdOut;
import me.cizezsy.chapter2.util.ArrayUtils;

public class HeapSort {

    public static void sort(Comparable[] a) {
        int N = a.length - 1;
        for (int i = N / 2; i >= 1; i--) {
            sink(a, i, N);
        }
        while (N > 1) {
            exch(a, 1, N--);
            sink(a, 1, N);
        }
    }

    private static void sink(Comparable[] a, int k, int N) {
        while (k <= N / 2) {
            int j = k * 2;
            if (j < N && less(a[j], a[j + 1])) j++;
            if (!less(a[k], a[j])) break;
            exch(a, k, j);
            k = j;
        }
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable v = a[i];
        a[i] = a[j];
        a[j] = v;
    }

    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }


    public static void main(String[] args) {
        Double[] a = new Double[201];
        a[0] = 0d;
        System.arraycopy(ArrayUtils.randomDoubleArray(200), 0, a, 1, a.length - 1);
        sort(a);
        System.out.println(isSorted(a));
        show(a);
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
}
