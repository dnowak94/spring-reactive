package com.example.reactiveclient;

import java.text.DecimalFormat;

public class Utils {
    public static String calcDuration(long startTime) {
        long duration = (System.nanoTime() - startTime) / 1000 / 1000;
        if (duration < 1000)
            return "" + duration + " ms";
        return (new DecimalFormat("#,##0.00")).format(duration / 1000.) + " s";

    }

    public static String durationToString(long duration) {
        if (duration < 1000)
            return "" + duration + " ms";
        return (new DecimalFormat("#,##0.00")).format(duration / 1000.) + " s";
    }
}
