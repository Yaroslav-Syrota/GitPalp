package ua.com.thinkmobiles.gitpalp.network;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;
import ua.com.thinkmobiles.gitpalp.model.request.LoginRequest;
import ua.com.thinkmobiles.gitpalp.model.response.CurrentUserRepositories;
import ua.com.thinkmobiles.gitpalp.model.response.CurrentUserResponse;
import ua.com.thinkmobiles.gitpalp.model.response.LoginResponse;
import ua.com.thinkmobiles.gitpalp.model.response.SearchQueryResponse;

/**
 * Created by CAT_Caterpiller on 21.08.2016.
 */

public interface ApiAuthorithation {

    @POST("authorizations")
    Observable<LoginResponse> login(@Header("Authorization") String _credentials,
                                    @Body LoginRequest _loginRequest);

    @GET("authorizations")
    Observable<List<LoginResponse>> getAuthorizations(@Header("Authorization") String _credentials);

    @DELETE("authorizations/{id}")
    Observable<ResponseBody> delete(@Header("Authorization") String _credentials,
                                    @Path("id") String id);

}
