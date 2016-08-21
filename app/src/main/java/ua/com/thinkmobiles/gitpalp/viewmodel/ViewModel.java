package ua.com.thinkmobiles.gitpalp.viewmodel;

import android.content.Context;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by CAT_Caterpiller on 20.08.2016.
 */

public abstract class ViewModel {

    protected Context context;
    private CompositeSubscription compositeSubscription = new CompositeSubscription();


    public ViewModel(Context context) {
        this.context = context;
    }

    public void onDestroy() {
        if(compositeSubscription.hasSubscriptions()) {
            compositeSubscription.unsubscribe();
        }
        context = null;
    }

    protected void addSubscription(Subscription _subscription){
        compositeSubscription.add(_subscription);
    }
}
