package com.tomaszpolanski.androidsandbox.utils.result;

import android.support.annotation.NonNull;

import com.tomaszpolanski.androidsandbox.models.Errors.ResultError;
import com.tomaszpolanski.androidsandbox.utils.option.Option;

import rx.functions.Func0;
import rx.functions.Func1;
import rx.functions.Func2;

public final class Failure<A> extends Result<A> {

    @NonNull
    private final ResultError mFailure;

    Failure(@NonNull final ResultError value) {
        mFailure = value;
    }

    @Override
    public boolean getIsSuccess() {
        return false;
    }

    @NonNull
    @Override
    public <OUT> Result<OUT> map(@NonNull final Func1<A, OUT> f) {
        return failure(mFailure);
    }

    @NonNull
    @Override
    public <OUT> Result<OUT> flatMap(@NonNull final Func1<A, Result<OUT>> f) {
        return failure(mFailure);
    }

    @NonNull
    @Override
    public Result<A> filter(@NonNull final Func1<A, Boolean> predicate,
                            @NonNull final Func1<A, ResultError> failMessage) {
        return failure(mFailure);
    }

    @Override
    public boolean isSuccess() {
        return false;
    }

    @NonNull
    @Override
    ResultError getMessage() {
        return mFailure;
    }

    @NonNull
    @Override
    A getUnsafe() {
        throw new IllegalStateException();
    }

    @NonNull
    @Override
    public Result<A> or(@NonNull final Func0<Result<A>> f) {
        return f.call();
    }

    @NonNull
    @Override
    public Option<A> toOption() {
        return Option.NONE;
    }

    @Override
    public <OUT> OUT match(@NonNull final Func1<A, OUT> fSuccess,
                           @NonNull final Func0<OUT> fFailure) {
        return fFailure.call();
    }

    @NonNull
    @Override
    public <IN, OUT> Result<OUT> lift(@NonNull final Result<IN> resultIn,
                                      @NonNull final Func2<A, IN, OUT> f) {
        return (Result<OUT>) this;
    }

    @Override
    public String toString() {
        return "Failure: " + getMessage();
    }
}
