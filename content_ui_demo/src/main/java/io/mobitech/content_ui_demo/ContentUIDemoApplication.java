package io.mobitech.content_ui_demo;

import android.app.Application;
import android.os.AsyncTask;
import android.provider.Settings;
import android.util.Log;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;

import java.util.UUID;

import io.mobitech.content.ContentService;

/**
 * Created by Viacheslav Titov on 14.09.2016.
 */

public class ContentUIDemoApplication extends Application {

    private static final String TAG = ContentUIDemoApplication.class.getPackage() + "." + ContentUIDemoApplication.class.getSimpleName();

    public static String userID = "";

    @Override
    public void onCreate() {
        super.onCreate();

        //callback for user id:
        //Either takes the user's advertiserId or creates a unique ID, if
        AsyncTask<Void, Void, String> task = new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                try {
                    AdvertisingIdClient.Info adInfo =  AdvertisingIdClient.getAdvertisingIdInfo(ContentUIDemoApplication.this);
                    if (!adInfo.isLimitAdTrackingEnabled()) {//if user hasn't opt-out
                        return  adInfo.getId();
                    }else{
                        //Settings.Secure.ANDROID_ID: A 64-bit number (as a hex string) that is randomly
                        // generated when the user first sets up the device and should remain
                        // constant for the lifetime of the user's device.
                        String android_id = Settings.Secure.getString(ContentUIDemoApplication.this.getContentResolver(),
                                Settings.Secure.ANDROID_ID);

                        //if ANDROID_ID is unavailable - generate a random ID
                        return android_id == null ?  new UUID(System.currentTimeMillis(), System.currentTimeMillis()*2).toString() : android_id;
                    }
                } catch (Exception e) {
                    Log.e(TAG, e.getMessage(), e);
                }
                //default return is empty, in order to catch issues with user id generation
                return "";
            }

            @Override
            protected void onPostExecute(String advertId) {
                userID = advertId;
                //init Mobitech's content SDK
                ContentService.init(ContentUIDemoApplication.this, userID);
            }

        };
        task.execute();
    }

}