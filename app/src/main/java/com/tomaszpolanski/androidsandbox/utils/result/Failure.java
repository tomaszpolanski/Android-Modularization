package com.tomaszpolanski.androidsandbox.utils.result;

import android.support.annotation.NonNull;

import com.android.internal.util.Predicate;
import com.tomaszpolanski.androidsandbox.utils.option.Option;

import rx.functions.Func0;
import rx.functions.Func1;
import rx.functions.Func2;

public final class Failure<A> extends Result<A> {

    @NonNull
    private final String mFailureMessage;

    Failure(@NonNull final String value) {
        mFailureMessage = value;
    }

    @NonNull
    @Override
    public <OUT> Result<OUT> map(@NonNull final Func1<A, OUT> f) {
        return failure(this.mFailureMessage);
    }

    @NonNull
    @Override
    public <OUT> Result<OUT> flatMap(@NonNull final Func1<A, Result<OUT>> f) {
        return failure(this.mFailureMessage);
    }

    @NonNull
    @Override
    public Result<A> filter(@NonNull final Predicate<? super A> predicate,
                            @NonNull final Func1<A, String> failMessage) {
        return failure(this.mFailureMessage);
    }

    @Override
    public boolean isSuccess() {
        return false;
    }

    @NonNull
    @Override
    public String getMessage() {
        return mFailureMessage;
    }

    @NonNull
    @Override
    public A getUnsafe() {
        throw new IllegalStateException();
    }

    @NonNull
    @Override
    public Result<A> or(@NonNull final Func0<Result<A>> f) {
        return f.call();
    }

    @NonNull
    @Override
    public Option<A> asOption() {
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
