package com.tomaszpolanski.androidsandbox.utils.result;

import com.android.internal.util.Predicate;
import com.tomaszpolanski.androidsandbox.utils.option.Option;

import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;

public final class Failure<A> extends Result<A> {

    private final String mFailureMessage;

    Failure(String value) {
        mFailureMessage = value;
    }

    @Override
    public <B> Result<B> map(Func1<A, B> f) {
        return failure(this.mFailureMessage);
    }

    @Override
    public <B> Result<B> flatMap(Func1<A, Result<B>> f) {
        return failure(this.mFailureMessage);
    }

    @Override
    public Result<A> filter(Predicate<? super A> predicate, Func1<A, String> failMessage) {
        return failure(this.mFailureMessage);
    }

    @Override
    public boolean isSuccess() {
        return false;
    }

    @Override
    public String getMessage() {
        return mFailureMessage;
    }

    @Override
    public A get() {
        throw new IllegalStateException();
    }

    @Override
    public Result<A> or(Func0<Result<A>> f) {
        return f.call();
    }

    @Override
    public Option<A> asOption() {
        return Option.NONE;
    }

    @Override
    public void match(Action1<A> fSuccess, Action0 fFailure) {
        fFailure.call();
    }

    @Override
    public <R> R matchResult(Func1<A, R> fSuccess, Func0<R> fFailure) {
        return fFailure.call();
    }


    @Override
    public String toString() {
        return "Failure: " + getMessage();
    }
}
