package sharedpref;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.appstone.androidbatch8.R;

public class HomeActivity extends AppCompatActivity {


    SharedPreferences pref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        pref = getApplicationContext().getSharedPreferences("APPSTONE", MODE_PRIVATE);
        editor = pref.edit();
    }

    public void doLogoutClicked(View v1){
        editor.putString("EMAIL", "");
        editor.putString("PASSWORD", "");
        editor.putBoolean("ISLOGIN", false);
        editor.apply();

        Intent loginIntent = new Intent(HomeActivity.this, LoginActivity.class);
        startActivity(loginIntent);
        finish();
    }
}