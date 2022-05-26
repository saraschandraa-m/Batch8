package intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.appstone.androidbatch8.R;

public class ImplicitIntentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_implicit_intent);

        EditText etPhone = findViewById(R.id.et_im_phone);
        Button btnCall = findViewById(R.id.btn_imp_call);

        TextView tvBrowser = findViewById(R.id.tv_im_browser);


        EditText mEtEmail = findViewById(R.id.et_imp_email);
        EditText mEtSubject = findViewById(R.id.et_imp_subject);
        EditText mEtMessage = findViewById(R.id.et_imp_message);
        Button btnSendMail = findViewById(R.id.btn_imp_send_email);

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNumber = etPhone.getText().toString();

                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" + phoneNumber));
                startActivity(callIntent);
            }
        });

        tvBrowser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                browserIntent.setData(Uri.parse("https://nextstacks.com"));
                startActivity(browserIntent);
            }
        });

        btnSendMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailaddress = mEtEmail.getText().toString();
                String message = mEtMessage.getText().toString();
                String subject = mEtSubject.getText().toString();

                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
                emailIntent.putExtra(Intent.EXTRA_TEXT, message);
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{emailaddress});
                emailIntent.setType("message/rfc822");
                startActivity(emailIntent);
            }
        });
    }
}