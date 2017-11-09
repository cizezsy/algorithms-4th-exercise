package me.cizezsy.chapter2.mergesort;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.LinkedList;
import java.util.Queue;

public class BUQueueMerge {


    public static Queue<Comparable> sort(Comparable[] a) {
        Queue<Queue<Comparable>> itemQueueQueue = new LinkedList<>();
        for (Comparable anA : a) {
            Queue<Comparable> t = new LinkedList<>();
            t.add(anA);
            itemQueueQueue.add(t);
        }
        while (itemQueueQueue.size() != 1) {
            Queue<Comparable> first = itemQueueQueue.poll();
            Queue<Comparable> second = itemQueueQueue.poll();
            itemQueueQueue.add(merge(first, second));
        }
        return itemQueueQueue.poll();
    }


    private static Queue<Comparable> merge(Queue<Comparable> lo, Queue<Comparable> hi) {
        Queue<Comparable> result = new LinkedList<>();
        while (!lo.isEmpty() && !hi.isEmpty()) {
            if (less(lo.peek(), hi.peek()))
                result.add(lo.poll());
            else
                result.add(hi.poll());
        }
        if (lo.isEmpty()) {
            result.addAll(hi);
        } else {
            result.addAll(lo);
        }
        return result;
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static void show(Queue<Comparable> a) {
        for (Comparable c : a) {
            StdOut.print(c + " ");
        }
        StdOut.println();
    }

    public static boolean isSorted(Queue<Comparable> a) {
        Comparable prev = a.poll();
        while (!a.isEmpty()) {
            Comparable curr = a.poll();
            if (less(curr, prev))
                return false;
            prev = curr;
        }
        return true;
    }

    public static void main(String[] args) {
        Comparable[] a = new Comparable[200];
        for (int i = 0; i < a.length; i++) {
            a[i] = StdRandom.uniform();
        }
        Queue<Comparable> result = sort(a);
        System.out.println(isSorted(result));
        show(result);
    }

}
