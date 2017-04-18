package com.ecode.ocjdemo;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by Administrator on 2017/4/14 0014.
 */

public class DemoApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
