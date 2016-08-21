package ua.com.thinkmobiles.gitpalp.viewmodel.activity_vm;

import android.content.Context;
import android.databinding.ObservableBoolean;
import android.view.View;

import ua.com.thinkmobiles.gitpalp.viewmodel.row_vm.SearchRowVM;

/**
 * Created by CAT_Caterpiller on 21.08.2016.
 */

public class RepositoryActivityVM extends SearchRowVM {

    private Context context;

    public final ObservableBoolean isProgressVisible = new ObservableBoolean(false);


    public RepositoryActivityVM(Context context, BackClicklistener backClicklistener, boolean isBackVisible) {
        super(backClicklistener, isBackVisible);
        this.context = context;
    }

    @Override
    public void clickSearch(View view) {

    }
}
