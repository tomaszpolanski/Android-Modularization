package com.tomaszpolanski.androidsandbox.viewmodels;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public abstract class BaseViewModel implements IDisposable {

    private final CompositeSubscription mCompositeSubscription = new CompositeSubscription();

    protected final void subscribe(final Subscription s) {
        mCompositeSubscription.add(s);
    }

    public void dispose() {
        mCompositeSubscription.clear();
    }
}
