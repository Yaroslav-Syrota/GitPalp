package ua.com.thinkmobiles.gitpalp;

import android.app.Application;

/**
 * Created by CAT_Caterpiller on 19.08.2016.
 */

public class GitPalpApp extends Application {

    //TODO create app singleton for us DataStorage instance
    private static GitPalpApp INSTANCE;

    public static GitPalpApp getInstance() {
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
    }
}
