package ua.com.thinkmobiles.gitpalp.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import ua.com.thinkmobiles.gitpalp.R;
import ua.com.thinkmobiles.gitpalp.databinding.ActivityLoginBinding;
import ua.com.thinkmobiles.gitpalp.viewmodel.activity_vm.LoginActivityVM;

/**
 * Created by CAT_Caterpiller on 20.08.2016.
 */

public class LoginActivity extends BaseActivity {

    public ActivityLoginBinding loginBinding;
    private LoginActivityVM     loginActivityVM;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        injectDataBinding(loginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login));
        injectViewModel(loginActivityVM = new LoginActivityVM(this));
        loginBinding.setViewModel(loginActivityVM);
    }
}
