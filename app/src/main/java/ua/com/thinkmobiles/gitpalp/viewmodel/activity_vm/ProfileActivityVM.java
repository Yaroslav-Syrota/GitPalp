package ua.com.thinkmobiles.gitpalp.viewmodel.activity_vm;

import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.view.View;
import android.widget.Toast;

import ua.com.thinkmobiles.gitpalp.utils.CircleTransform;
import ua.com.thinkmobiles.gitpalp.view.activity.RepositoryActivity;
import ua.com.thinkmobiles.gitpalp.viewmodel.row_vm.SearchRowVM;

/**
 * Created by CAT_Caterpiller on 21.08.2016.
 */

public class ProfileActivityVM extends SearchRowVM {

    private Context context;
    public final ObservableBoolean isProgressVisible = new ObservableBoolean(false);

    public final ObservableField<String> avatar         = new ObservableField<>();
    public final ObservableField<String> name           = new ObservableField<>();
    public final ObservableField<String> description    = new ObservableField<>();
    public final CircleTransform circleTransform;

    public ProfileActivityVM(Context context, BackClicklistener backClicklistener, boolean isBackVisible) {
        super(backClicklistener, isBackVisible);
        this.context    = context;
        circleTransform = new CircleTransform(context);
    }

    @Override
    public void clickSearch(View view) {
        context.startActivity(new Intent(context, RepositoryActivity.class));
    }
}
