package ua.com.thinkmobiles.gitpalp.viewmodel.activity_vm;

import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableField;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import ua.com.thinkmobiles.gitpalp.data.DataStorage;
import ua.com.thinkmobiles.gitpalp.model.response.CurrentUserRepositories;
import ua.com.thinkmobiles.gitpalp.model.response.CurrentUserResponse;
import ua.com.thinkmobiles.gitpalp.network.task.GetOwnerRepositories;
import ua.com.thinkmobiles.gitpalp.network.task.GetProfile;
import ua.com.thinkmobiles.gitpalp.utils.CircleTransform;
import ua.com.thinkmobiles.gitpalp.view.activity.RepositoryActivity;
import ua.com.thinkmobiles.gitpalp.view.recycler.OwnerRepositoryAdapter;
import ua.com.thinkmobiles.gitpalp.viewmodel.row_vm.SearchRowVM;

/**
 * Created by CAT_Caterpiller on 21.08.2016.
 */

public class ProfileActivityVM extends SearchRowVM<CurrentUserRepositories> {

    public final ObservableField<String> avatar         = new ObservableField<>();
    public final ObservableField<String> name           = new ObservableField<>();
    public final ObservableField<String> description    = new ObservableField<>();
    public final CircleTransform circleTransform;

    public ProfileActivityVM(Context context, BackClicklistener backClicklistener, boolean isBackVisible) {
        super(context, backClicklistener, isBackVisible);
        this.context    = context;
        circleTransform = new CircleTransform(context);
        recyclerAdapter = new OwnerRepositoryAdapter();
        profileRequest();
    }

    private void profileRequest() {
        isProgressVisible.set(true);
        addSubscription(new GetProfile(DataStorage.getToken()).getTask(context)
                .subscribe(this::onProfileSuccess, this::onResponseError));
    }

    private void onProfileSuccess(CurrentUserResponse response) {
        if(response != null) {
            avatar.set(response.avatarUrl);
            name.set(response.login);
            description.set("public Repo: " + response.publicRepos + " /  " + response.htmlUrl);
        }

        repositoryRequest();
    }

    private void repositoryRequest() {
        addSubscription(new GetOwnerRepositories(DataStorage.getToken()).getTask(context)
                .subscribe(this::onRepositoriesSuccess, this::onResponseError));
    }

    private void onRepositoriesSuccess(List<CurrentUserRepositories> repositories) {
        loadFinished();

        recyclerAdapter.clearAllData();
        recyclerAdapter.addData((ArrayList) repositories);
    }

    @Override
    public void clickSearch(View view) {
        if(search.get().length() >= 3) {
            Intent intent = new Intent(context, RepositoryActivity.class);
            intent.putExtra(RepositoryActivity.SEARCH, search.get());
            context.startActivity(intent);
        }
    }
}
