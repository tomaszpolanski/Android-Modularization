package com.tomaszpolanski.androidsandbox;

import com.tomaszpolanski.androidsandbox.injection.app.Application;
import com.tomaszpolanski.androidsandbox.injection.app.BaseApplicationComponent;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = ApplicationModule.class)
@Singleton
public interface SandboxApplicationComponent extends BaseApplicationComponent {

    android.app.Application getApplication();

    @Application
    Context getApplicationContext();

    void inject(final SandboxApplication application);
}
