package ua.com.thinkmobiles.gitpalp.view.activity;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import ua.com.thinkmobiles.gitpalp.R;
import ua.com.thinkmobiles.gitpalp.databinding.ActivityRepositoryBinding;
import ua.com.thinkmobiles.gitpalp.viewmodel.activity_vm.ProfileActivityVM;
import ua.com.thinkmobiles.gitpalp.viewmodel.activity_vm.RepositoryActivityVM;
import ua.com.thinkmobiles.gitpalp.viewmodel.row_vm.SearchRowVM;

/**
 * Created by CAT_Caterpiller on 21.08.2016.
 */

public class RepositoryActivity extends BaseActivity implements SearchRowVM.BackClicklistener{

    public static final String SEARCH = "search";

    public ActivityRepositoryBinding    repositoryBinding;
    private RepositoryActivityVM        repositoryActivityVM;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String search = "";
        if(getIntent() != null) {
            search = getIntent().getStringExtra(SEARCH);
        }

        injectDataBinding(repositoryBinding     = DataBindingUtil.setContentView(this, R.layout.activity_repository));
        injectViewModel(repositoryActivityVM    = new RepositoryActivityVM(this, this, true, search));
        repositoryBinding                      .setViewModel(repositoryActivityVM);
    }

    @Override
    public void back() {
        this.onBackPressed();
    }
}
