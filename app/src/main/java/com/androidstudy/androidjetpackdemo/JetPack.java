package com.androidstudy.androidjetpackdemo;

import android.app.Application;

import timber.log.Timber;

public class JetPack extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        //Plant a Timber Debug Tree here
        Timber.plant(new Timber.DebugTree());
    }
}
