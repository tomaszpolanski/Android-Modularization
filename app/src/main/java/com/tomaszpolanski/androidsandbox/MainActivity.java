package com.tomaszpolanski.androidsandbox;

import com.tomaszpolanski.androidsandbox.injection.ActivityModule;
import com.tomaszpolanski.androidsandbox.providers.INavigator;
import com.tomaszpolanski.androidsandbox.providers.IResourceProvider;
import com.tomaszpolanski.androidsandbox.providers.Navigator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import javax.inject.Inject;

import polanski.option.Option;
import toothpick.Scope;
import toothpick.Toothpick;

import static com.tomaszpolanski.androidsandbox.common.Preconditions.get;

public class MainActivity extends AppCompatActivity {

    @Inject
    @Nullable
    IResourceProvider mResourceProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Scope scope = Toothpick.openScopes(getApplication(), this);
        scope.installModules(new ActivityModule(this));
        super.onCreate(savedInstanceState);
        Toothpick.inject(this, scope);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final Activity self = this;
        INavigator nav = new Navigator(this);
        Option<FloatingActionButton> fab = Option.ofObj(findViewById(R.id.fab))
                                                 .ofType(FloatingActionButton.class);

        fab.ifSome(button -> button.setOnClickListener(view -> Snackbar
                .make(view,
                      get(mResourceProvider).getString(R.string.popup),
                      Snackbar.LENGTH_LONG)
                .setAction("Start", v -> {
                    startActivity(new Intent(this, ThirdActivity.class));
                })
                .show()));
    }

}
