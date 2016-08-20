package ua.com.thinkmobiles.gitpalp.viewmodel.dialod_vm;

import android.content.Context;
import android.databinding.ObservableField;

/**
 * Created by CAT_Caterpiller on 20.08.2016.
 */

public class MessageDialogVM extends BaseDialogVM{

    public final ObservableField<String> title          = new ObservableField<>();
    public final ObservableField<String> message        = new ObservableField<>();
    public final ObservableField<String> textDismissing  = new ObservableField<>();


    public MessageDialogVM(Context context, DialogCloseListener listener, final String title,
                           final String message, final String textDismissing) {
        super(context, listener);
        this.title          .set(title);
        this.message        .set(message);
        this.textDismissing  .set(textDismissing);
    }
}
