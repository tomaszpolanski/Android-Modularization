package com.tomaszpolanski.androidsandbox.utils.result;

import android.support.annotation.NonNull;

import com.tomaszpolanski.androidsandbox.models.Errors.ResultError;

/**
 * Helper class allowing unsafe operations on Result
 */
public final class ResultUnsafe {

    private ResultUnsafe() {}

    /**
     * ATTENTION: Only use it when you know what you are doing!
     *
     * Returns inner value of result if it is Success, otherwise will throw uncatchable exception
     * @param result Result that will be unwrapped
     * @return Value of Success or if Failure, throws exception
     */
    @NonNull
    public static <T> T getUnsafe(@NonNull final Result<T> result) {
        return result.getUnsafe();
    }

    /**
     * ATTENTION: Only use it when you know what you are doing!
     *
     * Returns failure reason of the result option if it is Failure, otherwise will throw uncatchable exception
     * @param result Result that will be unwrapped
     * @return ResultError of Failure or if Success, throws exception
     */
    @NonNull
    public static <T> ResultError getFailure(@NonNull final Result<T> result) {
        return result.getMessage();
    }
}
