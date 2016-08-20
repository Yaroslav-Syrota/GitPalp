package ua.com.thinkmobiles.gitpalp.binding;

import android.databinding.BindingAdapter;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;

import ua.com.thinkmobiles.gitpalp.R;
import ua.com.thinkmobiles.gitpalp.listener.EdittextBackgroundListener;
import ua.com.thinkmobiles.gitpalp.viewmodel.activity_vm.LoginActivityVM;

/**
 * Created by CAT_Caterpiller on 20.08.2016.
 */

public abstract class Converter {

    @BindingAdapter({"bind:binding"})
    public static void bindEditTextField(TextView view, final BindableString bindableString) {
        if (view.getTag(R.id.binded) == null) {
            view.setTag(R.id.binded, true);
            view.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(view.getTag(R.string.background_key) != null && (boolean) view.getTag(R.string.background_key)) {
                        view.setTag(R.string.background_key, false);

                        view.setBackgroundDrawable(view.getContext().getResources().getDrawable(R.drawable.edit_background));
                        view.setTextColor(view.getContext().getResources().getColor(R.color.primaryText));
                    }
                    if(s.length() == 0) {
                        view.setHintTextColor(view.getContext().getResources().getColor(R.color.secondaryText));
                    }

                    bindableString.set(s.toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }
        String newValue = bindableString.get();
        if (!view.getText().toString().equals(newValue)) {
            view.setText(newValue);
        }
    }

    @BindingAdapter({"bind:backgroundListener", "bind:type"})
    public static void setEditField(final View view, final EdittextBackgroundListener listener,
                                    final LoginActivityVM.FieldType type) {
        listener.setEditField(type, view);
    }
}
