package com.tomaszpolanski.androidsandbox.common;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public final class Preconditions {

    @NonNull
    public static <T> T get(@Nullable final T value) {
        if (value == null) {
            throw new NullPointerException("Value cannot be null.");
        }
        return value;
    }
}
