package ua.com.thinkmobiles.gitpalp.view.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.IntentCompat;
import android.text.InputType;
import android.widget.EditText;

import ua.com.thinkmobiles.gitpalp.R;
import ua.com.thinkmobiles.gitpalp.databinding.ActivityLoginBinding;
import ua.com.thinkmobiles.gitpalp.utils.KeyboardUtils;
import ua.com.thinkmobiles.gitpalp.viewmodel.activity_vm.LoginActivityVM;

/**
 * Created by CAT_Caterpiller on 20.08.2016.
 */

public class LoginActivity extends BaseActivity implements LoginActivityVM.LoginListener {

    public ActivityLoginBinding loginBinding;
    private LoginActivityVM     loginActivityVM;


    public static void startItAlone(final Context context) {
        final Intent intent = new Intent(context, LoginActivity.class);
        intent.setFlags(IntentCompat.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        injectDataBinding(loginBinding  = DataBindingUtil.setContentView(this, R.layout.activity_login));
        injectViewModel(loginActivityVM = new LoginActivityVM(this, this));
        loginBinding                    .setViewModel(loginActivityVM);
    }


    @Override
    public void showOrHidePassword() {
        EditText editText = loginBinding.etPasswordLA;
        if (editText != null) {
            if ((editText.getInputType() & InputType.TYPE_TEXT_VARIATION_PASSWORD) > 0) {
                editText.setInputType(InputType.TYPE_CLASS_TEXT
                        | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
            } else {
                editText.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD
                        | InputType.TYPE_CLASS_TEXT
                        | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
            }
            int position = editText.getText().length();
            editText.setSelection(position);
        }
    }

    @Override
    public void hideKeyboard() {
        KeyboardUtils.hideSoftKeyboard(this);
    }
}
