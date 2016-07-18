package com.tomaszpolanski.androidsandbox.commonandroid;

import org.junit.Test;
import org.junit.runner.RunWith;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.support.test.runner.AndroidJUnit4;

import java.io.Serializable;

import polanski.option.Option;

import static com.tomaszpolanski.androidsandbox.commonandroid.BundleImmutable.Builder.builder;
import static com.tomaszpolanski.androidsandbox.commonandroid.BundleImmutableConverterAndroidUtils.from;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static polanski.option.OptionUnsafe.getUnsafe;

@RunWith(AndroidJUnit4.class)
public class BundleImmutableConverterAndroidUtilsTest {

    private static final String KEY = "Key";

    @Test
    public void testFrom_deserializeString() {
        String value = "SomeString";
        Bundle bundle = new Bundle();
        bundle.putString(KEY, value);

        Option<BundleImmutable> bundleIm = from(bundle);

        assertThat(getUnsafe(bundleIm).getString(KEY))
                .isEqualTo(Option.ofObj(value));
    }

    @Test
    public void testFrom_deserializeInt() {
        int value = 21;
        Bundle bundle = new Bundle();
        bundle.putInt(KEY, value);

        Option<BundleImmutable> bundleIm = from(bundle);

        assertThat(getUnsafe(bundleIm).getInt(KEY))
                .isEqualTo(Option.ofObj(value));
    }

    @Test
    public void testFrom_deserializeFloat() {
        float value = 21.1f;
        Bundle bundle = new Bundle();
        bundle.putFloat(KEY, value);

        Option<BundleImmutable> bundleIm = from(bundle);

        assertThat(getUnsafe(bundleIm).getFloat(KEY))
                .isEqualTo(Option.ofObj(value));
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void testFrom_deserializeBoolean() {
        boolean value = false;
        Bundle bundle = new Bundle();
        bundle.putBoolean(KEY, value);

        Option<BundleImmutable> bundleIm = from(bundle);

        assertThat(getUnsafe(bundleIm).getBoolean(KEY))
                .isEqualTo(Option.ofObj(value));
    }

    @Test
    public void testFrom_deserializeObject() {
        IBinder value = mock(IBinder.class);
        Bundle bundle = new Bundle();
        bundle.putBinder(KEY, value);

        Option<BundleImmutable> bundleIm = from(bundle);

        assertThat(getUnsafe(bundleIm).getObject(KEY))
                .isEqualTo(Option.ofObj(value));
    }

    @Test
    public void testFrom_serializeString() {
        String value = "SomeString";
        BundleImmutable bundleIm = builder().put(KEY, value)
                                            .build();

        Option<Bundle> bundle = BundleImmutableConverterAndroidUtils.to(bundleIm);

        assertThat(getUnsafe(bundle).getString(KEY))
                .isEqualTo(value);
    }

    @Test
    public void testFrom_serializeInt() {
        int value = 43;
        BundleImmutable bundleIm = builder().put(KEY, value)
                                            .build();

        Option<Bundle> bundle = BundleImmutableConverterAndroidUtils.to(bundleIm);

        assertThat(getUnsafe(bundle).getInt(KEY))
                .isEqualTo(value);
    }

    @Test
    public void testFrom_serializeFloat() {
        float value = 43.4f;
        BundleImmutable bundleIm = builder().put(KEY, value)
                                            .build();

        Option<Bundle> bundle = BundleImmutableConverterAndroidUtils.to(bundleIm);

        assertThat(getUnsafe(bundle).getFloat(KEY))
                .isEqualTo(value);
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void testFrom_serializeBoolean() {
        boolean value = false;
        BundleImmutable bundleIm = builder().put(KEY, value)
                                            .build();

        Option<Bundle> bundle = BundleImmutableConverterAndroidUtils.to(bundleIm);

        assertThat(getUnsafe(bundle).getBoolean(KEY))
                .isEqualTo(value);
    }

    @Test
    public void testFrom_serializeParcelable() {
        Parcelable value = mock(Parcelable.class);
        BundleImmutable bundleIm = builder().put(KEY, value)
                                            .build();

        Option<Bundle> bundle = BundleImmutableConverterAndroidUtils.to(bundleIm);

        Parcelable result = getUnsafe(bundle).getParcelable(KEY);
        assertThat(result).isEqualTo(value);
    }

    @Test
    public void testFrom_serializeSerializable() {
        Serializable value = mock(Serializable.class);
        BundleImmutable bundleIm = builder().put(KEY, value)
                                            .build();

        Option<Bundle> bundle = BundleImmutableConverterAndroidUtils.to(bundleIm);

        assertThat(getUnsafe(bundle).getSerializable(KEY))
                .isEqualTo(value);
    }

    @Test
    public void testFrom_serializeUnknownObject() {
        Object value = mock(Object.class);
        BundleImmutable bundleIm = builder().put(KEY, value)
                                            .build();

        Option<Bundle> bundle = BundleImmutableConverterAndroidUtils.to(bundleIm);

        assertThat(bundle).isEqualTo(Option.none());
    }
}
