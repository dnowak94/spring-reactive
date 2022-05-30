package com.example.nonreactiveclient;

import java.text.DecimalFormat;
// source: https://github.com/dnowak94/vislab/blob/master/lab1/lab1-example/src/main/java/de/hska/iwi/vislab/lab1/example/TestWsClient.java

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
