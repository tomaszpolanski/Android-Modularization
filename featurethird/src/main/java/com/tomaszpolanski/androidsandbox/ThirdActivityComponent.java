package com.tomaszpolanski.androidsandbox;

import com.tomaszpolanski.androidsandbox.injection.activity.BaseActivityComponent;
import com.tomaszpolanski.androidsandbox.injection.activity.BaseActivityModule;
import com.tomaszpolanski.androidsandbox.injection.activity.PerActivity;

import dagger.Subcomponent;

@PerActivity
@Subcomponent(modules = {BaseActivityModule.class})
public interface ThirdActivityComponent extends BaseActivityComponent {

    void inject(final ThirdActivity activity);
}


