package me.cizezsy.chapter2.mergesort;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.LinkedList;
import java.util.List;

/**
 *  time complexity is 4N + 3Nlg3N ~ NlgN
 */
public class Triplicates {
    private static int[] sort(List<Comparable> a) {
        int[] perm = new int[a.size()];
        int[] aux = new int[a.size()];

        for (int i = 0; i < a.size(); i++) {
            perm[i] = i;
        }

        sort(a, perm, aux, 0, a.size() - 1);
        return perm;
    }

    private static void sort(List<Comparable> a, int[] perm, int[] aux, int lo, int hi) {
        if (hi <= lo)
            return;
        int mid = lo + (hi - lo) / 2;

        sort(a, perm, aux, lo, mid);
        sort(a, perm, aux, mid + 1, hi);
        merge(a, perm, aux, lo, mid, hi);
    }

    private static void merge(List<Comparable> a, int[] perm, int[] aux, int lo, int mid, int hi) {
        System.arraycopy(perm, lo, aux, lo, hi - lo + 1);

        for (int i = lo, j = mid+1, k = lo; k <= hi; k++) {
            if (i > mid)
                perm[k] = aux[j++];
            else if (j > hi)
                perm[k] = aux[i++];
            else if (less(a.get(aux[i]), a.get(aux[j])))
                perm[k] = aux[i++];
            else
                perm[k] = aux[j++];
        }
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }


    private static int firstTriplicates(LinkedList<Comparable> a, LinkedList<Comparable> b, LinkedList<Comparable> c) {
        a.addAll(b);
        a.addAll(c);
        int[] perm = sort(a);
        show(a, perm);
        return findTriplicates(a, perm, b.size());
    }

    private static int findTriplicates(LinkedList<Comparable> a, int[] perm, int eachSize) {
        int min = a.size() + 99;
        for (int i = 0; i < perm.length - 2; i++) {
            if (a.get(perm[i]).equals(a.get(perm[i + 1])) && a.get(perm[i]).equals(a.get(perm[i + 2]))) {
                int theseMin = min;
                int result = 0;
                Comparable item = a.get(perm[i]);
                while (i < perm.length - 1 && a.get(perm[i]).equals(item)) {
                    if (perm[i] % eachSize <= theseMin % eachSize)
                        theseMin = perm[i];
                    switch (perm[i] / eachSize) {
                        case 0:
                            result |= 1;
                            break;
                        case 1:
                            result |= 2;
                            break;
                        case 2:
                            result |= 4;
                            break;
                    }
                    i++;
                }
                if(result == 7 && theseMin < min) {
                    min = theseMin;
                }
            }
        }
        return min == a.size() + 99?-1:min;
    }

    private static void show(LinkedList<Comparable> a, int[] perm) {
        for(int i : perm) {
            StdOut.print(a.get(i) + " ");
        }
        StdOut.println();
    }

    public static void main(String[] args) {
        int n = 100;
        LinkedList<Comparable> a = new LinkedList<>();
        LinkedList<Comparable> b = new LinkedList<>();
        LinkedList<Comparable> c = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            a.add(StdRandom.uniform(n * 5));
            b.add(StdRandom.uniform(n * 5));
            c.add(StdRandom.uniform(n * 5));
        }
        int result = firstTriplicates(a, b, c);

        if(result == -1) {
            StdOut.println("Not find Triplicates");
        } else {
            StdOut.printf("The first Triplicates index is %d, value is %d", result, a.get(result));
        }
    }

}
