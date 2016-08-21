package ua.com.thinkmobiles.gitpalp.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by CAT_Caterpiller on 21.08.2016.
 */

public class RestApiClient {

    static final int CONNECTION_TIMEOUT = 20;

    private static RestApiClient INSTANCE;
    private Retrofit retrofit;
    private Gson gson;

    private RestApiClient() {}

    public static synchronized RestApiClient getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new RestApiClient();
            INSTANCE.build();
        }
        return INSTANCE;
    }


    private void build() {
        final OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();

        clientBuilder.readTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS);
        clientBuilder.connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS);

        final OkHttpClient client = clientBuilder.build();

        retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Config.getBaseUrl())
                .client(client)
                .build();

        gson = new GsonBuilder().create();
    }

    public Gson getGson() {
        return gson;
    }

    public ApiAuthorithation auth() {
        return retrofit.create(ApiAuthorithation.class);
    }
}
