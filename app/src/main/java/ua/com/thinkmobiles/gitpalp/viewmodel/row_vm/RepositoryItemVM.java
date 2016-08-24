package ua.com.thinkmobiles.gitpalp.viewmodel.row_vm;

import android.content.Context;
import android.databinding.ObservableField;
import android.text.TextUtils;

import ua.com.thinkmobiles.gitpalp.R;
import ua.com.thinkmobiles.gitpalp.model.response.SearchQueryResponse;
import ua.com.thinkmobiles.gitpalp.viewmodel.ViewModel;

/**
 * Created by CAT_Caterpiller on 24.08.2016.
 */

public class RepositoryItemVM extends ViewModel {

    private SearchQueryResponse.ItemsBean itemBean;

    public final ObservableField<String> title          = new ObservableField<>();
    public final ObservableField<String> description    = new ObservableField<>();
    public final ObservableField<String> url            = new ObservableField<>();
    public final ObservableField<String> date           = new ObservableField<>();
    public final ObservableField<String> language       = new ObservableField<>();

    public RepositoryItemVM(Context context) {
        super(context);
    }

    public void updateItem(SearchQueryResponse.ItemsBean item) {
        this.itemBean = item;
        title.set(itemBean.name);
        if(TextUtils.isEmpty(itemBean.description)) {
            description.set(context.getString(R.string.description_is_missing));
        } else {
            description.set(itemBean.description);
        }
        url.set(itemBean.htmlUrl);
        date.set(itemBean.createdAt);
        if(TextUtils.isEmpty(itemBean.language)) {
            language.set(context.getString(R.string.no_language));
        } else {
            language.set(itemBean.language);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        itemBean = null;
    }
}
