package me.cizezsy.chapter2.util;

import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;

public class VisualUtils {

    public static void drawArray(Double[] a) {
        drawArray(a, -1, -1);
    }

    public static void drawArray(Double[] a, int m, int n) {
        StdDraw.enableDoubleBuffering();
        for (int i = 0; i < a.length; i++) {
            if (i == m || i == n)
                StdDraw.setPenColor(Color.RED);
            else
                StdDraw.setPenColor(Color.GRAY);
            drawRectangle(a, i, a[i]);
        }
        StdDraw.pause(50);
        StdDraw.show();
        StdDraw.clear();
    }

    public static void drawRectangle(Double[] a, double x, double y) {
        x = x / a.length;
        y = y / 2;
        double rw = 0.5 / a.length;
        StdDraw.filledRectangle(x, y, rw, y);
    }

}
