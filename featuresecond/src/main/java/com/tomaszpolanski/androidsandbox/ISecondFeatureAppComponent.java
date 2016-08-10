package com.tomaszpolanski.androidsandbox;

import com.tomaszpolanski.androidsandbox.injection.activity.BaseActivityModule;

public interface ISecondFeatureAppComponent {

    SecondActivityComponent plusSecondActivity(BaseActivityModule activityModule);
}
