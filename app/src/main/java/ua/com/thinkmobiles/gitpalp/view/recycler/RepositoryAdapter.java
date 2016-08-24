package ua.com.thinkmobiles.gitpalp.view.recycler;

import com.hannesdorfmann.adapterdelegates2.AdapterDelegatesManager;

import ua.com.thinkmobiles.gitpalp.model.response.SearchQueryResponse;

/**
 * Created by CAT_Caterpiller on 24.08.2016.
 */

public class RepositoryAdapter extends BaseRecyclerAdapter<SearchQueryResponse.ItemsBean>{

    @Override
    protected void initDelegatesManager(AdapterDelegatesManager<SearchQueryResponse.ItemsBean> delegatesManager) {
        delegatesManager.addDelegate(new RepositoryDelegate());
    }
}
