package com.example.androiddevelopment_kennypassenier.models;

import android.app.IntentService;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class FetchImageService extends IntentService {
    private static final String TAG = "FetchImageService";
    public static final String NOTIFY_IMAGE ="com.example.androiddevelopment_kennypassenier.models.FetchImageService.total";

    private final Intent mImageIntent;

    // Todo use class
    public FetchImageService()
    {
        super(TAG);
        // Prepareer intents die gebruikt worden bij broadcast -> momenteel maar 1
        mImageIntent = new Intent();
        mImageIntent.setAction(NOTIFY_IMAGE);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if(intent != null){
            // Todo get image data and store it in a bitmap
            String urlString = intent.getStringExtra("posterUrl");
            Bitmap posterImage;
            try {
                URL url = new URL(urlString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                posterImage = BitmapFactory.decodeStream(input);
                mImageIntent.putExtra("posterImage", posterImage);
                LocalBroadcastManager.getInstance(this).sendBroadcast(mImageIntent);
            } catch (IOException e) {
                Log.d(TAG, "onHandleIntent: IOException");
                e.printStackTrace();
            }
        }
    }
}
