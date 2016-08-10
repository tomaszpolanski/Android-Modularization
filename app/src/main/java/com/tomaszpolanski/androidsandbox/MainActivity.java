package com.tomaszpolanski.androidsandbox;

import com.tomaszpolanski.androidsandbox.injection.activity.BaseActivity;
import com.tomaszpolanski.androidsandbox.injection.activity.BaseActivityModule;
import com.tomaszpolanski.androidsandbox.providers.IResourceProvider;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;

import javax.inject.Inject;

import polanski.option.Option;

import static com.tomaszpolanski.androidsandbox.common.Preconditions.get;
import static polanski.option.Option.ofObj;
import static polanski.option.OptionUnsafe.orThrowUnsafe;

public class MainActivity extends BaseActivity<MainActivityComponent> {

    @Inject
    @Nullable
    IResourceProvider mResourceProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Option<FloatingActionButton> fab = Option.ofObj(findViewById(R.id.fab))
                                                 .ofType(FloatingActionButton.class);

        fab.ifSome(button -> button.setOnClickListener(view -> Snackbar
                .make(view,
                      get(mResourceProvider).getString(R.string.popup),
                      Snackbar.LENGTH_LONG)
                .setAction("Start",
                           __ -> startActivity(new Intent(this, ThirdActivity.class)))
                .show()));
    }

    @NonNull
    @Override
    protected MainActivityComponent createComponent() {
        return orThrowUnsafe(ofObj(getApplication())
                                     .ofType(SandboxApplication.class)
                                     .map(SandboxApplication::component)
                                     .map(it -> it.plusMainActivity(
                                             new BaseActivityModule(this))),
                             new RuntimeException("Cannot inject " + getClass().getSimpleName()));
    }

    @Override
    public void inject() {
        component().inject(this);
    }
}
