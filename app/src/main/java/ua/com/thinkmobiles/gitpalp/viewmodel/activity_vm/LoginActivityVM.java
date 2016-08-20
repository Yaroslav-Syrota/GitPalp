package ua.com.thinkmobiles.gitpalp.viewmodel.activity_vm;

import android.content.Context;
import android.databinding.ObservableBoolean;

import ua.com.thinkmobiles.gitpalp.viewmodel.ViewModel;

/**
 * Created by CAT_Caterpiller on 20.08.2016.
 */

public class LoginActivityVM extends ViewModel {

    private Context context;

    public final ObservableBoolean isProgressVisible = new ObservableBoolean(false);

    public LoginActivityVM(Context context) {
        this.context = context;
    }
}
