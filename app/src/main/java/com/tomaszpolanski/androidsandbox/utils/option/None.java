package com.tomaszpolanski.androidsandbox.utils.option;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.android.internal.util.Predicate;
import com.tomaszpolanski.androidsandbox.utils.result.Result;

import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.functions.Func3;
import rx.functions.Func4;

public final class None<T> extends Option<T> {

    None() { }

    @Override
    public void iter(@NonNull final Action1<T> action) {
        // Do nothing
    }

    @NonNull
    @Override
    public <OUT> Option<OUT> map(@NonNull final Func1<T, OUT> f) {
        return NONE;
    }

    @NonNull
    @Override
    public <OUT> Option<OUT> flatMap(@NonNull final Func1<T, Option<OUT>> f) {
        return NONE;
    }

    @NonNull
    @Override
    public Option<T> filter(@NonNull final Predicate<? super T> predicate) {
        return NONE;
    }

    @NonNull
    @Override
    public Option<T> orOption(@NonNull final Func0<Option<T>> f) {
        return f.call();
    }

    @NonNull
    @Override
    public T orDefault(@NonNull final Func0<T> def) {
        return def.call();
    }

    @NonNull
    @Override
    public T getUnsafe() {
        throw new IllegalStateException();
    }

    @NonNull
    @Override
    public <OUT> Option<OUT> ofType(@NonNull Class<OUT> type) {
        return NONE;
    }

    @Nullable
    @Override
    public <OUT> OUT match(@NonNull Func1<T, OUT> fSome,
                           @NonNull Func0<OUT> fNone) {
        return fNone.call();
    }

    @NonNull
    @Override
    public <IN, OUT> Option<OUT> lift(@NonNull final Option<IN> optionB,
                                        @NonNull final Func2<T, IN, OUT> f) {
        return NONE;
    }

    @NonNull
    @Override
    public <IN1, IN2, OUT> Option<OUT> lift(@NonNull Option<IN1> option1,
                                            @NonNull Option<IN2> option2,
                                            @NonNull Func3<T, IN1, IN2, OUT> f) {
        return NONE;
    }

    @NonNull
    @Override
    public <IN1, IN2, IN3, OUT> Option<OUT> lift(@NonNull Option<IN1> option1, @NonNull Option<IN2> option2, @NonNull Option<IN3> option3, @NonNull Func4<T, IN1, IN2, IN3, OUT> f) {
        return NONE;
    }

    @NonNull
    @Override
    public Result<T> asResult(@NonNull final String message) {
        return Result.failure(message);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
