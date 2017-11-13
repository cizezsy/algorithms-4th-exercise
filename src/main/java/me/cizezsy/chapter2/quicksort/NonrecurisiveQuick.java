package me.cizezsy.chapter2.quicksort;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import me.cizezsy.chapter2.util.ArrayUtils;

import java.util.Stack;

public class NonrecurisiveQuick {
    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);

        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        stack.push(a.length - 1);

        while (true) {
            int hi = stack.pop();
            int lo = stack.pop();

            if(hi > lo) {
                int j = partition(a, lo, hi);
                stack.push(lo);
                stack.push(j);
                stack.push(j+1);
                stack.push(hi);
            }

            if(stack.size() == 0)
                break;

        }

    }

    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi + 1;
        Comparable v = a[lo];
        while (true) {
            while (less(a[++i], v)) if (i == hi) break;
            while (less(v, a[--j])) if (j == lo) break;
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
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
        Double[] a = ArrayUtils.randomDoubleArray(200);
        sort(a);
        System.out.println(isSorted(a));
        show(a);
    }
}
