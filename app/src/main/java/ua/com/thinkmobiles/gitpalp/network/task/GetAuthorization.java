package ua.com.thinkmobiles.gitpalp.network.task;

import android.content.Context;

import java.util.List;

import rx.Observable;
import ua.com.thinkmobiles.gitpalp.model.response.LoginResponse;
import ua.com.thinkmobiles.gitpalp.network.RestApiClient;

/**
 * Created by CAT_Caterpiller on 22.08.2016.
 */

public class GetAuthorization extends ObservableTask<List<LoginResponse>> {

    private final String basic;

    public GetAuthorization(String basic) {
        super(true);
        this.basic = basic;
    }

    @Override
    protected Observable<List<LoginResponse>> getObservableTask(Context context) {
        return RestApiClient.getInstance().auth().getAuthorizations(basic);
    }
}
