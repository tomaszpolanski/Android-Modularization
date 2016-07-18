package com.tomaszpolanski.androidsandbox.commonandroid;

import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.Map;

import polanski.option.Option;

/**
 * Representation on systems {@link android.os.Bundle}.
 */
public interface IBundle {

    /**
     * Gets Integer with given key.
     */
    @NonNull
    Option<Integer> getInt(@NonNull String key);

    /**
     * Gets Float with given key.
     */
    @NonNull
    Option<Float> getFloat(@NonNull String key);

    /**
     * Gets Boolean with given key.
     */
    @NonNull
    Option<Boolean> getBoolean(@NonNull String key);

    /**
     * Gets String with given key.
     */
    @NonNull
    Option<String> getString(@NonNull String key);

    /**
     * Gets Parcelable with given key.
     */
    @NonNull
    Option<Parcelable> getParcelable(@NonNull String key);

    /**
     * Gets Serializable with given key.
     */
    @NonNull
    Option<Serializable> getSerializable(@NonNull String key);

    /**
     * Gets any object with given key.
     */
    @NonNull
    Option<Object> getObject(@NonNull String key);

    /**
     * Gets copy of whole data of the bundle.
     */
    @NonNull
    Map<String, Object> get();
}
