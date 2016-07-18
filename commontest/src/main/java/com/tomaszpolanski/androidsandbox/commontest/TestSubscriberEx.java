package com.tomaszpolanski.androidsandbox.commontest;

import android.support.annotation.NonNull;

import rx.Observable;
import rx.functions.Func1;
import rx.observers.TestSubscriber;

/**
 * Extension to the default {@link TestSubscriber}.
 */
public final class TestSubscriberEx<T> extends TestSubscriber<T> {

    /**
     * Create Subscriber and subscribes to the given {@param observable}.
     *
     * @param observable Observable to subscribe the Subscriber to
     */
    @NonNull
    public static <T> TestSubscriberEx<T> create(@NonNull final Observable<T> observable) {
        final TestSubscriberEx<T> ts = new TestSubscriberEx<>();
        observable.subscribe(ts);
        return ts;
    }

    /**
     * Returns last event in the subscriber, otherwise throws assertion exception.
     */
    @NonNull
    public T getLast() {
        if (getOnNextEvents().isEmpty()) {
            throw new AssertionError("No items found in Subscriber");
        }
        return getOnNextEvents().get(getOnNextEvents().size() - 1);
    }

    /**
     * Returns the first event in the subscriber, otherwise throws assertion exception.
     */
    @NonNull
    public T getFirst() {
        if (getOnNextEvents().isEmpty()) {
            throw new AssertionError("No items found in Subscriber");
        }
        return getOnNextEvents().get(0);
    }

    /**
     * Returns the first event in the subscriber, otherwise throws assertion exception.
     */
    @NonNull
    public T getOnlyValue() {
        if (getOnNextEvents().size() != 1) {
            throw new AssertionError(
                    "Items count is not equal 1: actual value " + getOnNextEvents().size());
        }
        return getOnNextEvents().get(0);
    }

    /**
     * Asserts the only value in the subscriber based on {@param actualSelector}.
     *
     * @param expected       Expected value
     * @param actualSelector Selector to get the actual value
     */
    public <R1> void assertValue(R1 expected, Func1<T, R1> actualSelector) {
        if (getOnNextEvents().size() > 1) {
            throw new AssertionError("There is more then one item to assert");
        }
        assertLastValue(expected, actualSelector);
    }

    /**
     * Asserts the last value in the subscriber based on {@param actualSelector}.
     *
     * @param expected       Expected value
     * @param actualSelector Selector to get the actual value
     */
    public <R1> void assertLastValue(R1 expected, Func1<T, R1> actualSelector) {
        R1 value = actualSelector.call(getLast());
        if (!value.equals(expected)) {
            //noinspection ConstantConditions
            throw new AssertionError(
                    String.format("Value expected to be [%s] (%s) but was: [%s] (%s)",
                                  expected, expected.getClass().getSimpleName(), value,
                                  (value != null
                                          ? value.getClass().getSimpleName()
                                          : "null")));
        }
    }
}
