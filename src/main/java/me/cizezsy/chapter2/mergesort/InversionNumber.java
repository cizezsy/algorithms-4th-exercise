package me.cizezsy.chapter2.mergesort;

/**
 * 2.2.19
 */
public class InversionNumber {

    public static int inversions(Comparable[] a) {
        int count = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = i; j > 0 && less(a[j], a[j - 1]); j--) {
                exch(a, j, j - 1);
                count++;
            }
        }
        return count;
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }


    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }


    public static void main(String[] args) {
        Integer[] nums = {7, 45, 6, 1, 8};
        System.out.println(inversions(nums));
    }
}
