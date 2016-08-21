package ua.com.thinkmobiles.gitpalp.viewmodel.dialod_vm;

import android.content.Context;
import android.view.View;

import ua.com.thinkmobiles.gitpalp.viewmodel.ViewModel;

/**
 * Created by CAT_Caterpiller on 20.08.2016.
 */

public abstract class BaseDialogVM extends ViewModel{

    protected DialogCloseListener closeListener;

    public BaseDialogVM(Context context, DialogCloseListener listener) {
        super(context);
        closeListener   = listener;
    }

    public void close(View view) {
        closeListener.closeDialog();
    }



    public interface DialogCloseListener {
        void closeDialog();
    }
}
