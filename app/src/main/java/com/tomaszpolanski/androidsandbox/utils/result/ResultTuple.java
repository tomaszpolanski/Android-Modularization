package com.tomaszpolanski.androidsandbox.utils.result;

import com.tomaszpolanski.androidsandbox.utils.T;

public final class ResultTuple<F, S> extends T<F, S> {

    private ResultTuple(F first, S second) {
        super(first, second);
    }

    public static <F, S> Result<ResultTuple<F, S>> create(Result<F> firstResult, Result<S> secondOption) {
        return firstResult.flatMap(first -> secondOption.map(second -> new ResultTuple(first, second)));
    }

}
