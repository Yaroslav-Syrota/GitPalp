package ua.com.thinkmobiles.gitpalp.network.task;

import android.content.Context;

import java.util.List;

import rx.Observable;
import ua.com.thinkmobiles.gitpalp.model.response.CurrentUserRepositories;
import ua.com.thinkmobiles.gitpalp.network.RestApiClient;

/**
 * Created by CAT_Caterpiller on 24.08.2016.
 */

public class GetOwnerRepositories extends ObservableTask<List<CurrentUserRepositories>> {

    private final String token;

    public GetOwnerRepositories(String token) {
        super(true);
        this.token = token;
    }


    @Override
    protected Observable<List<CurrentUserRepositories>> getObservableTask(Context context) {
        return RestApiClient.getInstance().repo().getCurrentUserRepos(token);
    }
}
