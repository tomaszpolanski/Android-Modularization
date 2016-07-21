package com.tomaszpolanski.androidsandbox;

import android.app.Application;

import toothpick.Configuration;
import toothpick.Scope;
import toothpick.Toothpick;
import toothpick.registries.FactoryRegistryLocator;
import toothpick.registries.MemberInjectorRegistryLocator;
import toothpick.smoothie.module.SmoothieApplicationModule;

public class SandboxApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Configuration.setConfiguration(Configuration.reflectionFree());
        MemberInjectorRegistryLocator
                .setRootRegistry(new com.tomaszpolanski.androidsandbox.MemberInjectorRegistry());
        FactoryRegistryLocator
                .setRootRegistry(new com.tomaszpolanski.androidsandbox.FactoryRegistry());
        Scope appScope = Toothpick.openScope(this);
        appScope.installModules(new SmoothieApplicationModule(this));
    }
}
