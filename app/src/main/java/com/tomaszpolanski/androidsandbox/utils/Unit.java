package com.tomaszpolanski.androidsandbox.utils;

import android.support.annotation.NonNull;

import rx.functions.Action0;

public final class Unit {
    @NonNull
    public static final Unit DEFAULT = new Unit();

    @NonNull
    public static Unit asUnit(@NonNull final Action0 action) {
        action.call();
        return DEFAULT;
    }

    private Unit() {}
}
