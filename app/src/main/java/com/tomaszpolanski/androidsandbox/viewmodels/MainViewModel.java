package com.tomaszpolanski.androidsandbox.viewmodels;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

import java8.util.stream.Collectors;
import java8.util.stream.IntStreams;
import java8.util.stream.Stream;

public class MainViewModel extends BaseViewModel {


    public MainViewModel() {

        toList();
    }

    private static void iterateStream() {
        final Stream<String> st = getRangedStream();
        st.forEach(s -> Log.e("Print", s));
    }

    private static void toList() {
        final Stream<String> st = getRangedStream();
        final List<String> str = st.collect(Collectors.toList());
    }

    @NonNull
    private static Stream<String> getRangedStream() {
        return IntStreams.range(0, 1000)
                .limit(10)
                .mapToObj(i -> "" + i);
    }

}
