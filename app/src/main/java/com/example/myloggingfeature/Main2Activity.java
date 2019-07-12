package com.example.myloggingfeature;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class Main2Activity extends AppCompatActivity {
    private String TAG = Main2Activity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MyApplication.EXTRA_MESSAGE);
        Log.i(TAG, message);

        //Enter username
        //Description of the problem
        //Get the phone's modelm OS version
        //Write info to a file
    }
}