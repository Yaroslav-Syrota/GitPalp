package ua.com.thinkmobiles.gitpalp.view.recycler;

import com.hannesdorfmann.adapterdelegates2.AdapterDelegatesManager;

import ua.com.thinkmobiles.gitpalp.model.response.CurrentUserRepositories;

/**
 * Created by CAT_Caterpiller on 24.08.2016.
 */

public class OwnerRepositoryAdapter extends BaseRecyclerAdapter<CurrentUserRepositories>{

    @Override
    protected void initDelegatesManager(AdapterDelegatesManager<CurrentUserRepositories> delegatesManager) {
        delegatesManager.addDelegate(new OwnerRepositoryDelegate());
    }
}
