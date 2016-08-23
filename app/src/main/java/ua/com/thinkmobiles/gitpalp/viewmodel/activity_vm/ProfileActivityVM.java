package ua.com.thinkmobiles.gitpalp.viewmodel.activity_vm;

import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import ua.com.thinkmobiles.gitpalp.data.DataStorage;
import ua.com.thinkmobiles.gitpalp.model.response.CurrentUserRepositories;
import ua.com.thinkmobiles.gitpalp.model.response.CurrentUserResponse;
import ua.com.thinkmobiles.gitpalp.network.RestApiClient;
import ua.com.thinkmobiles.gitpalp.utils.CircleTransform;
import ua.com.thinkmobiles.gitpalp.view.activity.RepositoryActivity;
import ua.com.thinkmobiles.gitpalp.view.dialog.MessageDialog;
import ua.com.thinkmobiles.gitpalp.viewmodel.row_vm.SearchRowVM;

/**
 * Created by CAT_Caterpiller on 21.08.2016.
 */

public class ProfileActivityVM extends SearchRowVM {

    public final ObservableBoolean isProgressVisible = new ObservableBoolean(false);

    public final ObservableField<String> avatar         = new ObservableField<>();
    public final ObservableField<String> name           = new ObservableField<>();
    public final ObservableField<String> description    = new ObservableField<>();
    public final CircleTransform circleTransform;

    public ProfileActivityVM(Context context, BackClicklistener backClicklistener, boolean isBackVisible) {
        super(context, backClicklistener, isBackVisible);
        this.context    = context;
        circleTransform = new CircleTransform(context);
        profileRequest();
    }

    private void profileRequest() {
        addSubscription(RestApiClient.getInstance().repo().getCurrentUser(DataStorage.getToken())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onGetTokenSuccess, this::onGetTokenError));
    }

    private void onGetTokenSuccess(CurrentUserResponse response) {
        CurrentUserResponse list = response;
        avatar.set(list.avatarUrl);
        name.set(list.login);
        description.set(list.htmlUrl + " ; public Repo: " + list.publicRepos);
    }

    private void onGetTokenError(Throwable throwable) {
        MessageDialog.getErrorDialog(context, throwable.getMessage());
    }

    @Override
    public void clickSearch(View view) {
        context.startActivity(new Intent(context, RepositoryActivity.class));
    }
}
