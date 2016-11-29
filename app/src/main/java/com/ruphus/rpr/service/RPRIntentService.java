package com.ruphus.rpr.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class RPRIntentService extends IntentService {

    private static final String TAG = RPRIntentService.class.getName();

    public RPRIntentService() {
        super("RPRIntentService");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(TAG, "Service about to start.");



        Log.d(TAG, "Service started.");
        BroadcastReceiver.completeWakefulIntent(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "Service destroyed.");
        sendBroadcast(new Intent("com.ruphus.rpr.SERVICE_STOPPED"));
    }
}
