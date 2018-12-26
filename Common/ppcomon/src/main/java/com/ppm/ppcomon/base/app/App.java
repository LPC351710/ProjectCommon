package com.ppm.ppcomon.base.app;

import android.app.Application;
import android.content.Context;

/**
 * @author by lpc on 2018/1/12.
 */
public class App extends Application {

    public static final boolean IS_TEST = true;
    public static final boolean IS_DEBUG = false;

    private static App mContext;


    public static Context getContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        saveMerchantInfo();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    protected String getAppId() {
        return "";
    }

    protected String getSecret() {
        return "";
    }

    protected void saveMerchantInfo() {
    }

}
