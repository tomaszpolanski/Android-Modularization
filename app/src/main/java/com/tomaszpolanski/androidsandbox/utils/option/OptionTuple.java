package com.tomaszpolanski.androidsandbox.utils.option;

import com.tomaszpolanski.androidsandbox.utils.T;

public final class OptionTuple<F, S> extends T<F, S> {

    private OptionTuple(F first, S second) {
        super(first, second);
    }

    public static <F, S> Option<OptionTuple<F, S>> create(Option<F> firstOption, Option<S> secondOption) {
        return firstOption.flatMap(first -> secondOption.map(second -> new OptionTuple<>(first, second)));
    }

    public static <F, S> Option<OptionTuple<F, S>> createOption(F first, Option<S> secondOption) {
        return secondOption.map(second -> new OptionTuple<>(first, second));
    }

    public static <F, S> Option<OptionTuple<F, S>> createOption(Option<F> firstOption, S second) {
        return firstOption.map(first -> new OptionTuple<>(first, second));
    }
}
