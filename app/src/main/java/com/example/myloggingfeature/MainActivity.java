package com.example.myloggingfeature;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("sdafasdf", "fasda");
    }


    public void clickToAux(View view){
        Intent intent = new Intent(this, AuxActivity.class);
        startActivity(intent);
    }
}
