package com.tomaszpolanski.androidsandbox;

import com.tomaszpolanski.androidsandbox.injection.app.Application;
import com.tomaszpolanski.androidsandbox.injection.app.BaseApplicationModule;
import com.tomaszpolanski.androidsandbox.providers.INavigator;
import com.tomaszpolanski.androidsandbox.providers.IResourceProvider;
import com.tomaszpolanski.androidsandbox.providers.Navigator;
import com.tomaszpolanski.androidsandbox.providers.ResourceProvider;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module(includes = {BaseApplicationModule.class})
class ApplicationModule {

    @Provides
    IResourceProvider provideResourceProvider(@Application final Context context) {
        return new ResourceProvider(context);
    }

    @Provides
    INavigator provideNavigator(@Application final Context context) {
        return new Navigator(context);
    }
}
