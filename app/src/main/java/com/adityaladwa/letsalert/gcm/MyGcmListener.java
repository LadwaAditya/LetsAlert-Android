package com.adityaladwa.letsalert.gcm;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.adityaladwa.letsalert.App;
import com.adityaladwa.letsalert.MainActivity;
import com.adityaladwa.letsalert.R;
import com.google.android.gms.gcm.GcmListenerService;

import java.util.Random;

/**
 * Created by Aditya on 22-Apr-16.
 */
public class MyGcmListener extends GcmListenerService {
    private SharedPreferences mSharedPreferences;
    boolean policeSub, waterSub, electricitySub, collegeSub;
    private static final String TAG = MyGcmListener.class.getSimpleName();

    @Override
    public void onMessageReceived(String from, Bundle data) {
        String message = data.getString("message");
        String detail = data.getString("description");
        String department = data.getString("department");
        Log.d(TAG, "From: " + from);
        Log.d(TAG, "Message: " + message);
        Log.d(TAG, "Department: " + department);

        mSharedPreferences = ((App) getApplication()).getNetComponent().getSharedPreference();

        policeSub = mSharedPreferences.getBoolean(getString(R.string.pref_subscribe_police), true);
        waterSub = mSharedPreferences.getBoolean(getString(R.string.pref_subscribe_water), true);
        electricitySub = mSharedPreferences.getBoolean(getString(R.string.pref_subscribe_electricity), true);
        collegeSub = mSharedPreferences.getBoolean(getString(R.string.pref_subscribe_college), true);
        assert department != null;
        if (department.equals("police") && policeSub)
            sendNotification(message, detail);
        if (department.equals("water") && waterSub)
            sendNotification(message, detail);
        if (department.equals("electricity") && electricitySub)
            sendNotification(message, detail);
        if (department.equals("college") && collegeSub)
            sendNotification(message, detail);


    }

    private void sendNotification(String message, String detail) {
        int id = new Random().nextInt();
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent contentIntent = PendingIntent.getActivity(this, id, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.alarm)
                .setContentTitle(message)
                .setContentText(detail)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setColor(ContextCompat.getColor(MyGcmListener.this, R.color.colorAccent))
                .setContentIntent(contentIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);


        notificationManager.notify(id, notificationBuilder.build());
    }
}
