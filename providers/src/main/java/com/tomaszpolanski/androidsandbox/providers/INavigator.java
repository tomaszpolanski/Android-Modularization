package com.tomaszpolanski.androidsandbox.providers;

import android.support.annotation.NonNull;

public interface INavigator {

    void startActivity(@NonNull final String activity);
}
