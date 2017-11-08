package me.cizezsy.chapter2;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;
import me.cizezsy.chapter2.util.VisualUtils;

public class VisualInsertion {


    public static void sort(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = i; j > 0 && less(a[j], a[j - 1]); j--) {
                show(a, j, j - 1);
                exch(a, j, j - 1);
            }
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
        Double[] a = new Double[200];
        for (int i = 0; i < 200; i++) {
            a[i] = StdRandom.uniform();
        }
        sort(a);
        assert isSorted(a);
        show(a);
    }
}
