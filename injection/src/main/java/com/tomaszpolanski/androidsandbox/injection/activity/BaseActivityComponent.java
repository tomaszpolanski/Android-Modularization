package com.tomaszpolanski.androidsandbox.injection.activity;



import com.tomaszpolanski.androidsandbox.injection.app.BaseApplicationComponent;

import android.content.Context;

import dagger.Component;

@com.tomaszpolanski.androidsandbox.injection.activity.PerActivity
@Component(dependencies = BaseApplicationComponent.class, modules = com.tomaszpolanski.androidsandbox.injection.activity.BaseActivityModule.class)
public interface BaseActivityComponent {

    android.app.Activity getActivity();

    @com.tomaszpolanski.androidsandbox.injection.activity.ForActivity
    Context getActivityContext();
}
