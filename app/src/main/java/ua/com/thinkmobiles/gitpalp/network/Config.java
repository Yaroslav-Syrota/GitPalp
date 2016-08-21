package ua.com.thinkmobiles.gitpalp.network;

/**
 * Created by CAT_Caterpiller on 21.08.2016.
 */

public class Config {

    private static final String PRODUCTION_URL = "https://api.github.com";

    public static String getBaseUrl(){
        return PRODUCTION_URL;
    }
}
