package com.adityaladwa.letsalert.dagger.component;


import android.content.SharedPreferences;

import com.adityaladwa.letsalert.MainActivity;
import com.adityaladwa.letsalert.MainFragment;
import com.adityaladwa.letsalert.SignUpActivity;
import com.adityaladwa.letsalert.dagger.module.AppModule;
import com.adityaladwa.letsalert.dagger.module.NetModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Aditya on 21-Apr-16.
 */
@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface NetComponent {
    void inject(MainActivity activity);

    void inject(MainFragment fragment);

    void inject(SignUpActivity activity);


    SharedPreferences getSharedPreference();
}
