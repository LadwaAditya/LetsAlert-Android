package com.adityaladwa.letsalert;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.adityaladwa.letsalert.api.EndPoint;
import com.adityaladwa.letsalert.api.model.Error;
import com.adityaladwa.letsalert.api.model.People;
import com.adityaladwa.letsalert.gcm.RegistrationIntentService;

import java.io.IOException;
import java.lang.annotation.Annotation;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.HttpException;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SignUpActivity extends AppCompatActivity {

    private People people;
    private BroadcastReceiver mBroadcastReceiver;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor editor;

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
        mSharedPreferences = ((App) getApplicationContext()).getNetComponent().getSharedPreference();


    }

    @OnClick(R.id.btn_signup)
    public void Signup() {
        final ProgressDialog progressDialog = new ProgressDialog(SignUpActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

        String[] sub = {"police", "college", "water", "college"};
        people = new People();
        people.setName(name.getText().toString());
        people.setEmail(email.getText().toString());
        people.setPhone(phone.getText().toString());
        people.setSubscribe(sub);


        mBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                final String token = intent.getExtras().getString(getString(R.string.extra_token));
                people.setGcm(token);

                retrofit.create(EndPoint.class).signUpRx(people).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .unsubscribeOn(Schedulers.io())
                        .subscribe(new Observer<People>() {
                            @Override
                            public void onCompleted() {
                                progressDialog.hide();
                                editor = mSharedPreferences.edit();
                                editor.putBoolean(getString(R.string.pref_login), true);
                                editor.putString(getString(R.string.pref_user_name), name.getText().toString());
                                editor.putString(getString(R.string.pref_user_email), email.getText().toString());
                                editor.apply();
                                finish();
                                startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                            }

                            @Override
                            public void onError(Throwable e) {
                                progressDialog.hide();
                                if (e instanceof HttpException) {
                                    //we have a HTTP exception (HTTP status code is not 200-300)
                                    Converter<ResponseBody, Error> errorConverter =
                                            retrofit.responseBodyConverter(Error.class, new Annotation[0]);
                                    //maybe check if ((HttpException) throwable).code() == 400 ??
                                    try {
                                        Error error = errorConverter.convert(((HttpException) e).response().errorBody());
                                        Toast.makeText(SignUpActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                                    } catch (IOException e1) {
                                        e1.printStackTrace();
                                    }
                                }

                            }

                            @Override
                            public void onNext(People people) {
                                progressDialog.hide();
                                Toast.makeText(SignUpActivity.this, people.getName(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        };

        LocalBroadcastManager.getInstance(this).registerReceiver(mBroadcastReceiver, new IntentFilter(RegistrationIntentService.ACTION));
        Intent regIntent = new Intent(this, RegistrationIntentService.class);
        startService(regIntent);

    }
}
