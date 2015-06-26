package com.tomaszpolanski.androidsandbox.viewmodels;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;

import java8.util.function.Consumer;
import java8.util.function.IntSupplier;
import java8.util.function.IntUnaryOperator;
import java8.util.function.Supplier;
import java8.util.stream.IntStreams;
import java8.util.stream.Stream;
import java8.util.stream.StreamSupport;

public class MainViewModel extends BaseViewModel {


    public MainViewModel() {

//        final Observable<Integer> numbers = Observable.just(1);
//
//        subscribe(numbers.subscribe(number -> Log.e("Subscription", "Sub1: " + number)));

        //////////////

//        final Observable<Integer> numbers = Observable.just(1)
//                                                      .map(number -> expensiveOperation(number));
//
//        subscribe(numbers.subscribe(number -> Log.e("Subscription", "Sub1: " + number)));
//

        //////////////

//        final Observable<Integer> numbers = Observable.just(1)
//                                                      .map(number -> expensiveOperation(number));
//
//        subscribe(numbers.subscribe(number -> Log.e("Subscription", "Sub1: " + number)));
//        subscribe(numbers.subscribe(number -> Log.e("Subscription", "Sub2: " + number)));

        ///////////////
        //Cold
//        final ConnectableObservable<Integer> numbers = Observable.just(1)
//                                                                 .map(number -> expensiveOperation(number))
//                                                                 .publish();
//
//        subscribe(numbers.subscribe(number -> Log.e("Subscription", "Sub1: " + number)));
//        subscribe(numbers.subscribe(number -> Log.e("Subscription", "Sub2: " + number)));
//
//        subscribe(numbers.connect());

        ///////////
        // Hot
//        final PublishSubject<Integer> hot = PublishSubject.create();
//        final Observable<Integer> numbers = hot
//                .map(number -> expensiveOperation(number))
//                .share();
//
//
//
//        subscribe(numbers.subscribe(number -> Log.e("Subscription", "Sub1: " + number)));
//        subscribe(numbers.subscribe(number -> Log.e("Subscription", "Sub2: " + number)));
//        hot.onNext(2);
//        hot.onNext(4);
        //////////
        // Timer
//        final Observable<Integer> numbers = Observable.timer(2, 2, TimeUnit.SECONDS).map(lon -> lon.intValue())
//                                                                 .map(number -> expensiveOperation(number));
//                                                                //.share();
//
//        subscribe(numbers.subscribe(number -> Log.e("Subscription", "Sub1: " + number)));
//        subscribe(numbers.subscribe(number -> Log.e("Subscription", "Sub2: " + number)));

        ///////////
        // Why not cold like this?

//        final Observable<Integer> n = Observable.create((Subscriber<? super Integer> subscriber) -> {
//            subscriber.onNext(10);
//          //  subscriber.onCompleted();
//        });
//
//        final Observable<Integer> numbers = n
//                .map(number -> expensiveOperation(number))
//                .share();
//
//
//        subscribe(numbers.subscribe(number -> Log.e("Subscription", "Sub1: " + number)));
//        subscribe(numbers.subscribe(number -> Log.e("Subscription", "Sub2: " + number)));

        //

        final Stream<String> st = getRangedStream();
        st.forEach(s -> Log.e("QQQ", s));
    }

    @NonNull
    private static Stream<String> getRangedStream() {
        return IntStreams.range(0, 1000)
                .limit(10)
                .mapToObj(i -> "" + i);
    }

    private static int expensiveOperation(final int number) {
        Log.e("MainViewModel", "Expensive");
        return number;
    }


}
