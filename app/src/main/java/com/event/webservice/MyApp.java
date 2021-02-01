package com.event.webservice;

import android.app.Application;

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        RestClient.getInst().setup();

    }

}
