package com.tomaszpolanski.androidsandbox;

import com.tomaszpolanski.androidsandbox.injection.activity.BaseActivity;
import com.tomaszpolanski.androidsandbox.injection.activity.BaseActivityModule;
import com.tomaszpolanski.androidsandbox.injection.app.BaseApplication;
import com.tomaszpolanski.androidsandbox.second.R;

import android.os.Bundle;
import android.support.annotation.NonNull;

import static polanski.option.Option.ofObj;
import static polanski.option.OptionUnsafe.orThrowUnsafe;

public class SecondActivity extends BaseActivity<SecondActivityComponent> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary_feature);
    }

    @NonNull
    @Override
    protected SecondActivityComponent createComponent() {
        return orThrowUnsafe(ofObj(getApplication())
                                     .ofType(BaseApplication.class)
                                     .map(BaseApplication::component)
                                     .ofType(ISecondFeatureAppComponent.class)
                                     .map(it -> it.plusSecondActivity(
                                             new BaseActivityModule(this))),
                             new RuntimeException("Cannot inject " + getClass().getSimpleName()));
    }

    @Override
    public void inject() {
        component().inject(this);
    }
}
