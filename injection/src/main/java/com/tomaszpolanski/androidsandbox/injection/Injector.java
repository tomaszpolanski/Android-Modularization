package com.tomaszpolanski.androidsandbox.injection;

import android.support.annotation.NonNull;

public interface Injector<T> {

    @NonNull
    T component();

    void inject();
}
