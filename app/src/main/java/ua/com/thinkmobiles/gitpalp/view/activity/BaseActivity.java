package ua.com.thinkmobiles.gitpalp.view.activity;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import ua.com.thinkmobiles.gitpalp.viewmodel.ViewModel;

/**
 * Created by CAT_Caterpiller on 19.08.2016.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private ArrayList<ViewModel> injectedModels         = new ArrayList<>();
    private ArrayList<ViewDataBinding> injectedBindings = new ArrayList<>();

    protected void injectViewModel(final ViewModel viewModel) {
        injectedModels.add(viewModel);
    }

    protected void injectDataBinding(final ViewDataBinding injectedBinding) {
        injectedBindings.add(injectedBinding);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        for (ViewDataBinding viewDataBinding: injectedBindings) {
            viewDataBinding.unbind();
        }
        for (ViewModel viewModel: injectedModels) {
            viewModel.onDestroy();
        }

        injectedBindings    .clear();
        injectedModels      .clear();

        super.onDestroy();
    }
}
