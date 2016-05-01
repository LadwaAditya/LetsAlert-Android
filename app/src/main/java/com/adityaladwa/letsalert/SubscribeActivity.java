package com.adityaladwa.letsalert;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SubscribeActivity extends AppCompatActivity implements View.OnClickListener {

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor editor;

    @Bind(R.id.chk_police)
    CheckBox police;

    @Bind(R.id.chk_water)
    CheckBox water;

    @Bind(R.id.chk_electricity)
    CheckBox electricity;

    @Bind(R.id.chk_college)
    CheckBox college;

    boolean policeSub, waterSub, electricitySub, collegeSub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscribe);
        ButterKnife.bind(this);

        mSharedPreferences = ((App) getApplication()).getNetComponent().getSharedPreference();

        policeSub = mSharedPreferences.getBoolean(getString(R.string.pref_subscribe_police), true);
        waterSub = mSharedPreferences.getBoolean(getString(R.string.pref_subscribe_water), true);
        electricitySub = mSharedPreferences.getBoolean(getString(R.string.pref_subscribe_electricity), true);
        collegeSub = mSharedPreferences.getBoolean(getString(R.string.pref_subscribe_college), true);

        if (policeSub)
            police.setChecked(true);
        if (waterSub)
            water.setChecked(true);
        if (electricitySub)
            electricity.setChecked(true);
        if (collegeSub)
            college.setChecked(true);

        police.setOnClickListener(this);
        water.setOnClickListener(this);
        electricity.setOnClickListener(this);
        college.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.chk_police:
                if (((CheckBox) v).isChecked()) {
                    editor = mSharedPreferences.edit();
                    editor.putBoolean(getString(R.string.pref_subscribe_police), true);
                    editor.apply();
                } else {
                    editor = mSharedPreferences.edit();
                    editor.putBoolean(getString(R.string.pref_subscribe_police), false);
                    editor.apply();
                }
                break;
            case R.id.chk_water:
                if (((CheckBox) v).isChecked()) {
                    editor = mSharedPreferences.edit();
                    editor.putBoolean(getString(R.string.pref_subscribe_water), true);
                    editor.apply();
                } else {
                    editor = mSharedPreferences.edit();
                    editor.putBoolean(getString(R.string.pref_subscribe_water), false);
                    editor.apply();
                }
                break;
            case R.id.chk_electricity:
                if (((CheckBox) v).isChecked()) {
                    editor = mSharedPreferences.edit();
                    editor.putBoolean(getString(R.string.pref_subscribe_electricity), true);
                    editor.apply();
                } else {
                    editor = mSharedPreferences.edit();
                    editor.putBoolean(getString(R.string.pref_subscribe_electricity), false);
                    editor.apply();
                }
                break;
            case R.id.chk_college:
                if (((CheckBox) v).isChecked()) {
                    editor = mSharedPreferences.edit();
                    editor.putBoolean(getString(R.string.pref_subscribe_college), true);
                    editor.apply();
                } else {
                    editor = mSharedPreferences.edit();
                    editor.putBoolean(getString(R.string.pref_subscribe_college), false);
                    editor.apply();
                }
                break;
        }


    }
}
