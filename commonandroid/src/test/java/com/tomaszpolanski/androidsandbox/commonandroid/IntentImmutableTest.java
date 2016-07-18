package com.tomaszpolanski.androidsandbox.commonandroid;

import org.junit.Test;

import polanski.option.Option;
import polanski.option.OptionUnsafe;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;
import static android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP;
import static org.assertj.core.api.Assertions.assertThat;
import static polanski.option.Option.none;
import static polanski.option.OptionUnsafe.getUnsafe;

public class IntentImmutableTest {

    @Test
    public void testIntentImmutable_withFlags() {
        final int flags = FLAG_ACTIVITY_CLEAR_TOP |
                          FLAG_ACTIVITY_SINGLE_TOP;

        final IntentImmutable b = new IntentImmutable.Builder()
                .withFlags(flags)
                .build();

        assertThat(b.flags()).isEqualTo(flags);
    }

    @Test
    public void testIntentImmutable_withExtra() {
        final String firstKey = "FirstKey";
        final Object firstValue = "FirstValue";
        final String secondKey = "SecondKey";
        final Object secondValue = "SecondValue";
        final IntentImmutable b = new IntentImmutable.Builder()
                .withExtra(firstKey, firstValue)
                .withExtra(secondKey, secondValue)
                .build();

        final Option<Object> first = b.extra(firstKey);
        assertThat(first).isNotEqualTo(none());
        assertThat(getUnsafe(first)).isEqualTo(firstValue);
        final Option<Object> second = b.extra(secondKey);
        assertThat(second).isNotEqualTo(none());
        assertThat(getUnsafe(second)).isEqualTo(secondValue);
    }

    @Test
    public void testIntentImmutable_withNotFoundExtra() {
        final String firstKey = "FirstKey";
        final Object firstValue = "FirstValue";
        final IntentImmutable b = new IntentImmutable.Builder()
                .withExtra(firstKey, firstValue)
                .build();

        final Option<Object> first = b.extra("SomeKey");
        assertThat(first).isEqualTo(none());
    }

    @Test
    public void testEquals_withEqualIntents() {
        final String firstKey = "FirstKey";
        final Object firstValue = "FirstValue";
        final IntentImmutable b1 = new IntentImmutable.Builder()
                .withExtra(firstKey, firstValue)
                .build();
        final IntentImmutable b2 = new IntentImmutable.Builder()
                .withExtra(firstKey, firstValue)
                .build();

        assertThat(b1).isEqualTo(b2);
    }

    @Test
    public void testEquals_withNotEqualIntents() {
        final String firstKey = "FirstKey";
        final Object firstValue = "FirstValue";

        final IntentImmutable b1 = new IntentImmutable.Builder()
                .withExtra(firstKey, firstValue)
                .build();

        final IntentImmutable b2 = new IntentImmutable.Builder()
                .withExtra(firstKey, firstValue)
                .withExtra("SecondKey", "SecondValue")
                .build();

        assertThat(b1).isNotEqualTo(b2);
    }

    @Test
    public void testIntentImmutable_withNoData() {
        final IntentImmutable intent = new IntentImmutable.Builder()
                .build();

        assertThat(intent.data()).isEqualTo(none());
    }

    @Test
    public void testIntentImmutable_withData() {
        final String data = "www.something.com";
        final IntentImmutable b1 = new IntentImmutable.Builder()
                .withData(data)
                .build();

        assertThat(b1.data()).isNotEqualTo(none());
        assertThat(OptionUnsafe.getUnsafe(b1.data())).isEqualTo(data);
    }
}
