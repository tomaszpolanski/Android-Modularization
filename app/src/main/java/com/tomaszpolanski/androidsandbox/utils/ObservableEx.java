package com.tomaszpolanski.androidsandbox.utils;

import android.support.annotation.NonNull;

import com.tomaszpolanski.androidsandbox.utils.option.Option;
import com.tomaszpolanski.androidsandbox.utils.option.OptionUnsafe;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import rx.Observable;
import rx.functions.Func1;
import rx.functions.Func2;

public final class ObservableEx {

    private ObservableEx() {
    }

    @NonNull
    public static <IN, OUT> Observable<OUT> choose(@NonNull final Observable<IN> observable,
                                                   @NonNull final Func1<IN, Option<OUT>> selector) {
        return observable.map(selector)
                .filter(Option::getIsSome)
                .map(OptionUnsafe::getUnsafe);
    }

    @NonNull
    public static <T> Option<? super T> first(@NonNull final Observable<T> observable,
                                              @NonNull final Func1<? super T, Boolean> predicate) {
        return Option.tryAsOption(() -> observable.first(predicate));
    }

    @NonNull
    public static <T> Option<? super T> first(@NonNull final Observable<T> observable) {
        return Option.tryAsOption(observable::first);
    }

    @NonNull
    public static <T> Option<? super T> last(@NonNull final Observable<T> observable,
                                             @NonNull final Func1<? super T, Boolean> predicate) {
        return Option.tryAsOption(() -> observable.last(predicate));
    }

    @NonNull
    public static <T> Option<? super T> last(@NonNull final Observable<T> observable) {
        return Option.tryAsOption(observable::last);
    }

    @NonNull
    public static <IN, OUT> Option<Observable<OUT>> traverseOption(@NonNull final Observable<IN> observable,
                                                                   @NonNull final Func1<IN, Option<OUT>> selector) {
        return observable.reduce(
                Option.ofObj(Observable.<OUT>empty()),
                (observableOption, in) ->
                        selector.call(in)
                                .flatMap(t ->
                                        observableOption.map(tObservable ->
                                                tObservable.concatWith(Observable.just(t)))))
                .toBlocking()
                .first();
    }

    @NonNull
    public static <T> Option<Observable<T>> sequenceOption(@NonNull final Observable<Option<T>> observable) {
        return traverseOption(observable, Option::id);
    }
}
