package ua.com.thinkmobiles.gitpalp.network.task;

import android.content.Context;

import rx.Observable;
import ua.com.thinkmobiles.gitpalp.model.response.SearchQueryResponse;
import ua.com.thinkmobiles.gitpalp.network.RestApiClient;

/**
 * Created by CAT_Caterpiller on 24.08.2016.
 */

public class Search extends ObservableTask<SearchQueryResponse> {

    private final String search;

    public Search(String search) {
        super(true);
        this.search = search;
    }


    @Override
    protected Observable<SearchQueryResponse> getObservableTask(Context context) {
        return RestApiClient.getInstance().repo().search(search);
    }
}

