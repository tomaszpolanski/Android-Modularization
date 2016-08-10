package com.tomaszpolanski.androidsandbox;

import com.tomaszpolanski.androidsandbox.injection.activity.BaseActivity;
import com.tomaszpolanski.androidsandbox.injection.activity.BaseActivityModule;
import com.tomaszpolanski.androidsandbox.injection.app.BaseApplication;
import com.tomaszpolanski.androidsandbox.providers.INavigator;
import com.tomaszpolanski.androidsandbox.third.R;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;

import javax.inject.Inject;

import static polanski.option.Option.ofObj;
import static polanski.option.OptionUnsafe.orThrowUnsafe;

public class ThirdActivity extends BaseActivity<ThirdActivityComponent> {

    @Inject
    @Nullable
    INavigator mNavigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(
                view -> mNavigator
                        .startActivity("com.tomaszpolanski.androidsandbox.SecondActivity"));
    }

    @NonNull
    @Override
    protected ThirdActivityComponent createComponent() {
        return orThrowUnsafe(ofObj(getApplication())
                                     .ofType(BaseApplication.class)
                                     .map(BaseApplication::component)
                                     .ofType(IThirdFeatureAppComponent.class)
                                     .map(it -> it.plusThirdActivity(
                                             new BaseActivityModule(this))),
                             new RuntimeException("Cannot inject " + getClass().getSimpleName()));
    }

    @Override
    public void inject() {
        component().inject(this);
    }

}
