package ua.com.thinkmobiles.gitpalp.view.recycler;

import android.view.View;

import ua.com.thinkmobiles.gitpalp.R;
import ua.com.thinkmobiles.gitpalp.model.response.SearchQueryResponse;

/**
 * Created by CAT_Caterpiller on 24.08.2016.
 */

public class RepositoryDelegate extends BaseAdapterDelegate<SearchQueryResponse.ItemsBean, RepositoryHolder> {

    @Override
    protected int getHolderLayoutRes() {
        return R.layout.card_repository;
    }

    @Override
    protected RepositoryHolder getHolder(View inflatedView) {
        return new RepositoryHolder(inflatedView);
    }
}
