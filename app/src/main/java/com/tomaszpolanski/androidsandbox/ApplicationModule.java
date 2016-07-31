package com.tomaszpolanski.androidsandbox;

import com.tomaszpolanski.androidsandbox.injection.app.BaseApplicationModule;

import dagger.Module;

@Module(includes = {BaseApplicationModule.class})
class ApplicationModule {
}
