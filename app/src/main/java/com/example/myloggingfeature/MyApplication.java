package com.example.myloggingfeature;

import android.app.Application;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import com.abangfadli.shotwatch.ScreenshotData;
import com.abangfadli.shotwatch.ShotWatch;


public class MyApplication extends Application {
    public final static String EXTRA_MESSAGE = "com.example.myloggingfeature.MESSAGE";
    private ShotWatch mShotWatch;
    public FileHelper logcatFile = new FileHelper();

    @Override
    public void onCreate(){
        super.onCreate();

        ContentResolver contentResolver = getContentResolver();

        ShotWatch.Listener listener = new ShotWatch.Listener() {
            @Override
            public void onScreenShotTaken(ScreenshotData screenshotData) {
                //Settings > myApp > Permissions
                logcatFile.writeLog();
                onLogActivity(getApplicationContext());
            }
        };

        mShotWatch = new ShotWatch(contentResolver, listener);
        mShotWatch.register(); // Register to begin receive event
        //mShotWatch.unregister(); //unregister when the app goes to background
    }

    public void onLogActivity(Context context){
        Intent intent = new Intent(this, LogActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(EXTRA_MESSAGE, "from MyApplication");
        context.startActivity(intent);
    }

}
