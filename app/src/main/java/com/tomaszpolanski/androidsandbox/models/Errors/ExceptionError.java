package com.tomaszpolanski.androidsandbox.models.Errors;

import android.support.annotation.NonNull;

public class ExceptionError extends ResultError {
    @NonNull
    private final Throwable mException;

    public ExceptionError(@NonNull final Throwable exception) {
        super(exception.toString());

        mException = exception;
    }

    @Override
    public String toString() {
        return "ExceptionError{" +
                "mException=" + mException +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExceptionError)) return false;
        if (!super.equals(o)) return false;

        ExceptionError that = (ExceptionError) o;

        return getException().equals(that.getException());

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getException().hashCode();
        return result;
    }

    @NonNull
    public Throwable getException() {
        return mException;
    }
}
