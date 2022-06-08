package intentactivityresult;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.appstone.androidbatch8.R;

public class TaskLoginActivity extends AppCompatActivity {

    private String email;
    private String password;
    private String mobile;

    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_login);

        EditText mEtEmail = findViewById(R.id.et_task_login_email);
        EditText mEtPassword = findViewById(R.id.et_task_login_password);
        EditText mEtMobile = findViewById(R.id.et_task_login_mobile);

        Button mBtnLogin = findViewById(R.id.btn_task_login);

        SharedPreferences prefManager = getApplicationContext().getSharedPreferences("APPSTONE", MODE_PRIVATE);
        editor = prefManager.edit();


        mBtnLogin.setOnClickListener(view -> {
            email = mEtEmail.getText().toString();
            password = mEtPassword.getText().toString();
            mobile = mEtMobile.getText().toString();

            editor.putString("TASK_EMAIL", email);
            editor.putString("TASK_PASSWORD", password);
            editor.putString("TASK_MOBILE", mobile);
            editor.putBoolean("IS_LOGIN", true);
            editor.apply();

            moveToHomeScreen();
        });

        email = prefManager.getString("TASK_EMAIL", "");
        password = prefManager.getString("TASK_PASSWORD", "");
        mobile = prefManager.getString("TASK_MOBILE", "");

        boolean isLogin = prefManager.getBoolean("IS_LOGIN", false);

        if (isLogin) {
            moveToHomeScreen();
        }
    }


    private void moveToHomeScreen() {
        Intent homeIntent = new Intent(TaskLoginActivity.this, TaskHomeActivity.class);
        homeIntent.putExtra("INTENT_EMAIL", email);
        homeIntent.putExtra("INTENT_PASSWORD", password);
        homeIntent.putExtra("INTENT_MOBILE", mobile);
        startActivity(homeIntent);
    }
}