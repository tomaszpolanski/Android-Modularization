package com.tomaszpolanski.androidsandbox.models.Errors.detail;

import android.support.annotation.NonNull;

import com.tomaszpolanski.androidsandbox.models.Errors.ResultError;

public class ArgumentError extends ResultError {
    public ArgumentError(@NonNull String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "ArgumentError{} " + super.toString();
    }
}
