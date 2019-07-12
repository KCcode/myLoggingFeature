package com.example.myloggingfeature;

import android.app.Application;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.util.Log;
import android.widget.TextView;


import com.abangfadli.shotwatch.ScreenshotData;
import com.abangfadli.shotwatch.ShotWatch;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyApplication extends Application {
    public final static String EXTRA_MESSAGE = "com.example.myloggingfeature.MESSAGE";
    private ShotWatch mShotWatch;

    @Override
    public void onCreate(){
        super.onCreate();

        ContentResolver contentResolver = getContentResolver();

        ShotWatch.Listener listener = new ShotWatch.Listener() {
            @Override
            public void onScreenShotTaken(ScreenshotData screenshotData) {
                //Settings > myApp > Permissions
                if (isExternalStorageWritable()) {
                    Log.i("here", "here");
                    //It is only generating the folder using the .getExternalStorageDirectory method...look for another method to future proof it?
                    File appDirectory = new File(Environment.getExternalStorageDirectory() + "/NewApp");
                    File logDirectory = new File(appDirectory + "/log");
                    //Fix time
                    File logFile = new File(logDirectory, genTimeStampName("Lenny"));

                    if (!appDirectory.exists()) {
                        appDirectory.mkdir();
                    }

                    if (!logDirectory.exists()) {
                        logDirectory.mkdir();
                        Log.i("MyApplication", "Made dir");
                    }

                    if (!logFile.exists()) {
                        try {
                            logFile.createNewFile();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    try {
                        Process process = Runtime.getRuntime().exec("logcat -c");
                        process = Runtime.getRuntime().exec("logcat -f " +logFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                else if (isExternalStorageReadable()){
                    Log.i("MyApplication", "Only Readable");
                }
                else{
                    Log.i("MyApplication", "Not accesible");
                }

                onLogActivity(getApplicationContext());

            }
        };


        mShotWatch = new ShotWatch(contentResolver, listener);
        mShotWatch.register(); // Register to begin receive event
        //mShotWatch.unregister(); //unregister when the app goes to background


    }


    public String genTimeStampName(String user) {
        Date time = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH:mm");
        return user + dateFormat.format(time)+ ".txt";
    }

    public boolean isExternalStorageWritable(){
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals((state))){
            return true;
        }

        else {
            return false;
        }
    }

    /* Checks if external storage is available to at least read */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if ( Environment.MEDIA_MOUNTED.equals( state ) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals( state ) ) {
            return true;
        }
        return false;
    }

    public void onLogActivity(Context context){
        Intent intent = new Intent(this, Main2Activity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(EXTRA_MESSAGE, "from MyApplication");
        context.startActivity(intent);
    }


}
