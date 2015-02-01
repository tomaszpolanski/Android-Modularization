package com.tomaszpolanski.androidsandbox.utils.option;

import com.android.internal.util.Predicate;

import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;

public final class Some<A> extends Option<A> {

    private final A mValue;

    Some(A value) {
        mValue = value;
    }

    @Override
    public void iter(final Action1<A> action) {
        action.call(mValue);
    }

    @Override
    public <B> Option<B> map(final Func1<A, B> f) {
        return some(f.call(mValue));
    }

    @Override
    public <B> Option<B> flatMap(final Func1<A, Option<B>> f) {
        return f.call(mValue);
    }

    @Override
    public Option<A> filter(final Predicate<? super A> predicate) {
        return predicate.apply(mValue) ? this : NONE;
    }

    @Override
    public Option<A> orOption(final Func0<Option<A>> f) {
        return this;
    }

    @Override
    public A orDefault(final Func0<A> def) {
        return mValue;
    }

    @Override
    public A get() {
        return mValue;
    }

    @Override
    public void match(final Action1<A> fSome, final Action0 fNone) {
        fSome.call(mValue);
    }

    @Override
    public <R> R matchResult(final Func1<A, R> fSome, final Func0<R> fNone) {
        return fSome.call(mValue);
    }

    @Override
    public boolean equals(final Object o) {
        return Option.asOption(o)
                     .filter(obj -> obj instanceof Some)
                     .map(obj -> (Some) obj)
                     .filter(some -> some.get().equals(mValue)) != Option.NONE;
    }
}