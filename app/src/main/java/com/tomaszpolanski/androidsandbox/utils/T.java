package com.tomaszpolanski.androidsandbox.utils;

import rx.functions.Action2;
import rx.functions.Func2;

public class T<T1, T2> {
    public final T1 T1;
    public final T2 T2;

    public T(T1 t1, T2 t2) {
        T1 = t1;
        T2 = t2;
    }

    public static <T1, T2> T tuple(T1 t1, T2 t2) {
        return new T(t1, t2);
    }

    public <R> R map(Func2<T1, T2, R> func) {
        return func.call(T1, T2);
    }

    public void iter(Action2<T1, T2> action) {
        action.call(T1, T2);
    }
}

