package com.tomaszpolanski.androidsandbox.utils;

import java.util.Arrays;
import java.util.List;

public final class StringUtils {
    public static final String EMPTY = "";

    public static Linq<String> split(final String source, final String separator) {
        List<String> list = Arrays.asList(source.split(separator));
        return Linq.create(list.iterator());
    }
}
