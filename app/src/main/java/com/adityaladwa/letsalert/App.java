package com.adityaladwa.letsalert;

import android.app.Application;

import com.adityaladwa.letsalert.dagger.component.DaggerNetComponent;
import com.adityaladwa.letsalert.dagger.component.NetComponent;
import com.adityaladwa.letsalert.dagger.module.AppModule;
import com.adityaladwa.letsalert.dagger.module.NetModule;

/**
 * Created by Aditya on 23-Apr-16.
 */
public class App extends Application {

    private NetComponent netComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        netComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule("http://letsalert.mybluemix.net"))
                .build();
    }

    public NetComponent getNetComponent() {
        return netComponent;
    }
}
