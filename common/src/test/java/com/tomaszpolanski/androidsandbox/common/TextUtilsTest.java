package com.tomaszpolanski.androidsandbox.common;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TextUtilsTest {

    @Test
    public void isEmpty_WhenStringIsNull() {
        assertThat(TextUtils.isEmpty(null)).isTrue();
    }

    @Test
    public void isEmpty_WhenStringIsEmpty() {
        assertThat(TextUtils.isEmpty("")).isTrue();
    }

    @Test
    public void isEmpty_WhenStringIsNotEmpty() {
        assertThat(TextUtils.isEmpty("not empty")).isFalse();
    }

}