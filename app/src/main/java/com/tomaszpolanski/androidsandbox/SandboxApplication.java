package com.tomaszpolanski.androidsandbox;

import com.facebook.stetho.Stetho;
import com.tomaszpolanski.androidsandbox.injection.app.BaseApplication;
import com.tomaszpolanski.androidsandbox.injection.app.BaseApplicationModule;

import android.support.annotation.NonNull;

public class SandboxApplication extends BaseApplication<SandboxApplicationComponent> {

    @Override
    public void onCreate() {
        super.onCreate();
        initStetho();
    }

    @Override
    public void inject() {
        component().inject(this);
    }

    @NonNull
    protected SandboxApplicationComponent createComponent() {
        return DaggerSandboxApplicationComponent.builder()
                                                .baseApplicationModule(
                                                        new BaseApplicationModule(this))
                                                .build();
    }

    private void initStetho() {
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                      .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                      .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                      .build());
    }

}
