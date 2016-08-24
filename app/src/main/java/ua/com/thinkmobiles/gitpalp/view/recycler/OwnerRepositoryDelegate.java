package ua.com.thinkmobiles.gitpalp.view.recycler;

import android.view.View;

import ua.com.thinkmobiles.gitpalp.R;
import ua.com.thinkmobiles.gitpalp.model.response.CurrentUserRepositories;

/**
 * Created by CAT_Caterpiller on 24.08.2016.
 */

public class OwnerRepositoryDelegate extends BaseAdapterDelegate<CurrentUserRepositories, OwnerRepositoryHolder> {

    @Override
    protected int getHolderLayoutRes() {
        return R.layout.card_owner_repository;
    }

    @Override
    protected OwnerRepositoryHolder getHolder(View inflatedView) {
        return new OwnerRepositoryHolder(inflatedView);
    }
}
