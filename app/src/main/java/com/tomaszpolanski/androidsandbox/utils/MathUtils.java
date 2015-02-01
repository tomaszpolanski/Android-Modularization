package com.tomaszpolanski.androidsandbox.utils;

public final class MathUtils {

    public static boolean areEqual(final double first, final double second, final double difference) {
        return Math.abs(Math.abs(first) - Math.abs(second)) <= difference;
    }
}
