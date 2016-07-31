package com.tomaszpolanski.androidsandbox;

import com.tomaszpolanski.androidsandbox.injection.activity.BaseActivity;
import com.tomaszpolanski.androidsandbox.injection.activity.BaseActivityModule;
import com.tomaszpolanski.androidsandbox.second.R;

import android.os.Bundle;
import android.support.annotation.NonNull;

public class SecondActivity extends BaseActivity<SecondActivityComponent> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary_feature);
    }

    @NonNull
    @Override
    protected SecondActivityComponent createComponent() {
        return DaggerSecondActivityComponent.builder()
                                            .baseActivityModule(new BaseActivityModule(this))
                                            .secondActivityModule(new SecondActivityModule())
                                            .build();
    }

    @Override
    public void inject() {
        component().inject(this);
    }
}
