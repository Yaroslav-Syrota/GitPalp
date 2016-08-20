package ua.com.thinkmobiles.gitpalp.view.dialog;

import android.content.Context;

import ua.com.thinkmobiles.gitpalp.R;
import ua.com.thinkmobiles.gitpalp.databinding.DialogMessageBinding;
import ua.com.thinkmobiles.gitpalp.viewmodel.dialod_vm.MessageDialogVM;

/**
 * Created by CAT_Caterpiller on 20.08.2016.
 */

public class MessageDialog extends BaseDialog {

    private MessageDialogVM         messageDialogVM;
    private DialogMessageBinding    dialogMessageBinding;


    public static MessageDialog getStandartDialog(Context context, String title, String message) {
        return new MessageDialog(context, title, message);
    }

    public static MessageDialog getErrorDialog(Context context, String message) {
        return new MessageDialog(context, context.getString(R.string.warning), message);
    }

    public static MessageDialog getCustomButtonNameDialog(Context context, String title, String message, String textDismissing) {
        return new MessageDialog(context, title, message, textDismissing);
    }


    private MessageDialog(Context context, final String title, final String message) {
        this(context, title, message, context.getString(R.string.ok));
    }

    private MessageDialog(Context context, final String title, final String message, final String textDismissing) {
        super(context);
        setCancelable(false);

        injectDataBinding(dialogMessageBinding  = DialogMessageBinding.inflate(getLayoutInflater()));
        injectViewModel(messageDialogVM         = new MessageDialogVM(context, this, title, message, textDismissing));
        dialogMessageBinding.setViewModel(messageDialogVM);

        setContentView(dialogMessageBinding.getRoot());
    }
}
