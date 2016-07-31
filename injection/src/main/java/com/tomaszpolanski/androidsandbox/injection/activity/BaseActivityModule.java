package com.tomaszpolanski.androidsandbox.injection.activity;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;

@Module
public class BaseActivityModule {

    private final Activity activity;

    public BaseActivityModule(@NonNull final Activity activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    @ForActivity
    Context provideActivityContext() {
        return activity;
    }

    @Provides
    @PerActivity
    Activity provideActivity() {
        return activity;
    }

}
