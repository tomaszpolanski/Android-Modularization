package com.tomaszpolanski.androidsandbox;


import com.tomaszpolanski.androidsandbox.providers.INavigator;
import com.tomaszpolanski.androidsandbox.providers.Navigator;
import com.tomaszpolanski.androidsandbox.third.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        INavigator nav = new Navigator(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nav.startActivity("com.tomaszpolanski.androidsandbox.SecondaryFeatureActivity");
            }
        });
    }

}
