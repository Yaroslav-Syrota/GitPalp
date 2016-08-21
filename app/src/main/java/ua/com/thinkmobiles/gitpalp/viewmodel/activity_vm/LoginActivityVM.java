package ua.com.thinkmobiles.gitpalp.viewmodel.activity_vm;

import android.content.Context;
import android.databinding.ObservableBoolean;
import android.text.TextUtils;
import android.view.View;

import ua.com.thinkmobiles.gitpalp.R;
import ua.com.thinkmobiles.gitpalp.binding.BindableString;
import ua.com.thinkmobiles.gitpalp.listener.EdittextBackgroundListener;
import ua.com.thinkmobiles.gitpalp.utils.ValidationUtils;
import ua.com.thinkmobiles.gitpalp.view.dialog.MessageDialog;
import ua.com.thinkmobiles.gitpalp.viewmodel.ViewModel;

/**
 * Created by CAT_Caterpiller on 20.08.2016.
 */

public class LoginActivityVM extends ViewModel {

    private Context context;

    public final BindableString email                   = new BindableString();
    public final BindableString password                = new BindableString();
    public final ObservableBoolean isProgressVisible    = new ObservableBoolean(false);

    public EdittextBackgroundListener   backgroundListener;
    public LoginListener                loginListener;

    public LoginActivityVM(Context context, LoginListener listener) {
        this.context        = context;
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
            if(!ValidationUtils.isValidEmail(email.get())) {
                backgroundListener.setErrorBackground(FieldType.EMAIL);
                MessageDialog.getErrorDialog(context, context.getString(R.string.email_not_valid)).show();
                return false;
            }
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
        isProgressVisible.set(true);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        context = null;
    }

    public enum FieldType {
        EMAIL, PASSWORD
    }

    public interface LoginListener {
        void showOrHidePassword();
        void hideKeyboard();
    }
}
