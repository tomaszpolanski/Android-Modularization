package com.tomaszpolanski.androidsandbox.commonandroid;

import com.google.auto.value.AutoValue;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import polanski.option.Option;

import static polanski.option.Option.ofObj;

/**
 * Represents testable version of {@link android.content.Intent}
 */
@AutoValue
public abstract class IntentImmutable {

    /**
     * Returns an {@link Option} of value that is located under given key
     *
     * @param key Key that expected value should be located under
     * @return If key exists, then returns {@link Option} of value
     * otherwise NONE
     */
    @NonNull
    public Option<Object> extra(@NonNull final String key) {
        return bundle().getObject(key);
    }

    /**
     * Returns flags set to intent
     */
    public abstract int flags();

    /**
     * {@link BundleImmutable} of the intent.
     */
    @NonNull
    public abstract IBundle bundle();

    /**
     * Uri intent data
     */
    @NonNull
    public abstract Option<String> data();

    @NonNull
    public static IntentImmutable create(final int flags,
                                         @NonNull final IBundle bundle,
                                         @NonNull final Option<String> data) {
        return new AutoValue_IntentImmutable(flags, bundle, data);
    }

    /**
     * Builder for {@link IntentImmutable}
     */
    @SuppressWarnings("ReturnOfThis")
    public static final class Builder {

        private int mFlags;

        @NonNull
        private final BundleImmutable.Builder mBundleBuilder = BundleImmutable.Builder.builder();

        @Nullable
        private String mData;

        @NonNull
        public IntentImmutable build() {
            return create(mFlags, mBundleBuilder.build(), ofObj(mData));
        }

        /**
         * Prepares flags for the intent
         *
         * @param flags Flags to be put to intent
         */
        @NonNull
        public Builder withFlags(final int flags) {
            mFlags = flags;
            return this;
        }

        /**
         * Prepares key, value pair to be inserted into {@link IntentImmutable}
         *
         * @param key   Key for the translateLanguageCodeOnce
         * @param value Value for the value
         */
        @NonNull
        public Builder withExtra(@NonNull final String key,
                                 @NonNull final Object value) {
            mBundleBuilder.put(key, value);
            return this;
        }

        /**
         * Uri data of the intent, see {@link android.content.Intent}
         *
         * @param data Uri data
         */
        @NonNull
        public Builder withData(@Nullable final String data) {
            mData = data;
            return this;
        }
    }

}
