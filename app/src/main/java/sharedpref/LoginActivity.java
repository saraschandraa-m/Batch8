package sharedpref;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.appstone.androidbatch8.R;

public class LoginActivity extends AppCompatActivity {

    EditText etEmail;
    EditText etPassword;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.et_shared_email);
        etPassword = findViewById(R.id.et_shared_pwd);

        preferences = getApplicationContext().getSharedPreferences("APPSTONE", MODE_PRIVATE);
        editor = preferences.edit();


        boolean isLoggedIn = preferences.getBoolean("ISLOGIN", false);

        if (isLoggedIn) {
            moveToHomeScreen();
        }
    }

    public void onLoginButtonClicked(View v) {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        editor.putString("EMAIL", email);
        editor.putString("PASSWORD", password);
        editor.putBoolean("ISLOGIN", true);
        editor.apply();

        moveToHomeScreen();
    }

    private void moveToHomeScreen() {
        Intent homeIntent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(homeIntent);
        finish();
    }
}