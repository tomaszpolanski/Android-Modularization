package com.tomaszpolanski.androidsandbox.utils.result;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.android.internal.util.Predicate;
import com.tomaszpolanski.androidsandbox.utils.option.Option;

import rx.functions.Func0;
import rx.functions.Func1;
import rx.functions.Func2;

public abstract class Result<A> {

    @NonNull
    public abstract <OUT> Result<OUT> map(@NonNull final Func1<A, OUT> f);

    @NonNull
    public abstract <OUT> Result<OUT> flatMap(@NonNull final Func1<A, Result<OUT>> f);

    @NonNull
    public abstract Result<A> filter(@NonNull final Predicate<? super A> predicate,
                                     @NonNull final Func1<A, String> failMessage);

    public abstract boolean isSuccess();

    @NonNull
    public abstract String getMessage();

    @NonNull
    public abstract A getUnsafe();

    @NonNull
    public abstract Result<A> or(@NonNull final Func0<Result<A>> f);

    @NonNull
    public static <A> Success<A> success(@NonNull final A value) {
        return new Success(value);
    }

    @NonNull
    public static <A> Failure<A> failure(@NonNull final String failure) {
        return new Failure<>(failure);
    }

    @NonNull
    public static <A> Result<A> asResult(@Nullable A value) {
        return asResult(value, "Object is null");
    }

    @NonNull
    public static <A> Result<A> asResult(@Nullable final A value,
                                         @NonNull final String failMessage) {
        return asResult(Option.asOption(value), failMessage);
    }

    @NonNull
    public static <A> Result<A> asResult(@NonNull final Option<A> value,
                                         @NonNull final String failMessage) {
        return value != Option.NONE ? success(value.getUnsafe()) : failure(failMessage);
    }

    @NonNull
    public abstract Option<A> asOption();

    @NonNull
    public static <A> Result<A> tryAsResult(@NonNull final Func0<A> f) {
        try {
            return Result.asResult(f.call());
        } catch (Exception e) {
            return Result.failure("Result failed: " + e.toString());
        }
    }

    @Nullable
    public abstract <OUT> OUT match(@NonNull final Func1<A, OUT> fSuccess,
                                    @NonNull final Func0<OUT> fFailure);

    @NonNull
    public abstract <IN, OUT> Result<OUT> lift(@NonNull final Result<IN> resultIn,
                                               @NonNull final Func2<A, IN, OUT> f);

    @NonNull
    public Result<A> id() {
        return this;
    }
}