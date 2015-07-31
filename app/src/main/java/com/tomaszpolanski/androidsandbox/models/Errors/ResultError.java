package com.tomaszpolanski.androidsandbox.models.Errors;

import android.support.annotation.NonNull;

public abstract class ResultError {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResultError)) return false;

        ResultError that = (ResultError) o;

        return mMessage.equals(that.mMessage);

    }

    @Override
    public int hashCode() {
        return mMessage.hashCode();
    }



    @NonNull
    public String getMessage() {
        return mMessage;
    }

    @Override
    public String toString() {
        return "ResultError{" +
                "mMessage='" + mMessage + '\'' +
                '}';
    }

    @NonNull
    private final String mMessage;

    protected ResultError(@NonNull final String message) {
        mMessage = message;
    }
}
