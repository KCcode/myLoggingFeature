package com.example.myloggingfeature;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileHelper {

    public void writeToFile(String data, Context context) {
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
                Log.i("something", "asdfa");
                /*try {
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("config.txt", Context.MODE_PRIVATE));
                    outputStreamWriter.write(data);
                    outputStreamWriter.close();
                } catch (IOException e) {
                    Log.e("Exception", "File write failed: " + e.toString());
                }*/
            }

        }
    }

    public void writeLog(){
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

}
