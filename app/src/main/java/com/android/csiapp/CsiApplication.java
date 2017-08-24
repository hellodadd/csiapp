package com.android.csiapp;

import android.app.Application;

/**
 * Created by liwei on 2017/4/5.
 */

public class CsiApplication extends Application {
    private boolean isReporting=false;
    public boolean getIsReporting(){
        return isReporting;
    }
    public void setIsReporting(boolean reporting){
        isReporting=reporting;
    }
    public void onCreate(){
        super.onCreate();
    }
}
