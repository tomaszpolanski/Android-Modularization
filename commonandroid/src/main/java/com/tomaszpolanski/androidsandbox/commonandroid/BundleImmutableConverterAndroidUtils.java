package com.tomaszpolanski.androidsandbox.commonandroid;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import polanski.option.Option;

public class BundleImmutableConverterAndroidUtils {

    private BundleImmutableConverterAndroidUtils() {
    }

    /**
     * Creates {@link Option} of {@link BundleImmutable} from {@link Bundle}.
     *
     * @param bundle System {@link Bundle} to be converted into testable bundle
     * @return Some of {@link BundleImmutable} if conversion was successful,
     * otherwise NONE
     */
    @NonNull
    public static Option<BundleImmutable> from(@Nullable final Bundle bundle) {
        return Option.ofObj(bundle)
                     .map(BundleImmutableConverterAndroidUtils::getExtras)
                     .map(BundleImmutable::new);
    }

    /**
     * Creates {@link Option} of {@link Bundle} from {@link IBundle}.
     *
     * @param bundle {@link IBundle} to be converted into system bundle
     * @return Some of {@link Bundle} if conversion was successful,
     * otherwise NONE
     */
    @NonNull
    public static Option<Bundle> to(@NonNull final IBundle bundle) {
        Bundle androidBundle = new Bundle();
        for (final Map.Entry<String, Object> entry : bundle.get().entrySet()) {
            if (!put(androidBundle, entry.getKey(), entry.getValue())) {
                return Option.none();
            }
        }
        return Option.ofObj(androidBundle);
    }

    private static boolean put(@NonNull final Bundle bundle,
                               @NonNull final String key,
                               @NonNull final Object value) {
        if (value instanceof String) {
            bundle.putString(key, (String) value);
        } else if (value instanceof Integer) {
            bundle.putInt(key, (Integer) value);
        } else if (value instanceof Float) {
            bundle.putFloat(key, (Float) value);
        } else if (value instanceof Boolean) {
            bundle.putBoolean(key, (Boolean) value);
        } else if (value instanceof Serializable) {
            bundle.putSerializable(key, (Serializable) value);
        } else if (value instanceof Parcelable) {
            bundle.putParcelable(key, (Parcelable) value);
        } else {
            return false;
        }
        return true;
    }

    @NonNull
    private static Map<String, Object> getExtras(@NonNull final Bundle bundle) {
        final Map<String, Object> map = new HashMap<>();
        for (String key : bundle.keySet()) {
            map.put(key, bundle.get(key));
        }
        return map;
    }
}
