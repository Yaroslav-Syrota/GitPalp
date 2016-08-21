package ua.com.thinkmobiles.gitpalp.network;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import ua.com.thinkmobiles.gitpalp.model.response.CurrentUserRepositories;
import ua.com.thinkmobiles.gitpalp.model.response.CurrentUserResponse;
import ua.com.thinkmobiles.gitpalp.model.response.SearchQueryResponse;

/**
 * Created by CAT_Caterpiller on 21.08.2016.
 */

public interface ApiRepository {

    @GET("user")
    Observable<CurrentUserResponse> getCurrentUser(@Query("access_token") String _token);


    @GET("user/repos")
    Observable<List<CurrentUserRepositories>> getCurrentUserRepos(@Query("access_token") String _token);

    @GET("search/repositories")
    Observable<SearchQueryResponse> search(@Query("q") String _searchQuery);
}
