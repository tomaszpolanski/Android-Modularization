package com.tomaszpolanski.androidsandbox;

import com.tomaszpolanski.androidsandbox.injection.activity.BaseActivityModule;
import com.tomaszpolanski.androidsandbox.injection.app.Application;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface SandboxApplicationComponent extends ISecondFeatureAppComponent {

    android.app.Application getApplication();

    @Application
    Context getApplicationContext();

    MainActivityComponent plusMainActivity(BaseActivityModule splashActivityModule);

    void inject(final SandboxApplication application);
}
