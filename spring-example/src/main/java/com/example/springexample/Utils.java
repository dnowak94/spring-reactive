package com.example.springexample;

import java.text.DecimalFormat;

public class Utils {
    public static long calcDuration(long startTime) {
        return (System.nanoTime() - startTime) / 1000 / 1000;
    }

    public static String durationToString(long duration) {
        if (duration < 1000)
            return "" + duration + " ms";
        return (new DecimalFormat("#,##0.00")).format(duration / 1000.) + " s";
    }
}
