package ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.appstone.androidbatch8.R;

public class UIActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uiactivity);

        EditText etEmailAddress = findViewById(R.id.et_email_address);
        EditText etMobile = findViewById(R.id.et_mobile);
        EditText etpassword = findViewById(R.id.et_password);

        Button btnLogin = findViewById(R.id.btn_login);
        TextView tvDisplay = findViewById(R.id.tv_display);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etEmailAddress.getText().toString();
                String mobile = etMobile.getText().toString();
                String password = etpassword.getText().toString();

                tvDisplay.setText(email);
            }
        });
    }
}