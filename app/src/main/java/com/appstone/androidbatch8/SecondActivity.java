package com.appstone.androidbatch8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {

    private static final String TAG = "Activity_Second";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Log.i(TAG, "OnCreate Called");

        Button btnMoveThird = findViewById(R.id.btnThird);

        Button btnCloseMove = findViewById(R.id.btnCloseAndMove);

        btnMoveThird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent thirdIntent = new Intent(SecondActivity.this, ThirdActivity.class);
                startActivity(thirdIntent);
            }
        });

        btnCloseMove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent thirdIntent = new Intent(SecondActivity.this, ThirdActivity.class);
                startActivity(thirdIntent);
                finish();
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