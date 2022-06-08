package intentactivityresult;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.appstone.androidbatch8.R;

public class TaskHomeActivity extends AppCompatActivity {

    private TextView mTvEmail;
    private TextView mTvPassword;
    private TextView mTvMobile;

    private String email;
    private String password;
    private String mobile;

    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_home);


        mTvEmail = findViewById(R.id.tv_task_email);
        mTvMobile = findViewById(R.id.tv_task_mobile);
        mTvPassword = findViewById(R.id.tv_task_password);

        Button mBtnEdit = findViewById(R.id.btn_task_edit);
        Button mBtnLogout = findViewById(R.id.btn_task_logout);


        Bundle data = getIntent().getExtras();

        if (data != null) {
            email = data.getString("INTENT_EMAIL");
            password = data.getString("INTENT_PASSWORD");
            mobile = data.getString("INTENT_MOBILE");
        }

        mTvEmail.setText(email);
        mTvPassword.setText(password);
        mTvMobile.setText(mobile);

        SharedPreferences prefManager = getApplicationContext().getSharedPreferences("APPSTONE", MODE_PRIVATE);
        editor = prefManager.edit();

        mBtnLogout.setOnClickListener(view -> {


            editor.putString("TASK_EMAIL", "");
            editor.putString("TASK_PASSWORD", "");
            editor.putString("TASK_MOBILE", "");
            editor.putBoolean("IS_LOGIN", false);
            editor.apply();

            finish();
        });

        mBtnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editIntent = new Intent(TaskHomeActivity.this, TaskEditActivity.class);
                editIntent.putExtra("EDIT_EMAIL", email);
                editIntent.putExtra("EDIT_MOBILE", mobile);
                editIntent.putExtra("EDIT_PASSWORD", password);
                startActivityForResult(editIntent, 9452);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 9452) {
            if (resultCode == Activity.RESULT_OK) {
                Bundle editedValues = data.getExtras();
                if (editedValues != null) {
                    String newEmail = editedValues.getString("NEW_EMAIL");
                    String newPassword = editedValues.getString("NEW_PASSWORD");
                    String newMobile = editedValues.getString("NEW_MOBILE");


                    mTvEmail.setText(newEmail);
                    mTvMobile.setText(newMobile);
                    mTvPassword.setText(newPassword);

                    editor.putString("TASK_EMAIL", newEmail);
                    editor.putString("TASK_PASSWORD", newPassword);
                    editor.putString("TASK_MOBILE", newMobile);
                    editor.apply();
                }
            }
        }
    }
}