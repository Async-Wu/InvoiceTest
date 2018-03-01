package com.example.wall.invoicetest;

import android.app.Application;
import android.util.Log;

import com.example.wall.invoicetest.util.FrescoUtil;
import com.example.wall.invoicetest.util.OkhttpUtil;

import cn.jiguang.analytics.android.api.JAnalyticsInterface;
import cn.jpush.android.api.JPushInterface;

/**
 * Created by Wall on 2018/1/29.
 */
public class MyApplication extends Application {
    private String userName;
    @Override
    public void onCreate() {
        super.onCreate();
        OkhttpUtil.initOkHttp();
        FrescoUtil.initFresco(this);
        JPushInterface.setDebugMode(true);
        JAnalyticsInterface.setDebugMode(true);
        JPushInterface.init(this);
        JAnalyticsInterface.init(this);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
