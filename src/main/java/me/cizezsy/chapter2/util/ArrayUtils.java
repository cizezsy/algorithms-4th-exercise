package me.cizezsy.chapter2.util;

import edu.princeton.cs.algs4.StdRandom;

public class ArrayUtils {

    public static Double[] randomDoubleArray(int length) {
        Double[] a = new Double[length];
        for (int i = 0; i < length; i++) {
            a[i] = StdRandom.uniform();
        }
        return a;
    }

}
