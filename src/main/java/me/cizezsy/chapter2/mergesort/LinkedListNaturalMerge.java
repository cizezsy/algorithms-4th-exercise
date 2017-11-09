package me.cizezsy.chapter2.mergesort;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.LinkedList;
import java.util.List;

public class LinkedListNaturalMerge {

    public static void sort(LinkedList<Comparable> a) {
        while (true) {
            int start = 0;
            while (start < a.size()) {
                int first = nextNatural(a, start);
                if (first == a.size())
                    break;
                int second = nextNatural(a, first);
                merge(a, start, first - 1, second - 1);
                start = second;
            }
            if (start == 0)
                break;
        }
    }

    private static int nextNatural(List<Comparable> a, int start) {
        Comparable prev = a.get(start);
        for (int i = start; i < a.size(); i++) {
            if (less(a.get(i), prev))
                return i;
            prev = a.get(i);
        }
        return a.size();
    }

    private static void merge(LinkedList<Comparable> a, int lo, int mid, int hi) {
        int j = hi;
        List<Comparable> result = new LinkedList<>();

        for (int k = lo; k < j; k++) {
            if (hi == mid) {
                result.add(a.remove(lo));
                mid--;
            } else if (lo - 1 == mid) {
                result.add(a.remove(mid + 1));
            } else if (less(a.get(lo), a.get(mid + 1))) {
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

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static void show(List<Comparable> a) {
        for (Comparable c : a) {
            StdOut.print(c + " ");
        }
        StdOut.println();
    }

    public static boolean isSorted(List<Comparable> a) {
        for (int i = 1; i < a.size(); i++) {
            if (less(a.get(i), a.get(i - 1))) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        LinkedList<Comparable> a = new LinkedList<>();
        for (int i = 0; i < 200; i++) {
            a.add(StdRandom.uniform());
        }
        sort(a);
        System.out.println(isSorted(a));
        show(a);
    }
}
