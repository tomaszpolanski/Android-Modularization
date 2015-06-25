package com.tomaszpolanski.androidsandbox.viewmodels;

import android.util.Log;

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
    }

    private static int expensiveOperation(final int number) {
        Log.e("MainViewModel", "Expensive");
        return number;
    }


}
