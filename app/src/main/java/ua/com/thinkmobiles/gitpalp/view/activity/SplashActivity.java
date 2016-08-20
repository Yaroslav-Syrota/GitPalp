package ua.com.thinkmobiles.gitpalp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.content.IntentCompat;
import android.support.v7.app.AppCompatActivity;

import ua.com.thinkmobiles.gitpalp.R;

/**
 * Created by CAT_Caterpiller on 19.08.2016.
 */

public class SplashActivity extends AppCompatActivity {

    private Handler handler;
    private static final long SPLASH_DELAY = 3000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        handler = new Handler();
        handler.postDelayed(mSplashRunnable, SPLASH_DELAY);
    }

    private final Runnable mSplashRunnable = () -> {
        final Intent launchIntent;
        launchIntent = new Intent(SplashActivity.this, LoginActivity.class);
        launchIntent.setFlags(IntentCompat.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

        if (handler != null)
            handler.removeCallbacks(this.mSplashRunnable);
        startActivity(launchIntent);
        finish();
    };

    @Override
    protected void onDestroy() {
        if (handler != null) handler.removeCallbacks(mSplashRunnable);
        super.onDestroy();
    }
}
