package com.example.administrator.lenglian;

import android.app.Application;
import android.content.Context;

/**
 * Created by Administrator on 2017/8/24.
 */

public class MyApplication extends Application {
    private static Application instance;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static Application getInstance() {
        return instance;
    }

    public static Context getGloableContext() {
        return instance.getApplicationContext();
    }
}
