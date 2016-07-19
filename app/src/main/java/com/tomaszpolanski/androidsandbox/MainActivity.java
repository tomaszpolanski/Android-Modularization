package com.tomaszpolanski.androidsandbox;

import com.tomaszpolanski.androidsandbox.providers.IResourceProvider;
import com.tomaszpolanski.androidsandbox.providers.ResourceProvider;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import polanski.option.Option;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Option<FloatingActionButton> fab = Option.ofObj(findViewById(R.id.fab))
                .ofType(FloatingActionButton.class);

        fab.ifSome(button ->
                button.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()));

        IResourceProvider rp = new ResourceProvider(getApplicationContext());

        String test = rp.getString(R.string.app_name_providers);
        String test2 = rp.getString(R.string.app_name);
    }

}
