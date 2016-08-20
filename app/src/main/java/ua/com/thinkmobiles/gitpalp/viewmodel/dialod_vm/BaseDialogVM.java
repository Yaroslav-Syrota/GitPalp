package ua.com.thinkmobiles.gitpalp.viewmodel.dialod_vm;

import android.content.Context;
import android.view.View;

import ua.com.thinkmobiles.gitpalp.viewmodel.ViewModel;

/**
 * Created by CAT_Caterpiller on 20.08.2016.
 */

public abstract class BaseDialogVM extends ViewModel{

    protected Context context;
    protected DialogCloseListener closeListener;

    public BaseDialogVM(Context context, DialogCloseListener listener) {
        this.context    = context;
        closeListener   = listener;
    }

    public void close(View view) {
        closeListener.closeDialog();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        context = null;
    }

    public interface DialogCloseListener {
        void closeDialog();
    }
}
