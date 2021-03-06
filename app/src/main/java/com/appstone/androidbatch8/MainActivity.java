package com.appstone.androidbatch8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Activity_Main";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constraint);

        Log.i(TAG, "OnCreate Called");
        ConstraintLayout setupCard = findViewById(R.id.cl_setup);

        setupCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent secondIntent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(secondIntent);
            }
        });
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