package ua.com.thinkmobiles.gitpalp.network.task;

import android.content.Context;

import okhttp3.ResponseBody;
import rx.Observable;
import ua.com.thinkmobiles.gitpalp.network.RestApiClient;

/**
 * Created by CAT_Caterpiller on 22.08.2016.
 */

public class ClearAuthorization extends ObservableTask<ResponseBody> {

    private final String basic;
    private final String id;

    public ClearAuthorization(String basic, String id) {
        super(true);
        this.basic  = basic;
        this.id     = id;
    }

    @Override
    protected Observable<ResponseBody> getObservableTask(Context context) {
        return RestApiClient.getInstance().auth().delete(basic, id);
    }
}
