package com.tomaszpolanski.androidsandbox.utils.option;

import android.util.Log;

import com.android.internal.util.Predicate;
import com.tomaszpolanski.androidsandbox.utils.result.Result;

import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.functions.Func2;

public abstract class Option<A> {

    public static final None NONE = new None();

    public abstract void iter(final Action1<A> action);

    public abstract <B> Option<B> map(final Func1<A, B> f);

    public abstract <B> Option<B> flatMap(final Func1<A, Option<B>> f);

    public abstract Option<A> filter(final Predicate<? super A> predicate);

    public abstract Option<A> orOption(final Func0<Option<A>> f);

    public abstract A orDefault(final Func0<A> def);

    public Result<A> asResult(final String error) {
        return Result.asResult(this, error);
    }

    public abstract A get();

    public static <A> Some<A> some(final A value) {
        return new Some(value);
    }

    public static <A> None<A> none() {
        return NONE;
    }

    public abstract <R> Option<R> ofType(final Class<R> type);

    public static <A> Option<A> asOption(final A value) {
        return value == null ? none() : some(value);
    }

    public static <A> Option<A> tryAsOption(final Func0<A> f) {
        try {
            return Option.asOption(f.call());
        } catch (Exception e) {
            return NONE;
        }
    }

    public abstract void match( final Action1<A> fSome, final Action0 fNone);

    public abstract <R> R matchResult( final Func1<A, R> fSome, final Func0<R> fNone);

    public Option<A> id() {
        return this;
    }

    public abstract  <B,C> Option<C> lift(final Option<B> optionB, final Func2<A, B, C> f);

    public Option<A> log(final String message) {
        Log.e(message, this.toString());
        return this;
    }
}

