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
import android.view.View;

import javax.inject.Inject;

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
                view -> mNavigator.startActivity("com.tomaszpolanski.androidsandbox.SecondActivity"));
    }

    @NonNull
    @Override
    protected ThirdActivityComponent createComponent() {
        BaseApplication app = (BaseApplication) getApplication();
        BaseActivityModule activityModule = new BaseActivityModule(this);

        return ((IThirdFeatureAppComponent) app.component()).plusThirdActivity(activityModule);
    }

    @Override
    public void inject() {
        component().inject(this);
    }

}
