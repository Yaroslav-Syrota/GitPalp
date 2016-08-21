package ua.com.thinkmobiles.gitpalp.viewmodel.activity_vm;

import android.databinding.ObservableBoolean;

import ua.com.thinkmobiles.gitpalp.viewmodel.row_vm.SearchRowVM;

/**
 * Created by CAT_Caterpiller on 21.08.2016.
 */

public class HomeActivityVM extends SearchRowVM {

    public final ObservableBoolean isProgressVisible = new ObservableBoolean(false);

    public HomeActivityVM() {

    }
}
