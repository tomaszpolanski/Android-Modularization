package com.tomaszpolanski.androidsandbox.utils;

import android.test.InstrumentationTestCase;

@SuppressWarnings("unchecked")
public class SimpleTestCase extends InstrumentationTestCase {
    public void assertNotEquals(java.lang.Object expected, java.lang.Object actual) {
        assertFalse(String.format("The object <%S> and <%S> are the equal", expected.toString(), actual.toString()),
                expected.equals(actual));
    }



}
