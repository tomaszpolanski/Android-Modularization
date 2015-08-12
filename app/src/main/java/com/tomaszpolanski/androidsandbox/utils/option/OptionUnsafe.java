package com.tomaszpolanski.androidsandbox.utils.option;

import android.support.annotation.NonNull;

/**
 * Helper class allowing unsafe operations on Option
 */
public final class OptionUnsafe {

    private OptionUnsafe() {}

    /**
     * ATTENTION: Only use it when you know what you are doing!
     *
     * Returns inner value of option if it is Some, otherwise will throw uncatchable exception
     * @param option Option that will be unwrapped
     * @return Value of Some orResult if None, throws exception
     */
    @NonNull
    public static <T> T getUnsafe(@NonNull final Option<T> option) {
        return option.getUnsafe();
    }
}
