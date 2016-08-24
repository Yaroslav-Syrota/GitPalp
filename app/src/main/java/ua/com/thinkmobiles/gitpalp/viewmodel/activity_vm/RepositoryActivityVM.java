package ua.com.thinkmobiles.gitpalp.viewmodel.activity_vm;

import android.content.Context;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.view.View;

import java.util.ArrayList;

import ua.com.thinkmobiles.gitpalp.model.response.SearchQueryResponse;
import ua.com.thinkmobiles.gitpalp.network.task.Search;
import ua.com.thinkmobiles.gitpalp.view.recycler.RepositoryAdapter;
import ua.com.thinkmobiles.gitpalp.viewmodel.row_vm.SearchRowVM;

/**
 * Created by CAT_Caterpiller on 21.08.2016.
 */

public class RepositoryActivityVM extends SearchRowVM {


    public RepositoryActivityVM(Context context, BackClicklistener backClicklistener, boolean isBackVisible, String search) {
        super(context, backClicklistener, isBackVisible);
        this.context = context;
        this.search.set(search);
        recyclerAdapter = new RepositoryAdapter();
        searchRequest();
    }

    private void searchRequest() {
        if(search.get().length() >= 3) {
            isProgressVisible.set(true);
            addSubscription(new Search(search.get()).getTask(context)
                    .subscribe(this::onRepositorySuccess, this::onResponseError));
        }
    }

    private void onRepositorySuccess(SearchQueryResponse response) {
        loadFinished();

        recyclerAdapter.clearAllData();
        recyclerAdapter.addData((ArrayList) response.items);
    }

    @Override
    public void clickSearch(View view) {
        searchRequest();
    }
}
