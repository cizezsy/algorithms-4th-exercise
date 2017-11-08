package me.cizezsy.chapter2;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import me.cizezsy.chapter2.util.VisualUtils;

public class VisualSelection {

    public static void sort(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            int min = i;
            for (int j = i + 1; j < a.length; j++) {
                if (!less(a[min], a[j])) min = j;
                show(a, min, j);
            }
            exch(a, min, i);
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
        VisualUtils.drawArray((Double[]) a);
    }

    private static void show(Comparable[] a, int m, int n) {
        VisualUtils.drawArray((Double[]) a, m, n);
    }


    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1])) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Double[] a = new Double[250];
        for (int i = 0; i < 250; i++) {
            a[i] = StdRandom.uniform();
        }
        sort(a);
        assert isSorted(a);
        show(a);
    }
}
