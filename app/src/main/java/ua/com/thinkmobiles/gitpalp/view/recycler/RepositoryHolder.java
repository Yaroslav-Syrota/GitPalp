package ua.com.thinkmobiles.gitpalp.view.recycler;

import android.view.View;

import ua.com.thinkmobiles.gitpalp.databinding.CardRepositoryBinding;
import ua.com.thinkmobiles.gitpalp.model.response.SearchQueryResponse;
import ua.com.thinkmobiles.gitpalp.viewmodel.row_vm.RepositoryItemVM;

/**
 * Created by CAT_Caterpiller on 24.08.2016.
 */

public class RepositoryHolder extends BaseRecyclerHolder<SearchQueryResponse.ItemsBean> {

    private CardRepositoryBinding   repositoryBinding;
    private RepositoryItemVM        repositoryItemVM;

    public RepositoryHolder(View itemView) {
        super(itemView);
        this.repositoryBinding = CardRepositoryBinding.bind(itemView);
        this.repositoryItemVM  = new RepositoryItemVM(itemView.getContext());
        this.repositoryBinding.setViewModel(repositoryItemVM);
    }

    @Override
    protected void contentUpdate(SearchQueryResponse.ItemsBean dataItem) {
        repositoryItemVM.updateItem(dataItem);
    }
}
