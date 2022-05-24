package sharedpref;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.appstone.androidbatch8.R;

public class HomeActivity extends AppCompatActivity {


    SharedPreferences pref;
    SharedPreferences.Editor editor;
    private EditText displayText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        pref = getApplicationContext().getSharedPreferences("APPSTONE", MODE_PRIVATE);
        editor = pref.edit();


        Bundle newData = getIntent().getExtras();

        String email = newData.getString("EMAIL");
        String password = newData.getString("PASSWORD");

        displayText = findViewById(R.id.textView7);

        displayText.setText(email);
    }

    public void doLogoutClicked(View v1){
//        editor.putString("EMAIL", "");
//        editor.putString("PASSWORD", "");
//        editor.putBoolean("ISLOGIN", false);
//        editor.apply();
//
//        Intent loginIntent = new Intent(HomeActivity.this, LoginActivity.class);
//        startActivity(loginIntent);
//        finish();

        String newEmail = displayText.getText().toString();

        Intent returnIntent = new Intent();
        returnIntent.putExtra("EMAIL", newEmail);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }
}