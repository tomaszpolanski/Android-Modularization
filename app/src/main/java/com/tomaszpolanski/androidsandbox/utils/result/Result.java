package com.tomaszpolanski.androidsandbox.utils.result;

import com.android.internal.util.Predicate;
import com.tomaszpolanski.androidsandbox.utils.StringUtils;
import com.tomaszpolanski.androidsandbox.utils.option.Option;


import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.functions.Func2;

public abstract class Result<A> {

    public abstract <B> Result<B> map(Func1<A, B> f);

    public abstract <B> Result<B> flatMap(Func1<A, Result<B>> f);

    public abstract Result<A> filter(Predicate<? super A> predicate, Func1<A, String> failMessage);

    public abstract boolean isSuccess();

    public abstract String getMessage();

    public abstract A get();

    public abstract Result<A> or(Func0<Result<A>> f);

    public static <A> Success<A> success(A value) {
        return new Success(value);
    }

    public static <A> Failure<A> failure(String failure) {
        return new Failure<>(failure);
    }

    public static <A> Result<A> merge(Result<A> first, Result<A> second) {
        return first.<Result<A>>matchResult(
                __ -> second,
                () -> failure(first.getMessage() + ", " + second.matchResult(
                        ___ -> StringUtils.EMPTY,
                        second::getMessage)));
    }

    public static <A> Result<A> asResult(A value) {
        return asResult(value, "Object is null");
    }

    public static <A> Result<A> asResult(A value, String failMessage) {
        return asResult(Option.asOption(value), failMessage);
    }

    public static <A> Result<A> asResult(Option<A> value, String failMessage) {
        return value != Option.NONE ? success(value.get()) : failure(failMessage);
    }

    public abstract Option<A> asOption();

    public static <A> Result<A> tryAsResult(Func0<A> f) {
        try {
            return Result.asResult(f.call());
        } catch (Exception e) {
            return Result.failure("Result failed: " + e.toString());
        }
    }

    public abstract void match(final Action1<A> fSuccess, final Action0 fFailure);

    public abstract <R> R matchResult(final Func1<A, R> fSuccess, final Func0<R> fFailure);

    public abstract  <B,C> Result<C> lift(final Result<B> resultB, Func2<A, B, C> f);

    public Result<A> id() {
        return this;
    }
}