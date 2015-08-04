package com.tomaszpolanski.androidsandbox.utils;

import android.support.annotation.NonNull;

public final class Benchmark {
    private final long mStartTime = System.currentTimeMillis();
    @NonNull
    private final String mLabel;

    public Benchmark(@NonNull final String label) {
        mLabel = label;
    }

    @Override
    public String toString() {
        return String.format("%s: %dms", mLabel, System.currentTimeMillis() - mStartTime);
    }
}
