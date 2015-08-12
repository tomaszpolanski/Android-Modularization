package com.tomaszpolanski.androidsandbox.utils.result;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.tomaszpolanski.androidsandbox.models.Errors.ExceptionError;
import com.tomaszpolanski.androidsandbox.models.Errors.NullError;
import com.tomaszpolanski.androidsandbox.models.Errors.ResultError;
import com.tomaszpolanski.androidsandbox.utils.option.Option;
import com.tomaszpolanski.androidsandbox.utils.option.OptionUnsafe;

import rx.functions.Func0;
import rx.functions.Func1;
import rx.functions.Func2;

public abstract class Result<A> {

    public abstract boolean getIsSuccess();

    @NonNull
    public abstract <OUT> Result<OUT> map(@NonNull final Func1<A, OUT> f);

    @NonNull
    public abstract <OUT> Result<OUT> flatMap(@NonNull final Func1<A, Result<OUT>> f);

    @NonNull
    public abstract Result<A> filter(@NonNull final Func1<A, Boolean> predicate,
                                     @NonNull final Func1<A, ResultError> failMessage);

    public abstract boolean isSuccess();

    @NonNull
    public abstract ResultError getMessage();

    @NonNull
    public abstract A getUnsafe();

    @NonNull
    public abstract Result<A> or(@NonNull final Func0<Result<A>> f);

    @NonNull
    public static <A> Result<A> success(@NonNull final A value) {
        return new Success(value);
    }

    @NonNull
    public static <A> Failure<A> failure(@NonNull final ResultError failure) {
        return new Failure<>(failure);
    }

    @NonNull
    public static <A> Result<A> ofObj(@Nullable final A value,
                                      @NonNull final String valueName) {
        return ofOption(Option.ofObj(value), new NullError(valueName));
    }

    @NonNull
    public static <A> Result<A> ofObj(@Nullable final A value,
                                      @NonNull final ResultError failMessage) {
        return ofOption(Option.ofObj(value), failMessage);
    }

    @NonNull
    public static <A> Result<A> ofOption(@NonNull final Option<A> value,
                                         @NonNull final ResultError failMessage) {
        return value.<Result<A>>match(Result::success,
                () -> failure(failMessage));
    }

    @NonNull
    public abstract Option<A> toOption();

    @NonNull
    public static <A> Result<A> tryAsResult(@NonNull final Func0<A> f) {
        try {
            return Result.ofObj(f.call(), new NullError("Try as result error"));
        } catch (Exception e) {
            return Result.failure(new ExceptionError(e));
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