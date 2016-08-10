package com.tomaszpolanski.androidsandbox.injection.app;

import com.tomaszpolanski.androidsandbox.injection.Injector;

import android.app.Application;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public abstract class BaseApplication<T> extends Application implements Injector<T> {

    @Nullable
    protected T component;

    @CallSuper
    @Override
    public void onCreate() {
        super.onCreate();
        inject();
    }

    @NonNull
    @Override
    public T component() {
        if (component == null) {
            component = createComponent();
        }
        return component;
    }

    @NonNull
    protected abstract T createComponent();

}
