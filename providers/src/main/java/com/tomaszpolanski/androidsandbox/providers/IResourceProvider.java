package com.tomaszpolanski.androidsandbox.providers;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

public interface IResourceProvider {

    @NonNull
    String getString(@StringRes final int id);
}
