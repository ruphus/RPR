package com.ruphus.rpr.service;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;

public class BroadcastReceiver extends WakefulBroadcastReceiver {

    private static final String TAG = BroadcastReceiver.class.getName();

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "Received RPR broadcast message: " +intent.getAction());

        switch(intent.getAction()){
            case Intent.ACTION_BOOT_COMPLETED:
            case Intent.ACTION_MY_PACKAGE_REPLACED:
            case Intent.ACTION_PACKAGE_FIRST_LAUNCH:

                if (intent.getAction().equals(Intent.ACTION_PACKAGE_FIRST_LAUNCH))
                {
                    Log.d(TAG, "Package name: " + intent.getPackage());
                }


                Log.d(TAG, "Restarting service...");

                startWakefulService(context, new Intent(context, RPRIntentService.class));

                Log.d(TAG, "RPR service restarted.");
            break;

            case Intent.ACTION_SHUTDOWN:
            case Intent.ACTION_PACKAGE_REMOVED:
            case Intent.ACTION_PACKAGE_FULLY_REMOVED:
                //TODO stop service
                Log.d(TAG, "Stop action not implemented.");
            break;

            default:
                Log.d(TAG, "Action not implemented.");
        }
    }
}
