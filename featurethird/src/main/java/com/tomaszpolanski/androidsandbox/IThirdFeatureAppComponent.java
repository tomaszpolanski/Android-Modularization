package com.tomaszpolanski.androidsandbox;

import com.tomaszpolanski.androidsandbox.injection.activity.BaseActivityModule;

public interface IThirdFeatureAppComponent {

    ThirdActivityComponent plusThirdActivity(BaseActivityModule activityModule);
}
