package com.example.hotumit.tommvpstructure;

import android.app.Application;

import com.example.hotumit.tommvpstructure.utils.Contextor;
import com.facebook.appevents.AppEventsLogger;


/**
 * Created by HOTUM IT on 5/2/2561.
 */

public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Contextor.getInstance().init(getApplicationContext());
     /*   AppEventsLogger.activateApp(MainApplication.this);*/
    }
}
