package ua.com.thinkmobiles.gitpalp.network.task;

import android.content.Context;

import java.util.Collections;

import rx.Observable;
import ua.com.thinkmobiles.gitpalp.R;
import ua.com.thinkmobiles.gitpalp.model.request.LoginRequest;
import ua.com.thinkmobiles.gitpalp.model.response.LoginResponse;
import ua.com.thinkmobiles.gitpalp.network.RestApiClient;
import ua.com.thinkmobiles.gitpalp.utils.GithubUtils;

/**
 * Created by CAT_Caterpiller on 21.08.2016.
 */

public class Login extends ObservableTask<LoginResponse>{

    private final String basic;

    public Login(String basic) {
        super(true);
        this.basic = basic;
    }


    @Override
    protected Observable<LoginResponse> getObservableTask(Context context) {
        return RestApiClient.getInstance().auth().login(basic, getLoginRequest());
    }

    private LoginRequest getLoginRequest() {
        LoginRequest loginRequest   = new LoginRequest();
        loginRequest.appKey         = context.getString(R.string.client_id);
        loginRequest.appSecret      = context.getString(R.string.client_secret_id);
        loginRequest.note           = GithubUtils.getDeviceDescription();
        loginRequest.fingerprint    = GithubUtils.getHashedDeviceId();
        loginRequest.scopes         = Collections.singletonList("public_repo");
        return loginRequest;
    }
}
