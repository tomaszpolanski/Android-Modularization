package com.tomaszpolanski.androidsandbox.injection;

import com.tomaszpolanski.androidsandbox.providers.INavigator;
import com.tomaszpolanski.androidsandbox.providers.IResourceProvider;
import com.tomaszpolanski.androidsandbox.providers.Navigator;
import com.tomaszpolanski.androidsandbox.providers.ResourceProvider;

import android.content.Context;
import android.support.annotation.NonNull;

import toothpick.config.Module;

public class ActivityModule extends Module {

    public ActivityModule(@NonNull final Context context) {
        bind(IResourceProvider.class).toInstance(new ResourceProvider(context));
        bind(INavigator.class).toInstance(new Navigator(context));
    }
}
