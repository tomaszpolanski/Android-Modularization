package com.tomaszpolanski.androidsandbox.providers;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

public class Navigator implements INavigator {

    @NonNull
    private final Context mContext;

    public Navigator(@NonNull final Context context) {
        mContext = context;
    }

    @Override
    public void startActivity(@NonNull final String activity) {
        Intent intent = new Intent();
        intent.setClassName("com.tomaszpolanski.androidsandbox", activity);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
    }
}
