package me.cizezsy.chapter2;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;
import me.cizezsy.chapter2.elementraysort.*;
import me.cizezsy.chapter2.mergesort.BottomUpMerge;
import me.cizezsy.chapter2.mergesort.FasterMerge;
import me.cizezsy.chapter2.mergesort.ImproveMerge;
import me.cizezsy.chapter2.mergesort.Merge;

import java.util.Scanner;

public class SortCompare {

    public static double time(String alg, Comparable[] a) {
        Stopwatch stopwatch = new Stopwatch();
        switch (alg) {
            case "Insertion":
                Insertion.sort(a);
                break;
            case "SentinelInsertion":
                SentinelInsertion.sort(a);
                break;
            case "NoExchInsertion":
                NoExchInsertion.sort(a);
                break;
            case "Selection":
                Selection.sort(a);
            case "Shell":
                Shell.sort(a);
                break;
            case "Merge":
                Merge.sort(a);
                break;
            case "BottomUpMerge":
                BottomUpMerge.sort(a);
                break;
            case "FasterMerge":
                FasterMerge.sort(a);
            case "ImproveMerge":
                ImproveMerge.sort(a);
            default:
                break;
        }
        return stopwatch.elapsedTime();
    }

    public static double timeRandomInput(String alg, int N, int T) {
        double total = 0.0;
        Double[] a = new Double[N];
        for (int t = 0; t < T; t++) {
            for (int i = 0; i < N; i++) {
                a[i] = StdRandom.uniform();
            }
            total += time(alg, a);
        }
        return total;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String alg1 = scanner.next();
        String alg2 = scanner.next();
        int N = scanner.nextInt();
        int T = scanner.nextInt();

        double t1 = timeRandomInput(alg1, N, T);
        double t2 = timeRandomInput(alg2, N, T);

        StdOut.printf("For %d random Doubles\n   %s is", N, alg1);
        StdOut.printf(" %.1f times faster than %s\n", t2 / t1, alg2);
    }
}
