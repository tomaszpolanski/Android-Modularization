package com.tomaszpolanski.androidsandbox.common;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Provides similar behaviour to Android's class but is able to be unit testable.
 * Android's implementation is a stub.
 */
public final class TextUtils {

    private TextUtils() {
    }

    /**
     * Returns true if the string is null or 0-length.
     *
     * @param string the string to be examined
     * @return true if string is null or zero length
     */
    public static boolean isEmpty(@Nullable CharSequence string) {
        return string == null || string.length() == 0;
    }

    /**
     * Returns false if the string is null or 0-length.
     *
     * @param string the string to be examined
     * @return false if string is null or zero length
     */
    public static boolean isNotEmpty(@Nullable CharSequence string) {
        return !isEmpty(string);
    }

    /**
     * Returns a string containing the tokens joined by delimiters.
     * This is a copy of Android TextUtils  implementation
     *
     * @param tokens an array objects to be joined. Strings will be formed from
     *               the objects by calling object.toString().
     * @see <a href="http://developer.android.com/reference/android/text/TextUtils.html#join%28java.lang.CharSequence,%20java.lang.Iterable%29">TextUtils</a>
     */
    @NonNull
    public static String join(@NonNull final CharSequence delimiter,
                              @NonNull final Iterable tokens) {
        StringBuilder sb = new StringBuilder();
        boolean firstTime = true;
        for (Object token : tokens) {
            if (firstTime) {
                firstTime = false;
            } else {
                sb.append(delimiter);
            }
            sb.append(token);
        }
        return sb.toString();
    }

}
