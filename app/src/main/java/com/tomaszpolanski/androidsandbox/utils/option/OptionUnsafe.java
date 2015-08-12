package com.tomaszpolanski.androidsandbox.utils.option;

import android.support.annotation.NonNull;

public final class OptionUnsafe {

    private OptionUnsafe() {}

    @NonNull
    public static <T> T getUnsafe(@NonNull final Option<T> option) {
        return option.getUnsafe();
    }
}
