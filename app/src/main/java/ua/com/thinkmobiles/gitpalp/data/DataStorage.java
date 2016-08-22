package ua.com.thinkmobiles.gitpalp.data;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by CAT_Caterpiller on 21.08.2016.
 */
public final class DataStorage {

    private static final String DATA_STORAGE = "data_storage";
    public static final String TOKEN = "token";

    private static Context context;
    private static SharedPreferences sharedPreferences;

    public static void initSharedPref(Context context) {
        DataStorage.context = context;
        if(sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(DATA_STORAGE, Context.MODE_PRIVATE);
        }
    }

    private static SharedPreferences getSharedPreferences() {
        if(sharedPreferences == null) {
            initSharedPref(context);
        }
        return sharedPreferences;
    }

    public static void saveToken(String token) {
        final SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putString(TOKEN, token);
        editor.apply();
    }

    public static String getToken() {
        return getSharedPreferences().getString(TOKEN, "");
    }
}
