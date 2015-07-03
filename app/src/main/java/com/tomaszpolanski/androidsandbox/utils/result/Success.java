package com.tomaszpolanski.androidsandbox.utils.result;

import android.support.annotation.NonNull;

import com.android.internal.util.Predicate;
import com.tomaszpolanski.androidsandbox.utils.option.Option;

import rx.functions.Func0;
import rx.functions.Func1;
import rx.functions.Func2;

public final class Success<A> extends Result<A> {

    @NonNull
    private final A mValue;

    Success(@NonNull A value) {
        mValue = value;
    }

    @NonNull
    @Override
    public <OUT> Result<OUT> map(@NonNull final Func1<A, OUT> f) {
        return success(f.call(mValue));
    }

    @NonNull
    @Override
    public <OUT> Result<OUT> flatMap(@NonNull final Func1<A, Result<OUT>> f) {
        return f.call(mValue);
    }

    @NonNull
    @Override
    public Result<A> filter(@NonNull final Predicate<? super A> predicate,
                            @NonNull final Func1<A, String> failMessage) {
        return predicate.apply(mValue) ? this : failure(failMessage.call(mValue));
    }

    @Override
    public boolean isSuccess() {
        return true;
    }

    @NonNull
    @Override
    public String getMessage() {
        throw new IllegalStateException();
    }

    @NonNull
    @Override
    public A getUnsafe() {
        return mValue;
    }

    @NonNull
    @Override
    public Result<A> or(@NonNull final Func0<Result<A>> f) {
        return this;
    }

    @NonNull
    @Override
    public Option<A> asOption() {
        return Option.asOption(mValue);
    }

    @Override
    public <OUT> OUT match(@NonNull final Func1<A, OUT> fSuccess,
                           @NonNull final Func0<OUT> fFailure) {
        return fSuccess.call(mValue);
    }

    @NonNull
    @Override
    public <IN, OUT> Result<OUT> lift(@NonNull final Result<IN> resultIn,
                                      @NonNull final Func2<A, IN, OUT> f) {
        return resultIn.map(b -> f.call(mValue, b));
    }

    @Override
    public String toString() {
        return "Success: " + mValue;
    }
}
