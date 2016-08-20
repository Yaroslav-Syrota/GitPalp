package ua.com.thinkmobiles.gitpalp.binding;

import android.databinding.BaseObservable;
import android.text.TextUtils;

/**
 * Created by CAT_Caterpiller on 20.08.2016.
 */

public class BindableString extends BaseObservable {
    String value;

    public String get() {
        return value != null ? value : "";
    }

    public void set(String value) {
        if (!TextUtils.equals(this.value,value)) {
            this.value = value;
            notifyChange();
        }
    }

    public boolean isEmpty() {
        return value == null || value.isEmpty();
    }
}
