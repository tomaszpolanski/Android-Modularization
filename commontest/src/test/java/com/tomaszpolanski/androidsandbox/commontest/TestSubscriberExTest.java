package com.tomaszpolanski.androidsandbox.commontest;

import org.junit.Test;

import polanski.option.Unit;

import static org.assertj.core.api.Assertions.assertThat;
import static rx.Observable.just;

public class TestSubscriberExTest {

    @Test
    public void testGetLast() {
        String lastElement = "Last";
        TestSubscriberEx<String> ts = new TestSubscriberEx<>();

        ts.onNext("First");
        ts.onNext(lastElement);

        assertThat(ts.getLast()).isEqualTo(lastElement);
    }

    @Test(expected = AssertionError.class)
    public void testGetLast_whenNoItems_throwsException() {
        TestSubscriberEx<String> ts = new TestSubscriberEx<>();

        ts.getLast();
    }

    @Test
    public void testCreate() {
        TestSubscriberEx<Unit> ts = TestSubscriberEx.create(just(Unit.DEFAULT));

        ts.assertValue(Unit.DEFAULT);
    }

    @Test(expected = AssertionError.class)
    public void testAssertValue_whenFails() {
        int value = 1;
        TestSubscriberEx<Integer> ts = TestSubscriberEx.create(just(value));

        ts.assertValue("2", Object::toString);
    }

    @Test
    public void testAssertValue_whenSuccessful() {
        int value = 1;
        TestSubscriberEx<Integer> ts = TestSubscriberEx.create(just(value));

        ts.assertValue(Integer.toString(value), Object::toString);
    }

    @Test(expected = AssertionError.class)
    public void testAssertValue_whenMoreThenOneItem() {
        TestSubscriberEx<Unit> ts = new TestSubscriberEx<>();

        ts.onNext(Unit.DEFAULT);
        ts.onNext(Unit.DEFAULT);

        ts.assertValue(Unit.DEFAULT, it -> it);
    }

    @Test(expected = AssertionError.class)
    public void testAssertValue_whenNoItems() {
        TestSubscriberEx<Unit> ts = new TestSubscriberEx<>();

        ts.assertValue(Unit.DEFAULT, it -> it);
    }

    @Test(expected = AssertionError.class)
    public void testAssertLastValue_whenFails() {
        TestSubscriberEx<Integer> ts = new TestSubscriberEx<>();

        ts.onNext(0);
        ts.onNext(1);

        ts.assertLastValue("2", Object::toString);
    }

    @Test
    public void testAssertLastValue_whenSuccessful() {
        int value = 1;
        TestSubscriberEx<Integer> ts = new TestSubscriberEx<>();

        ts.onNext(0);
        ts.onNext(value);

        ts.assertLastValue(Integer.toString(value), Object::toString);
    }

    @Test(expected = AssertionError.class)
    public void testAssertLastValue_whenNoItems() {
        TestSubscriberEx<Unit> ts = new TestSubscriberEx<>();

        ts.assertLastValue(Unit.DEFAULT, it -> it);
    }
}
