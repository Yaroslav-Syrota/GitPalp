package ua.com.thinkmobiles.gitpalp.network.task;

import android.content.Context;

import rx.Observable;
import ua.com.thinkmobiles.gitpalp.model.response.CurrentUserResponse;
import ua.com.thinkmobiles.gitpalp.network.RestApiClient;

/**
 * Created by CAT_Caterpiller on 24.08.2016.
 */

public class GetProfile extends ObservableTask<CurrentUserResponse> {

    private final String token;

    public GetProfile(String token) {
        super(true);
        this.token = token;
    }

    @Override
    protected Observable<CurrentUserResponse> getObservableTask(Context context) {
        return RestApiClient.getInstance().repo().getCurrentUser(token);
    }
}
