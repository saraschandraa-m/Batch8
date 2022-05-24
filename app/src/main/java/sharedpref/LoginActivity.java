package sharedpref;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.appstone.androidbatch8.R;

public class LoginActivity extends AppCompatActivity {

    EditText etEmail;
    EditText etPassword;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    private String email;
    private String password;

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
        email = etEmail.getText().toString();
        password = etPassword.getText().toString();

//        editor.putString("EMAIL", email);
//        editor.putString("PASSWORD", password);
//        editor.putBoolean("ISLOGIN", true);
//        editor.apply();

        moveToHomeScreen();
    }

    private void moveToHomeScreen() {
        Intent homeIntent = new Intent(LoginActivity.this, HomeActivity.class);
        homeIntent.putExtra("EMAIL", email);
        homeIntent.putExtra("PASSWORD", password);
        startActivityForResult(homeIntent, 124);
//        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 124){
            if(resultCode == Activity.RESULT_OK){
                String newEmail = data.getExtras().getString("EMAIL");
                etEmail.setText(newEmail);
            }else{
                Toast.makeText(LoginActivity.this, "User Cancelled", Toast.LENGTH_SHORT).show();
            }
        }
    }
}