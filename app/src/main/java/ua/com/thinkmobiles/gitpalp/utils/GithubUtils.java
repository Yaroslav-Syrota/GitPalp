package ua.com.thinkmobiles.gitpalp.utils;

import android.os.Build;
import android.provider.Settings;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

import ua.com.thinkmobiles.gitpalp.GitPalpApp;
import ua.com.thinkmobiles.gitpalp.R;

/**
 * Created by CAT_Caterpiller on 21.08.2016.
 */
public final class GithubUtils {

    private GithubUtils() {
        new IllegalAccessError();
    }


    public static String getDeviceDescription() {
        return GitPalpApp.getInstance().getString(R.string.app_name) + " " + Build.MANUFACTURER + " " + Build.MODEL;
    }

    public static String getHashedDeviceId() {
        String androidId = Settings.Secure.getString(GitPalpApp.getInstance().getContentResolver(),
                Settings.Secure.ANDROID_ID);
        if (androidId == null) {
            androidId = Build.FINGERPRINT;
        }

        try {
            MessageDigest digest    = MessageDigest.getInstance("SHA-1");
            byte[] result           = digest.digest(androidId.getBytes("UTF-8"));
            StringBuilder sb        = new StringBuilder();
            for (byte b : result) {
                sb.append(String.format(Locale.US, "%02X", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            // won't happen
            return androidId;
        }
    }
}
