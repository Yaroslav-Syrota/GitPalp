package ua.com.thinkmobiles.gitpalp.viewmodel.row_vm;

import android.content.Context;
import android.databinding.ObservableField;
import android.text.TextUtils;

import ua.com.thinkmobiles.gitpalp.R;
import ua.com.thinkmobiles.gitpalp.model.response.CurrentUserRepositories;
import ua.com.thinkmobiles.gitpalp.viewmodel.ViewModel;

/**
 * Created by CAT_Caterpiller on 24.08.2016.
 */

public class OwnerRepositoryItemVM extends ViewModel{

    private CurrentUserRepositories repository;

    public final ObservableField<String> title          = new ObservableField<>();
    public final ObservableField<String> description    = new ObservableField<>();
    public final ObservableField<String> url            = new ObservableField<>();
    public final ObservableField<String> owner          = new ObservableField<>();

    public OwnerRepositoryItemVM(Context context) {
        super(context);
    }

    public void updateItem(CurrentUserRepositories reposit) {
        this.repository = reposit;
        title.set(repository.name);
        if(TextUtils.isEmpty(repository.description)) {
            description.set(context.getString(R.string.description_is_missing));
        } else {
            description.set(repository.description);
        }
        url.set(repository.updatedAt);
        owner.set(repository.language);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        repository = null;
    }
}
