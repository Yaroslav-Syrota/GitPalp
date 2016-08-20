package ua.com.thinkmobiles.gitpalp.listener;

import android.view.View;
import android.widget.TextView;

import java.util.HashMap;

import ua.com.thinkmobiles.gitpalp.R;
import ua.com.thinkmobiles.gitpalp.viewmodel.activity_vm.LoginActivityVM;

/**
 * Created by CAT_Caterpiller on 20.08.2016.
 */

public class EdittextBackgroundListener {

    private HashMap<LoginActivityVM.FieldType, View> fieldMap = new HashMap<>();


    public void setEditField (LoginActivityVM.FieldType type, View field) {
        fieldMap.put(type, field);
    }

    public void setErrorBackground(LoginActivityVM.FieldType type) {
        TextView view = (TextView) fieldMap.get(type);
        if(view != null) {
            view.setBackgroundDrawable(view.getContext().getResources().getDrawable(R.drawable.bg_error_red));
            view.setTextColor(view.getContext().getResources().getColor(R.color.base_red));
            view.setHintTextColor(view.getContext().getResources().getColor(R.color.base_red));
            view.setTag(R.string.background_key, true);
        }
    }
}
