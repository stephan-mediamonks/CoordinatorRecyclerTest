package com.mediamonks.testcoordinatorrecycler;

import android.app.Application;

import timber.log.Timber;

/**
 * Created on 07/03/2018.
 */

public class MyApp extends Application{
    @Override
    public void onCreate() {
        super.onCreate();

        Timber.plant(new Timber.DebugTree());
    }
}
