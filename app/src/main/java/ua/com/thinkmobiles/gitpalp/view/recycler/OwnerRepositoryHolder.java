package ua.com.thinkmobiles.gitpalp.view.recycler;

import android.view.View;

import ua.com.thinkmobiles.gitpalp.databinding.CardOwnerRepositoryBinding;
import ua.com.thinkmobiles.gitpalp.model.response.CurrentUserRepositories;
import ua.com.thinkmobiles.gitpalp.viewmodel.row_vm.OwnerRepositoryItemVM;

/**
 * Created by CAT_Caterpiller on 24.08.2016.
 */

public class OwnerRepositoryHolder extends BaseRecyclerHolder<CurrentUserRepositories> {

    private CardOwnerRepositoryBinding  repositoryBinding;
    private OwnerRepositoryItemVM       repositoryItemVM;

    public OwnerRepositoryHolder(View itemView) {
        super(itemView);
        this.repositoryBinding = CardOwnerRepositoryBinding.bind(itemView);
        this.repositoryItemVM  = new OwnerRepositoryItemVM(itemView.getContext());
        this.repositoryBinding.setViewModel(repositoryItemVM);
    }

    @Override
    protected void contentUpdate(CurrentUserRepositories dataItem) {
        repositoryItemVM.updateItem(dataItem);
    }
}
