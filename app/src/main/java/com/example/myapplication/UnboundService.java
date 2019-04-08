package com.example.myapplication;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import java.util.Random;

public class UnboundService extends Service {
    private String TAG = "UnboundService";
    private final IBinder binder = new LocalBinder();
    private final Random mRnumber = new Random();

    public void set_state(final int number){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences.Editor editor = getSharedPreferences("myPrefs",MODE_PRIVATE).edit();
                editor.putInt("number", number);
                editor.apply();
            }
        }, 5000);

    }

    public int get_state(){
        int stateNumber = 0;
        SharedPreferences prefs = getSharedPreferences("myPrefs", MODE_PRIVATE);
        stateNumber = prefs.getInt("number", 0);
        return stateNumber;
    }

    public class LocalBinder extends Binder{
        UnboundService getService(){
            return UnboundService.this;
        }
    }
    public UnboundService() {
        Log.i(TAG, "Constructor");
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        Log.i(TAG, "onBind");
        return binder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand");

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        Log.i(TAG, "onCreate");
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy");
        super.onDestroy();
    }

    public int getRandomNumber(){
        return mRnumber.nextInt(100);
    }
}