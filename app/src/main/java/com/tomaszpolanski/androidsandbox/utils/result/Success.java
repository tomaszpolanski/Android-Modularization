package com.tomaszpolanski.androidsandbox.utils.result;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.tomaszpolanski.androidsandbox.models.Errors.ResultError;
import com.tomaszpolanski.androidsandbox.utils.Unit;
import com.tomaszpolanski.androidsandbox.utils.option.Option;

import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.functions.Func2;

public final class Success<A> extends Result<A> {

    @NonNull
    private final A mValue;

    Success(@NonNull A value) {
        mValue = value;
    }

    @Override
    public boolean getIsSuccess() {
        return true;
    }

    @NonNull
    @Override
    public <OUT> Result<OUT> map(@NonNull final Func1<A, OUT> f) {
        return new Success(f.call(mValue));
    }

    @NonNull
    @Override
    public <OUT> Result<OUT> flatMap(@NonNull final Func1<A, Result<OUT>> f) {
        return f.call(mValue);
    }

    @NonNull
    @Override
    public Result<A> filter(@NonNull final Func1<A, Boolean> predicate,
                            @NonNull final Func1<A, ResultError> failMessage) {
        return predicate.call(mValue) ? this : new Failure(failMessage.call(mValue));
    }

    @NonNull
    @Override
    ResultError getMessageUnsafe() {
        throw new IllegalStateException();
    }

    @NonNull
    @Override
    A getUnsafe() {
        return mValue;
    }

    @NonNull
    @Override
    public Result<A> orResult(@NonNull final Func0<Result<A>> f) {
        return this;
    }

    @NonNull
    @Override
    public Option<A> toOption() {
        return Option.ofObj(mValue);
    }

    @Nullable
    @Override
    public <OUT> OUT match(@NonNull final Func1<A, OUT> fSuccess,
                           @NonNull final Func1<? super ResultError, OUT> fFailure) {
        return fSuccess.call(mValue);
    }

    @NonNull
    @Override
    public Unit matchAction(@NonNull final Action1<A> fSuccess,
                            @NonNull final Action1<? super ResultError> fFailure) {
        return Unit.asUnit(() -> fSuccess.call(mValue));
    }

    @Nullable
    @Override
    public <OUT> OUT matchUnsafe(@NonNull final Func1<A, OUT> fSuccess,
                                 @NonNull final Func1<? super ResultError, OUT> fFailure) {
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
