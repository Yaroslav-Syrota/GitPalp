package ua.com.thinkmobiles.gitpalp.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.databinding.ViewDataBinding;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;

import java.util.ArrayList;

import ua.com.thinkmobiles.gitpalp.viewmodel.ViewModel;
import ua.com.thinkmobiles.gitpalp.viewmodel.dialod_vm.BaseDialogVM;

/**
 * Created by CAT_Caterpiller on 20.08.2016.
 */

public abstract class BaseDialog extends Dialog implements BaseDialogVM.DialogCloseListener {

    private ArrayList<ViewModel> injectedModels         = new ArrayList<>();
    private ArrayList<ViewDataBinding> injectedBindings = new ArrayList<>();


    public BaseDialog(Context context) {
        super(context);
        setCancelable(true);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }


    protected void injectViewModel(final ViewModel viewModel) {
        injectedModels.add(viewModel);
    }

    protected void injectDataBinding(final ViewDataBinding injectedBinding) {
        injectedBindings.add(injectedBinding);
    }

    @Override
    public void closeDialog() {
        this.dismiss();
    }

    @Override
    public void dismiss() {
        super.dismiss();
        for (ViewDataBinding viewDataBinding: injectedBindings) {
            viewDataBinding.unbind();
        }
        for (ViewModel viewModel: injectedModels) {
            viewModel.onDestroy();
        }

        injectedBindings    .clear();
        injectedModels      .clear();
    }

}
