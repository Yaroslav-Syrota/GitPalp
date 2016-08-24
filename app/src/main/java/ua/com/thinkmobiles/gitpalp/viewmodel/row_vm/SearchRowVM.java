package ua.com.thinkmobiles.gitpalp.viewmodel.row_vm;

import android.content.Context;
import android.databinding.ObservableBoolean;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import ua.com.thinkmobiles.gitpalp.binding.BindableString;
import ua.com.thinkmobiles.gitpalp.view.dialog.MessageDialog;
import ua.com.thinkmobiles.gitpalp.view.recycler.BaseRecyclerAdapter;
import ua.com.thinkmobiles.gitpalp.viewmodel.ViewModel;

/**
 * Created by CAT_Caterpiller on 21.08.2016.
 */

public abstract class SearchRowVM<T> extends ViewModel {

    public final BindableString search              = new BindableString();
    public final ObservableBoolean isBackVisible    = new ObservableBoolean();
    private BackClicklistener backClicklistener;

    public LinearLayoutManager      layoutManager;
    public BaseRecyclerAdapter<T>   recyclerAdapter;

    public final ObservableBoolean isProgressVisible = new ObservableBoolean(false);


    public SearchRowVM(Context context, BackClicklistener backClicklistener, boolean isBackVisible) {
        super(context);
        this.backClicklistener  = backClicklistener;
        this.isBackVisible      .set(isBackVisible);

        layoutManager = new LinearLayoutManager(context);
    }

    public void clickBack(View view) {
        backClicklistener.back();
    }

    protected void loadFinished() {
        isProgressVisible.set(false);
    }

    protected void onResponseError(Throwable throwable) {
        MessageDialog.getErrorDialog(context, throwable.getMessage());
    }

    public abstract void clickSearch(View view);


    public interface BackClicklistener {
        void back();
    }
}
