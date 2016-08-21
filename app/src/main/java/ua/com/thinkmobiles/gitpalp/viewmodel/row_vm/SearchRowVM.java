package ua.com.thinkmobiles.gitpalp.viewmodel.row_vm;

import android.databinding.ObservableBoolean;
import android.view.View;

import ua.com.thinkmobiles.gitpalp.binding.BindableString;
import ua.com.thinkmobiles.gitpalp.viewmodel.ViewModel;

/**
 * Created by CAT_Caterpiller on 21.08.2016.
 */

public abstract class SearchRowVM extends ViewModel {

    public final BindableString search              = new BindableString();
    public final ObservableBoolean isBackVisible    = new ObservableBoolean();
    private BackClicklistener backClicklistener;


    public SearchRowVM(BackClicklistener backClicklistener, boolean isBackVisible) {
        this.backClicklistener  = backClicklistener;
        this.isBackVisible      .set(isBackVisible);
    }

    public void clickBack(View view) {
        backClicklistener.back();
    }

    public abstract void clickSearch(View view);

    public interface BackClicklistener {
        void back();
    }
}
