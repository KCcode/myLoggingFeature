package com.example.myloggingfeature;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LogActivity extends AppCompatActivity {
    public static String TAG = LogActivity.class.getName();
    EditText user, problem;
    public String username, description, device, phoneModel, osVersion;
    public FileHelper file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MyApplication.EXTRA_MESSAGE);
        Log.i(TAG, message);

        user = findViewById(R.id.etUser);
        problem = findViewById(R.id.etComment);

        username = user.getText().toString();
        description = problem.getText().toString();

        //Enter username
        //Description of the problem
        //Get the phone's modelm OS version
        //Write info to a file
    }

    public void generateReport(){
        device = Build.DEVICE;
        phoneModel = Build.MODEL;
        osVersion = Build.VERSION.RELEASE;

        Log.i(TAG, username + " " + description + " " + device + " " + phoneModel + " " + osVersion);
    }


    public void onSaveFile(View view){
        file.writeToFile(username, getApplicationContext());
    }
}