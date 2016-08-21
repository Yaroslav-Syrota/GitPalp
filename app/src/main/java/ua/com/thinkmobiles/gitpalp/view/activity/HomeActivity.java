package ua.com.thinkmobiles.gitpalp.view.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.IntentCompat;

import ua.com.thinkmobiles.gitpalp.R;
import ua.com.thinkmobiles.gitpalp.databinding.ActivityHomeBinding;
import ua.com.thinkmobiles.gitpalp.viewmodel.activity_vm.HomeActivityVM;

/**
 * Created by CAT_Caterpiller on 21.08.2016.
 */

public class HomeActivity extends BaseActivity {

    public ActivityHomeBinding  homeBinding;
    private HomeActivityVM      homeActivityVM;


    public static void startItAlone(final Context context) {
        final Intent intent = new Intent(context, HomeActivity.class);
        intent.setFlags(IntentCompat.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        injectDataBinding(homeBinding   = DataBindingUtil.setContentView(this, R.layout.activity_home));
        injectViewModel(homeActivityVM  = new HomeActivityVM());
        homeBinding                     .setViewModel(homeActivityVM);
    }
}
