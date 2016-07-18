package com.tomaszpolanski.androidsandbox.commonandroid;

import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import polanski.option.Option;

public class BundleImmutable implements IBundle {

    @NonNull
    public static final BundleImmutable EMPTY = new Builder().build();

    @NonNull
    private final Map<String, Object> mMap;

    public BundleImmutable() {
        this(Collections.<String, Object>emptyMap());
    }

    public BundleImmutable(@NonNull final Map<String, Object> map) {
        mMap = new HashMap<>(map);
    }

    @NonNull
    @Override
    public Option<Integer> getInt(@NonNull final String key) {
        return getObject(key)
                .ofType(Integer.class);
    }

    @NonNull
    @Override
    public Option<Float> getFloat(@NonNull final String key) {
        return getObject(key)
                .ofType(Float.class);
    }

    @NonNull
    @Override
    public Option<Boolean> getBoolean(@NonNull final String key) {
        return getObject(key)
                .ofType(Boolean.class);
    }

    @NonNull
    @Override
    public Option<String> getString(@NonNull final String key) {
        return getObject(key)
                .ofType(String.class);
    }

    @NonNull
    @Override
    public Option<Parcelable> getParcelable(@NonNull final String key) {
        return getObject(key)
                .ofType(Parcelable.class);
    }

    @NonNull
    @Override
    public Option<Serializable> getSerializable(@NonNull final String key) {
        return getObject(key)
                .ofType(Serializable.class);
    }

    @NonNull
    @Override
    public Option<Object> getObject(@NonNull final String key) {
        return Option.ofObj(mMap.get(key));
    }

    @NonNull
    @Override
    public Map<String, Object> get() {
        return new HashMap<>(mMap);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BundleImmutable)) {
            return false;
        }

        final BundleImmutable that = (BundleImmutable) o;

        return mMap.equals(that.mMap);

    }

    @Override
    public String toString() {
        return "BundleImmutable{" +
               "mMap=" + mMap +
               '}';
    }

    @Override
    public int hashCode() {
        return mMap.hashCode();
    }

    /**
     * Builder for {@link BundleImmutable}
     */
    public static final class Builder {

        @NonNull
        private final Map<String, Object> mMap = new HashMap<>();

        /**
         * Creates builder.
         */
        @NonNull
        public static Builder builder() {
            return new Builder();
        }

        /**
         * Builds and instance of {@link BundleImmutable}.
         */
        @NonNull
        public BundleImmutable build() {
            return new BundleImmutable(mMap);
        }

        /**
         * Prepares key, value pair to be inserted into {@link BundleImmutable}
         *
         * @param key   Key for the map
         * @param value Value for the value
         */
        @NonNull
        public Builder put(@NonNull final String key,
                           @NonNull final Object value) {
            mMap.put(key, value);
            return this;
        }

    }
}
