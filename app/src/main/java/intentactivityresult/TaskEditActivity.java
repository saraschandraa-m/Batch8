package intentactivityresult;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.appstone.androidbatch8.R;

public class TaskEditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_edit);

        EditText mEtEmail = findViewById(R.id.et_task_edit_email);
        EditText mEtMobile = findViewById(R.id.et_task_edit_mobile);
        EditText mEtPassword = findViewById(R.id.et_task_edit_password);

        Button btnEdit = findViewById(R.id.btn_task_edit);
        Button btnCancel = findViewById(R.id.btn_task_cancel);

        Bundle editData = getIntent().getExtras();

        String editEmail = editData.getString("EDIT_EMAIL");
        String editPassword = editData.getString("EDIT_PASSWORD");
        String editMobile = editData.getString("EDIT_MOBILE");

        mEtEmail.setText(editEmail);
        mEtMobile.setText(editMobile);
        mEtPassword.setText(editPassword);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newEmail = mEtEmail.getText().toString();
                String newMobile = mEtMobile.getText().toString();
                String newPassword = mEtPassword.getText().toString();


                Intent returnIntent = new Intent();
                returnIntent.putExtra("NEW_EMAIL", newEmail);
                returnIntent.putExtra("NEW_PASSWORD", newPassword);
                returnIntent.putExtra("NEW_MOBILE", newMobile);
                setResult(Activity.RESULT_OK, returnIntent);
                finish();

            }
        });

    }
}