package com.adityaladwa.letsalert.gcm;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.adityaladwa.letsalert.App;
import com.adityaladwa.letsalert.R;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

/**
 * Created by Aditya on 22-Apr-16.
 */
public class RegistrationIntentService extends IntentService {

    public static final String TAG = RegistrationIntentService.class.getSimpleName();
    public static final String ACTION = "registrationcomplete";
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor editor;


    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     */
    public RegistrationIntentService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        mSharedPreferences = ((App) getApplication()).getNetComponent().getSharedPreference();
        String token = null;

        try {
            Log.d(TAG, "Recieve intent");
            InstanceID instanceID = InstanceID.getInstance(this);
            token = instanceID.getToken(getString(R.string.gcm_defaultSenderId),
                    GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);
            Log.i(TAG, "GCM Registration Token: " + token);

            sendRegistrationToServer(token);

            editor = mSharedPreferences.edit();
            editor.putString(getString(R.string.pref_token), token);
            editor.apply();


        } catch (Exception e) {
            Log.d(TAG, "Failed to complete token refresh", e);

        }
        Intent registrationComplete = new Intent(ACTION);
        registrationComplete.putExtra(getString(R.string.extra_token), token);
        LocalBroadcastManager.getInstance(this).sendBroadcast(registrationComplete);

    }

    private void sendRegistrationToServer(String token) {
        // Add custom implementation, as needed.
    }
}
