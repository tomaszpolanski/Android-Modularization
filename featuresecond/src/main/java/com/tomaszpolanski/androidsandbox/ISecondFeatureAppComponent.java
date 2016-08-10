package com.tomaszpolanski.androidsandbox;

import com.tomaszpolanski.androidsandbox.injection.activity.BaseActivityModule;

/**
 * Created by Kriger on 10.08.2016.
 */
public interface ISecondFeatureAppComponent {

    SecondActivityComponent plusSecondActivity(BaseActivityModule splashActivityModule);
}
