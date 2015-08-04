package com.tomaszpolanski.androidsandbox;

import com.tomaszpolanski.androidsandbox.utils.Linq;
import com.tomaszpolanski.androidsandbox.utils.SimpleTestCase;
import com.tomaszpolanski.androidsandbox.utils.option.Option;

public class LinqBenchmark extends SimpleTestCase {

    private static final int ITEM_COUNT = 1000000;

    private final Linq<Integer> ITEMS = Linq.range(0, ITEM_COUNT);

    public void testRange() throws Exception {
        Linq.range(0, ITEM_COUNT);
    }

    public void testMap() throws Exception {
        ITEMS.map(it -> it + 1);
    }

    public void testCreate() throws Exception {
        Linq.create(1);
    }

    public void testAll() throws Exception {
        ITEMS.all(it -> it >= 0);
    }

    public void testCount() throws Exception {
        ITEMS.count(it -> it % 2 == 0);
    }

    public void testReverseConcat() throws Exception {
        ITEMS.reverseConcat(ITEMS);
    }

    public void testLast() throws Exception {
        ITEMS.last(it -> it < 0);
    }

    public void testLastElement() throws Exception {
        ITEMS.last();
    }

    public void testFirst() throws Exception {
        ITEMS.first(it -> it < 0);
    }

    public void testFirstElement() throws Exception {
        ITEMS.first();
    }

    public void testGetOption() throws Exception {
        ITEMS.getOption(ITEM_COUNT + 1);
    }

    public void testBuffer() throws Exception {
        ITEMS.buffer(100, 100);
    }

    public void testChoose() throws Exception {
        ITEMS.choose(it -> it % 2 == 0 ? Option.ofObj(it) : Option.NONE);
    }

    public void testChooseStatic() throws Exception {
        Linq.choose(ITEMS, it -> it % 2 == 0 ? Option.ofObj(it) : Option.NONE);
    }
}
