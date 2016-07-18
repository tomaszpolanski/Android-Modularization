package com.tomaszpolanski.androidsandbox.commonandroid;

import org.junit.Test;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import polanski.option.Option;
import polanski.option.OptionUnsafe;

import static com.tomaszpolanski.androidsandbox.commonandroid.BundleImmutable.Builder.builder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static polanski.option.Option.none;

public class BundleImmutableTest {

    @Test
    public void getInt_whenDoesNotExit() {
        BundleImmutable bundle = new BundleImmutable(Collections.<String, Object>emptyMap());

        Option<Integer> result = bundle.getInt("SomeKey");

        assertThat(result).isEqualTo(none());
    }

    @Test
    public void getInt_whenDoesExits() {
        String key = "SomeKey";
        final int value = 1;
        BundleImmutable bundle = new BundleImmutable(
                Collections.<String, Object>singletonMap(key, value));

        Option<Integer> result = bundle.getInt(key);

        assertThat(OptionUnsafe.getUnsafe(result)).isEqualTo(value);
    }

    @Test
    public void getInt_whenDoesNotExitInNonEmptyMap() {
        String key = "MissingKey";
        BundleImmutable bundle = new BundleImmutable(
                Collections.<String, Object>singletonMap("OtherKey", 2));

        Option<Integer> result = bundle.getInt(key);

        assertThat(result).isEqualTo(none());
    }

    @Test
    public void getObject_whenDoesExits() {
        String key = "SomeKey";
        Object value = mock(Object.class);
        BundleImmutable bundle = new BundleImmutable(Collections.singletonMap(key, value));

        Option<Object> result = bundle.getObject(key);

        assertThat(OptionUnsafe.getUnsafe(result)).isEqualTo(value);
    }

    @Test
    public void getObject_whenDoesNotExitInNonEmptyMap() {
        String key = "MissingKey";
        BundleImmutable bundle = new BundleImmutable(
                Collections.singletonMap("OtherKey", mock(Object.class)));

        Option<Object> result = bundle.getObject(key);

        assertThat(result).isEqualTo(none());
    }

    @Test
    public void getFloat_whenDoesExits() {
        String key = "SomeKey";
        final float value = 0.2f;
        BundleImmutable bundle = new BundleImmutable(
                Collections.<String, Object>singletonMap(key, value));

        Option<Float> result = bundle.getFloat(key);

        assertThat(OptionUnsafe.getUnsafe(result)).isEqualTo(value);
    }

    @Test
    public void getFloat_whenDoesNotExitInNonEmptyMap() {
        String key = "MissingKey";
        BundleImmutable bundle = new BundleImmutable(
                Collections.<String, Object>singletonMap("OtherKey", 2.0f));

        Option<Float> result = bundle.getFloat(key);

        assertThat(result).isEqualTo(none());
    }

    @Test
    public void getString_whenDoesExits() {
        String key = "SomeKey";
        String value = "Some value";
        BundleImmutable bundle = new BundleImmutable(
                Collections.<String, Object>singletonMap(key, value));

        Option<String> result = bundle.getString(key);

        assertThat(OptionUnsafe.getUnsafe(result)).isEqualTo(value);
    }

    @Test
    public void getString_whenDoesNotExitInNonEmptyMap() {
        String key = "MissingKey";
        BundleImmutable bundle = new BundleImmutable(
                Collections.<String, Object>singletonMap("OtherKey", "AnotherValue"));

        Option<String> result = bundle.getString(key);

        assertThat(result).isEqualTo(none());
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void getBoolean_whenDoesExits() {
        String key = "SomeKey";
        final boolean value = true;
        BundleImmutable bundle = new BundleImmutable(
                Collections.<String, Object>singletonMap(key, value));

        Option<Boolean> result = bundle.getBoolean(key);

        assertThat(OptionUnsafe.getUnsafe(result)).isEqualTo(value);
    }

    @Test
    public void getBoolean_whenDoesNotExitInNonEmptyMap() {
        String key = "MissingKey";
        BundleImmutable bundle = new BundleImmutable(
                Collections.<String, Object>singletonMap("OtherKey", false));

        Option<Boolean> result = bundle.getBoolean(key);

        assertThat(result).isEqualTo(none());
    }

    @Test
    public void getParcelable_whenDoesExits() {
        String key = "SomeKey";
        Parcelable value = mock(Parcelable.class);
        BundleImmutable bundle = new BundleImmutable(
                Collections.<String, Object>singletonMap(key, value));

        Option<Parcelable> result = bundle.getParcelable(key);

        assertThat(OptionUnsafe.getUnsafe(result)).isEqualTo(value);
    }

    @Test
    public void getParcelable_whenDoesNotExitInNonEmptyMap() {
        String key = "MissingKey";
        BundleImmutable bundle = new BundleImmutable(
                Collections.<String, Object>singletonMap("OtherKey", mock(Parcelable.class)));

        Option<Parcelable> result = bundle.getParcelable(key);

        assertThat(result).isEqualTo(none());
    }

    @Test
    public void getSerializable_whenDoesExits() {
        String key = "SomeKey";
        Serializable value = mock(Serializable.class);
        BundleImmutable bundle = new BundleImmutable(
                Collections.<String, Object>singletonMap(key, value));

        Option<Serializable> result = bundle.getSerializable(key);

        assertThat(OptionUnsafe.getUnsafe(result)).isEqualTo(value);
    }

    @Test
    public void getSerializable_whenDoesNotExitInNonEmptyMap() {
        String key = "MissingKey";
        BundleImmutable bundle = new BundleImmutable(
                Collections.<String, Object>singletonMap("OtherKey", mock(Serializable.class)));

        Option<Serializable> result = bundle.getSerializable(key);

        assertThat(result).isEqualTo(none());
    }

    @Test
    public void get_whenEmpty() {
        BundleImmutable bundle = new BundleImmutable();

        Map<String, Object> result = bundle.get();

        assertThat(result).isEmpty();
    }

    @Test
    public void get_whenNonEmptyEmpty() {
        Map<String, Object> map = Collections.<String, Object>singletonMap("Key", "Value");
        BundleImmutable bundle = new BundleImmutable(map);

        Map<String, Object> result = bundle.get();

        assertThat(result.entrySet()).containsExactlyElementsOf(map.entrySet());
    }

    @Test
    public void ctor_createsCopy() {
        Map<String, Object> map = new HashMap<>();
        BundleImmutable bundle = new BundleImmutable(map);
        Map<String, Object> result = bundle.get();

        map.put("Key", "Value");

        assertThat(result.entrySet()).isEmpty();
    }

    @Test
    public void get_returnsCopy() {
        BundleImmutable bundle = new BundleImmutable(Collections.<String, Object>emptyMap());
        Map<String, Object> result = bundle.get();

        result.put("Key", "Value");

        assertThat(bundle.get().entrySet()).isEmpty();
    }

    @Test
    public void builderPut_insertsTheValue() {
        String key = "key";
        String value = "value";

        BundleImmutable bundle = builder()
                .put(key, value)
                .build();

        assertThat(bundle.get().entrySet()).hasSize(1);
        assertThat(OptionUnsafe.getUnsafe(bundle.getObject(key))).isEqualTo(value);
    }
}