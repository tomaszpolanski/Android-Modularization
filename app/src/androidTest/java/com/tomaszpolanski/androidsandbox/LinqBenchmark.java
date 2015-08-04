package com.tomaszpolanski.androidsandbox;

import com.tomaszpolanski.androidsandbox.utils.Linq;
import com.tomaszpolanski.androidsandbox.utils.SimpleTestCase;

public class LinqBenchmark extends SimpleTestCase {

    private static final int ITEM_COUNT = 1000000;

    public void testRange() throws Exception {
        Linq.range(0, ITEM_COUNT);
    }

    public void testMap() throws Exception {
        Linq.range(0, ITEM_COUNT).map(it -> it + 1);
    }
}
