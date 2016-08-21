package ua.com.thinkmobiles.gitpalp.viewmodel.activity_vm;

import android.content.Context;
import android.databinding.ObservableBoolean;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import ua.com.thinkmobiles.gitpalp.GitPalpApp;
import ua.com.thinkmobiles.gitpalp.R;
import ua.com.thinkmobiles.gitpalp.binding.BindableString;
import ua.com.thinkmobiles.gitpalp.listener.EdittextBackgroundListener;
import ua.com.thinkmobiles.gitpalp.model.request.LoginRequest;
import ua.com.thinkmobiles.gitpalp.model.response.LoginResponse;
import ua.com.thinkmobiles.gitpalp.network.RestApiClient;
import ua.com.thinkmobiles.gitpalp.utils.GithubUtils;
import ua.com.thinkmobiles.gitpalp.utils.ValidationUtils;
import ua.com.thinkmobiles.gitpalp.view.dialog.MessageDialog;
import ua.com.thinkmobiles.gitpalp.viewmodel.ViewModel;

/**
 * Created by CAT_Caterpiller on 20.08.2016.
 */

public class LoginActivityVM extends ViewModel {

    public final BindableString email                   = new BindableString();
    public final BindableString password                = new BindableString();
    public final ObservableBoolean isProgressVisible    = new ObservableBoolean(false);

    public EdittextBackgroundListener   backgroundListener;
    public LoginListener                loginListener;

    public LoginActivityVM(Context context, LoginListener listener) {
        super(context);
        backgroundListener  = new EdittextBackgroundListener();
        loginListener       = listener;
    }


    public void onClickEye(View view) {
        loginListener.showOrHidePassword();
    }

    public void clickLogin(View view) {
        loginListener.hideKeyboard();
        if(validData()) {
            ValidationUtils.unDoubleClick(view);
            login();
        }
    }

    private boolean validData() {
        if(ifAllFieldsFull()) {
            // TODO: it’s username not email
//            if(!ValidationUtils.isValidEmail(email.get())) {
//                backgroundListener.setErrorBackground(FieldType.EMAIL);
//                MessageDialog.getErrorDialog(context, context.getString(R.string.email_not_valid)).show();
//                return false;
//            }
            if(!ValidationUtils.isValidPassword(password.get())) {
                backgroundListener.setErrorBackground(FieldType.PASSWORD);
                MessageDialog.getErrorDialog(context, context.getString(R.string.password_not_valid)).show();
                return false;
            }
            return true;
        }
        return false;
    }

    private boolean ifAllFieldsFull() {
        if(TextUtils.isEmpty(email.get()) & TextUtils.isEmpty(password.get())) {
            backgroundListener.setErrorBackground(FieldType.EMAIL);
            backgroundListener.setErrorBackground(FieldType.PASSWORD);
            MessageDialog.getErrorDialog(context, context.getString(R.string.fill_all_fields)).show();
            return false;
        }
        if(TextUtils.isEmpty(email.get())) {
            backgroundListener.setErrorBackground(FieldType.EMAIL);
            MessageDialog.getErrorDialog(context, context.getString(R.string.email_empty)).show();
            return false;
        }
        if(TextUtils.isEmpty(password.get())) {
            backgroundListener.setErrorBackground(FieldType.PASSWORD);
            MessageDialog.getErrorDialog(context, context.getString(R.string.password_empty)).show();
            return false;
        }
        return true;
    }

    private void login() {

        String credentials = email.get() + ":" + password.get();
        final String basic =
                "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);

        LoginRequest loginRequest   = new LoginRequest();
        loginRequest.appKey         = context.getString(R.string.client_id);
        loginRequest.appSecret      = context.getString(R.string.client_secret_id);
        loginRequest.note           = GithubUtils.getDeviceDescription();
        loginRequest.fingerprint    = GithubUtils.getHashedDeviceId();
        loginRequest.scopes         = Collections.singletonList("public_repo");


        addSubscription(RestApiClient.getInstance()
                .auth()
                .getAuthorizations(basic)
                .doOnNext(loginResponses    -> onCheckAlreadyExistUser(basic, loginResponses))
                .flatMap(loginResponse      -> RestApiClient.getInstance().auth()
                        .login(basic, loginRequest))
                .map(_loginResponse1        -> _loginResponse1.token)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onGetTokenSuccess, this::onGetTokenError));
    }

    private void onCheckAlreadyExistUser(String basic, List<LoginResponse> loginResponses) {
        for (LoginResponse loginResponse : loginResponses) {
            String fingerprint = loginResponse.fingerprint;
            if (fingerprint != null && fingerprint.equals(GithubUtils.getHashedDeviceId())){
                addSubscription(RestApiClient.getInstance()
                        .auth().delete(basic, loginResponse.id).subscribe());
            }
        }
    }

    private void onGetTokenSuccess(String token) {
        Toast.makeText(GitPalpApp.getInstance(), token, Toast.LENGTH_SHORT).show();
        // TODO: make saving token and start Activity
    }

    private void onGetTokenError(Throwable throwable) {
        MessageDialog.getErrorDialog(context, throwable.getMessage());
    }



    public enum FieldType {
        EMAIL, PASSWORD
    }

    public interface LoginListener {
        void showOrHidePassword();
        void hideKeyboard();
    }
}
