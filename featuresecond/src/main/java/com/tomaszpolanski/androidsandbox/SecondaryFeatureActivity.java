package com.tomaszpolanski.androidsandbox;

import com.tomaszpolanski.androidsandbox.injection.ActivityModule;
import com.tomaszpolanski.androidsandbox.second.R;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import toothpick.Scope;
import toothpick.Toothpick;

public class SecondaryFeatureActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Scope scope = Toothpick.openScopes(getApplication(), this);
        scope.installModules(new ActivityModule(this));
        super.onCreate(savedInstanceState);
        Toothpick.inject(this, scope);
        setContentView(R.layout.activity_secondary_feature);
    }

}
