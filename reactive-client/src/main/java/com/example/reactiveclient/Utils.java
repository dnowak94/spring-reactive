package com.example.reactiveclient;

import java.text.DecimalFormat;
// source: https://github.com/dnowak94/vislab/blob/master/lab1/lab1-example/src/main/java/de/hska/iwi/vislab/lab1/example/TestWsClient.java
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
