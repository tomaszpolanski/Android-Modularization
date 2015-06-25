package com.tomaszpolanski.androidsandbox.utils.option;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.android.internal.util.Predicate;
import com.tomaszpolanski.androidsandbox.utils.result.Result;

import java8.util.stream.Stream;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.functions.Func3;
import rx.functions.Func4;

public abstract class Option<T> {

    @NonNull
    public static final None NONE = new None();

    public abstract void iter(@NonNull final Action1<T> action);

    @NonNull
    public abstract <OUT> Option<OUT> map(@NonNull final Func1<T, OUT> selector);

    @NonNull
    public abstract <OUT> Option<OUT> flatMap(@NonNull final Func1<T, Option<OUT>> selector);

    @NonNull
    public abstract Option<T> filter(@NonNull final Predicate<? super T> selector);

    @NonNull
    public abstract Option<T> orOption(@NonNull final Func0<Option<T>> f);

    @NonNull
    public abstract T orDefault(@NonNull final Func0<T> def);

    @NonNull
    public abstract T getUnsafe();

    @NonNull
    public abstract <OUT> Option<OUT> ofType(@NonNull final Class<OUT> type);

    @NonNull
    public static <IN> Option<IN> asOption(@Nullable final IN value) {
        return value == null ? Option.NONE : new Some(value);
    }

    @NonNull
    public static <OUT> Option<OUT> tryAsOption(@NonNull final Func0<OUT> f) {
        try {
            return Option.asOption(f.call());
        } catch (Exception e) {
            return NONE;
        }
    }

    @Nullable
    public abstract <OUT> OUT match(@NonNull final Func1<T, OUT> fSome,
                                    @NonNull final Func0<OUT> fNone);

    @NonNull
    public Option<T> id() {
        return this;
    }

    @NonNull
    public abstract  <IN1, OUT> Option<OUT> lift(@NonNull final Option<IN1> option1,
                                                 @NonNull final Func2<T, IN1, OUT> f);

    @NonNull
    public abstract <IN1, IN2, OUT> Option<OUT> lift(@NonNull final Option<IN1> option1,
                                                     @NonNull final Option<IN2> option2,
                                                     @NonNull final Func3<T, IN1, IN2, OUT> f);

    @NonNull
    public abstract <IN1, IN2, IN3, OUT> Option<OUT> lift(@NonNull final Option<IN1> option1,
                                                          @NonNull final Option<IN2> option2,
                                                          @NonNull final Option<IN3> option3,
                                                          @NonNull final Func4<T, IN1, IN2, IN3, OUT> f);

    @NonNull
    public Option<T> log(@Nullable final String message) {
        Log.e(message, "" + message);
        return this;
    }

    @NonNull
    public abstract Result<T> toResult(@NonNull final String message);

    @NonNull
    public abstract Stream<T> toStream();
}

