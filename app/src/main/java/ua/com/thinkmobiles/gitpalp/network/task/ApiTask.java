package ua.com.thinkmobiles.gitpalp.network.task;

import android.content.Context;
import android.widget.Toast;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.adapter.rxjava.HttpException;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;
import ua.com.thinkmobiles.gitpalp.data.DataStorage;
import ua.com.thinkmobiles.gitpalp.model.response.ErrorResponse;
import ua.com.thinkmobiles.gitpalp.network.RestApiClient;
import ua.com.thinkmobiles.gitpalp.view.activity.LoginActivity;

/**
 * Created by CAT_Caterpiller on 22.08.2016.
 */

public abstract class ApiTask<R> extends ObservableTask<R> {

    public ApiTask() {
        super(true);
    }

    public ApiTask(boolean isBackground) {
        super(isBackground);
    }

    @Override
    public Observable<R> getErrorHandler(Throwable throwable) {
        return Observable.create(subscriber -> {
                    Observable.just(throwable)
                            .map(this::transformError)
                            .subscribe(HttpException -> parseError(subscriber,HttpException)
                                    ,Throwable::printStackTrace);
                }

        );
    }

    private ErrorResponse transformError(Throwable throwable) {
        ErrorResponse errorResponse = new ErrorResponse(throwable.getMessage(), null);
        try {
            if (throwable instanceof UnknownHostException) {
                errorResponse = new ErrorResponse(ErrorResponse.INTERNET_MISSING);
                errorResponse.setError(throwable.getMessage());
                errorResponse.setThrowable(throwable);
            } else if (throwable instanceof SocketTimeoutException) {
                errorResponse = new ErrorResponse(ErrorResponse.TIME_OUT_EXCEPTION);
                errorResponse.setError(throwable.getMessage());
            } else if (throwable instanceof IOException) {
                errorResponse = new ErrorResponse(ErrorResponse.INTERCEPTOR_EXCEPTION);
                errorResponse.setError(throwable.getMessage());
            } else {
                HttpException httpException = (HttpException) throwable;
                String errorBody = httpException.response().errorBody().string();
                errorResponse = RestApiClient.getInstance().getGson().fromJson(errorBody, ErrorResponse.class);
                errorResponse.setThrowable(throwable);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return errorResponse;
    }

    //todo deprecated TRY USE errorResponseObservable (only for develop)
    public static Subscription errorResponseSimpleShow(Context context, Throwable throwable, boolean isShow) {
        return Observable.just(throwable)
                .map(throwable1 ->
                        (ErrorResponse) throwable1)
                .subscribe(errorResponse -> {
                    showError(context, isShow, errorResponse);
                }, Throwable::printStackTrace);
    }

    public static Subscription errorResponseObservable(Context context, Throwable throwable
            , Action0 internetError, Action1<ErrorResponse> serverError) {
        return Observable.just(throwable)
                .map(throwable1 ->
                        (ErrorResponse) throwable1)
                .subscribe(errorResponse -> {
                    reacteOnError(context, internetError, serverError, errorResponse);
                }, Throwable::printStackTrace);
    }

    private static void reacteOnError(Context context
            , Action0 internetError, Action1<ErrorResponse> serverError, ErrorResponse errorResponse) {
        if (errorResponse.getCode() == ErrorResponse.INTERNET_MISSING || errorResponse.getCode() == ErrorResponse.TIME_OUT_EXCEPTION) {
            if (internetError!=null)
                internetError.call();
        }
        else if (errorResponse.getCode() == ErrorResponse.INTERCEPTOR_EXCEPTION) {
            Toast.makeText(context, errorResponse.getError(), Toast.LENGTH_SHORT).show();
        }
        //todo need to describe all server errors
        else {
            if (serverError != null) {
                serverError.call(errorResponse);
            }
        }
        //todo develop version
//        Toast.makeText(_context, errorResponse.getError(), Toast.LENGTH_SHORT).show();
    }


    //todo deprecated
    private static void showError(Context context, boolean isShow, ErrorResponse errorResponse) {
        if (isShow)
            Toast.makeText(context, errorResponse.getError(), Toast.LENGTH_SHORT).show();
    }

    private void parseError(Subscriber<? super R> subscriber, ErrorResponse errorResponse) {
        if (errorResponse.getCode() == 101) {
            DataStorage.saveToken("");
            //todo not sure
            subscriber.onCompleted();
            LoginActivity.startItAlone(context);
        } else {
            //todo on the development
//            if (errorResponse.getThrowable() == null)
//                errorResponse.setError(ErrorResponse.getErrorByCode(mContext, errorResponse.getCode()));
            subscriber.onError(errorResponse);
        }
    }

    public static final String EMAIL            = "email";
    public static final String PASSWORD         = "password";

}
