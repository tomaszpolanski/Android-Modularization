package com.tomaszpolanski.androidsandbox.utils.result;
import com.android.internal.util.Predicate;
import com.tomaszpolanski.androidsandbox.utils.option.Option;

import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.functions.Func2;

public final class Success<A> extends Result<A> {

    private final A mValue;

    Success(A value) {
        mValue = value;
    }

    @Override
    public <B> Result<B> map(Func1<A, B> f) {
        return success(f.call(mValue));
    }

    @Override
    public <B> Result<B> flatMap(Func1<A, Result<B>> f) {
        return f.call(mValue);
    }

    @Override
    public Result<A> filter(Predicate<? super A> predicate, Func1<A, String> failMessage) {
        return predicate.apply(mValue) ? this : failure(failMessage.call(mValue));
    }

    @Override
    public boolean isSuccess() {
        return true;
    }

    @Override
    public String getMessage() {
        throw new IllegalStateException();
    }

    @Override
    public A get() {
        return mValue;
    }

    @Override
    public Result<A> or(Func0<Result<A>> f) {
        return this;
    }

    @Override
    public Option<A> asOption() {
        return Option.asOption(mValue);
    }

    @Override
    public void match(Action1<A> fSuccess, Action0 fFailure) {
        fSuccess.call(mValue);
    }

    @Override
    public <R> R matchResult(Func1<A, R> fSuccess, Func0<R> fFailure) {
        return fSuccess.call(mValue);
    }

    @Override
    public <B, C> Result<C> lift(final Result<B> resultB, final Func2<A, B, C> f) {
        return resultB.map(b -> f.call(mValue, b));
    }

    @Override
    public String toString() {
        return "Success: " + mValue;
    }
}
