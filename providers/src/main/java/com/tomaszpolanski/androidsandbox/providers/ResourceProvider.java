package com.tomaszpolanski.androidsandbox.providers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

public class ResourceProvider implements IResourceProvider {

    @NonNull
    private final Context mContext;

    public ResourceProvider(@NonNull final Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public String getString(@StringRes final int id) {
        return mContext.getString(id);
    }

}
