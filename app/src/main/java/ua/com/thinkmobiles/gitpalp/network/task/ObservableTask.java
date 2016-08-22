package ua.com.thinkmobiles.gitpalp.network.task;

import android.content.Context;

import rx.Observable;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by CAT_Caterpiller on 22.08.2016.
 */

public abstract class ObservableTask<R> {

    private boolean isBackgroundTask = true;
    protected Context context;


    public ObservableTask(final boolean isBackground) {
        isBackgroundTask = isBackground;
    }

    protected abstract Observable<R> getObservableTask(final Context context);

    public Observable<R> getTask(final Context context) {
        this.context = context;
        Observable<R> observable;
        if (isBackgroundTask) {
            observable = Observable.defer(() -> getObservableTask(context));
        } else {
            observable = getObservableTask(context);
        }
        if (getErrorHandlerFunc() != null) {
            observable = observable.onErrorResumeNext(getErrorHandlerFunc());
        }
        observable = observable.observeOn(getObserveScheduler())
                .subscribeOn(getSubscribeScheduler())
                .unsubscribeOn(getSubscribeScheduler());
        return observable;
    }

    public Observable<R> getErrorHandler(final Throwable throwable) {
        return Observable.<R>create(subscriber -> {
            subscriber.onNext(null);
            subscriber.onCompleted();
        });
    }

    public Func1 getErrorHandlerFunc() {
        return new Func1<Throwable, Observable<? extends R>>() {
            @Override
            public Observable<? extends R> call(Throwable throwable) {
                throwable.printStackTrace();
                return getErrorHandler(throwable);
            }
        };
    }

    public Scheduler getObserveScheduler() {
        return AndroidSchedulers.mainThread();
    }

    public Scheduler getSubscribeScheduler() {
        return isBackgroundTask ? Schedulers.newThread() :
                Schedulers.io();
    }
}
