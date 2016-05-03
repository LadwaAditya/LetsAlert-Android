package com.adityaladwa.letsalert;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.adityaladwa.letsalert.api.EndPoint;
import com.adityaladwa.letsalert.api.model.Complaint;
import com.adityaladwa.letsalert.api.model.Error;

import java.io.IOException;
import java.lang.annotation.Annotation;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.HttpException;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ComplaintActivity extends AppCompatActivity implements View.OnClickListener {

    SharedPreferences mSharedPreferences;
    @Bind(R.id.department_spinner)
    Spinner spinner;
    @Bind(R.id.input_complaint_name)
    EditText editTextName;
    @Bind(R.id.btn_complaint)
    Button complaintBtn;

    String gcm, person;

    @Inject
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint);
        ButterKnife.bind(this);
        ((App) getApplication()).getNetComponent().inject(this);
        mSharedPreferences = ((App) getApplicationContext()).getNetComponent().getSharedPreference();
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.department_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        complaintBtn.setOnClickListener(this);


        gcm = mSharedPreferences.getString(getString(R.string.pref_token), "");
        person = mSharedPreferences.getString(getString(R.string.pref_user_name), "");

    }

    @Override
    public void onClick(View v) {

        try {
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {

        }

        Complaint complaint = new Complaint();
        complaint.setName(editTextName.getText().toString());
        complaint.setDepartment(spinner.getSelectedItem().toString());
        complaint.setPerson(person);
        complaint.setGcm(gcm);

        retrofit.create(EndPoint.class).postComplaint(complaint).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Observer<Complaint>() {
                    @Override
                    public void onCompleted() {
                        editTextName.setText("");
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof HttpException) {
                            //we have a HTTP exception (HTTP status code is not 200-300)
                            Converter<ResponseBody, Error> errorConverter =
                                    retrofit.responseBodyConverter(Error.class, new Annotation[0]);
                            //maybe check if ((HttpException) throwable).code() == 400 ??
                            try {
                                Error error = errorConverter.convert(((HttpException) e).response().errorBody());
                                Toast.makeText(ComplaintActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onNext(Complaint complaint) {
                        Toast.makeText(ComplaintActivity.this, complaint.getName() + " is posted", Toast.LENGTH_SHORT).show();
                    }
                });

    }
}
