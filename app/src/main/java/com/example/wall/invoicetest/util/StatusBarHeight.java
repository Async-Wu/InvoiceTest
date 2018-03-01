package com.example.wall.invoicetest.util;

import android.content.Context;
import android.content.res.Resources;


public class StatusBarHeight {
    private Context context;
    public StatusBarHeight(Context context){
        this.context = context;
    }
    //获取屏幕高度
    public int getStatusBarHeight() {
        int statusBarHeight = 0;
        Resources res = context.getResources();
        int resourceId = res.getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = res.getDimensionPixelSize(resourceId);
        }
        return statusBarHeight;
    }
}
