package ua.com.thinkmobiles.gitpalp.viewmodel;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by CAT_Caterpiller on 20.08.2016.
 */

public abstract class ViewModel {

    protected CompositeSubscription compositeSubscription = new CompositeSubscription();

    public void onDestroy() {
        if(compositeSubscription.hasSubscriptions()) {
            compositeSubscription.unsubscribe();
        }
    }
}
