package com.tomaszpolanski.androidsandbox.utils.option;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.android.internal.util.Predicate;
import com.tomaszpolanski.androidsandbox.models.Errors.ResultError;
import com.tomaszpolanski.androidsandbox.utils.Linq;
import com.tomaszpolanski.androidsandbox.utils.Unit;
import com.tomaszpolanski.androidsandbox.utils.result.Result;

import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.functions.Func3;
import rx.functions.Func4;

public final class None<T> extends Option<T> {

    None() { }

    @Override
    public boolean getIsSome() {
        return false;
    }

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
    public Option<T> filter(@NonNull final Func1<T, Boolean> predicate) {
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

    @Nullable
    @Override
    public Unit match(@NonNull Action1<T> fSome, @NonNull Action0 fNone) {
        return Unit.asUnit(fNone);
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
    public <IN1, IN2, IN3, OUT> Option<OUT> lift(@NonNull Option<IN1> option1,
                                                 @NonNull Option<IN2> option2,
                                                 @NonNull Option<IN3> option3,
                                                 @NonNull Func4<T, IN1, IN2, IN3, OUT> f) {
        return NONE;
    }

    @NonNull
    @Override
    public Linq<T> toLinq() {
        return Linq.empty();
    }

    @NonNull
    @Override
    public Result<T> toResult(@NonNull final ResultError message) {
        return Result.failure(message);
    }


    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
