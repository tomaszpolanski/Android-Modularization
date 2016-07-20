package com.tomaszpolanski.androidsandbox.injection;

import com.tomaszpolanski.androidsandbox.providers.IResourceProvider;
import com.tomaszpolanski.androidsandbox.providers.ResourceProvider;

import android.content.Context;
import android.support.annotation.NonNull;

import toothpick.config.Module;

public class ActivityModule extends Module {

    public ActivityModule(@NonNull final Context context) {
        bind(IResourceProvider.class).toInstance(new ResourceProvider(context));
    }
}
