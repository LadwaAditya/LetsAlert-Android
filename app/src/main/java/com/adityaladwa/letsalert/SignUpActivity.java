package com.adityaladwa.letsalert;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.adityaladwa.letsalert.api.EndPoint;
import com.adityaladwa.letsalert.api.model.Error;
import com.adityaladwa.letsalert.api.model.People;

import java.io.IOException;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SignUpActivity extends AppCompatActivity {


    @Bind(R.id.btn_signup)
    Button signup;

    @Bind(R.id.input_name)
    EditText name;
    @Bind(R.id.input_email)
    EditText email;
    @Bind(R.id.input_phone)
    EditText phone;

    @Inject
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        ((App) getApplication()).getNetComponent().inject(this);


    }

    @OnClick(R.id.btn_signup)
    public void Signup() {
        final ProgressDialog progressDialog = new ProgressDialog(SignUpActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

        People people = new People();
        people.setName(name.getText().toString());
        people.setEmail(email.getText().toString());
        people.setPhone(phone.getText().toString());

        Call<People> call = retrofit.create(EndPoint.class).signUp(people);

        call.enqueue(new Callback<People>() {
            @Override
            public void onResponse(Call<People> call, Response<People> response) {
                if (response.body() == null)
                    try {
                        Error error = (Error) retrofit.responseBodyConverter(Error.class, Error.class.getAnnotations()).convert(response.errorBody());
                        Toast.makeText(SignUpActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                        progressDialog.hide();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                else
                    Toast.makeText(SignUpActivity.this, response.body().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<People> call, Throwable t) {
                Toast.makeText(SignUpActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
