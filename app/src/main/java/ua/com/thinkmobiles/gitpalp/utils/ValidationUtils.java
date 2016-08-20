package ua.com.thinkmobiles.gitpalp.utils;

import android.text.TextUtils;
import android.view.View;

/**
 * Created by CAT_Caterpiller on 20.08.2016.
 */

public class ValidationUtils {

    public static void unDoubleClick(View v) {
        v.setClickable(false);
        v.postDelayed(() -> v.setClickable(true),1000);
    }

    public static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean isValidPassword(String password) {
        if(password.length() < 6) return false;
        return true;
    }
}
