package com.appstone.androidbatch8;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class ThirdActivity extends AppCompatActivity {

    private static final String TAG = "Activity_Third";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        Log.i(TAG, "OnCreate Called");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "OnStart Called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "OnResume Called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "OnPause Called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "OnStop Called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "OnDestroy Called");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "OnRestart Called");
    }
}