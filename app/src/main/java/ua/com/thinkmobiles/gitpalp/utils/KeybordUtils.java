package ua.com.thinkmobiles.gitpalp.utils;

import android.app.Activity;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * Created by CAT_Caterpiller on 20.08.2016.
 */

public class KeybordUtils {

    public static void hideSoftKeyboard(final Activity _activity) {
        if(_activity != null && _activity.getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) _activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(_activity.getCurrentFocus().getWindowToken(), 0);
        }
    }

    //TODO this method for search field (search repository)
    public static void showKeyboard(final EditText _editText) {
        if (_editText != null) {
            InputMethodManager imm = (InputMethodManager) _editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(_editText, InputMethodManager.SHOW_IMPLICIT);
        }
    }
}
