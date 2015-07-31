package com.tomaszpolanski.androidsandbox.models.Errors;

import android.support.annotation.NonNull;

public class NullError extends ResultError {

    public NullError(@NonNull String variable) {
        super("Value is null: " + variable);
    }

    @Override
    public String toString() {
        return "NullError{} " + super.toString();
    }
}
