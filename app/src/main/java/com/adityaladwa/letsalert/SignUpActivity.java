package com.adityaladwa.letsalert;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpActivity extends AppCompatActivity {


    @Bind(R.id.btn_signup)
    Button signup;

    @Bind(R.id.input_name)
    EditText name;
    @Bind(R.id.input_email)
    EditText email;
    @Bind(R.id.input_phone)
    EditText phone;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);


    }

    @OnClick(R.id.btn_signup)
    public void Signup() {
        Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show();
        final ProgressDialog progressDialog = new ProgressDialog(SignUpActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();
    }
}
