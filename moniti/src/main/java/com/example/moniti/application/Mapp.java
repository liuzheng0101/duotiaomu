package com.example.moniti.application;

import android.app.Application;

import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

public class Mapp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
//        initzxing();
        initData();
    }
    private void initData() {
        UMConfigure.init(this,"5c089159b465f59767000066","小米",UMConfigure.DEVICE_TYPE_PHONE,"");
        UMConfigure.setLogEnabled(true);
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
    }
//    private void initzxing() {
//        ZXingLibrary.initDisplayOpinion(this);
//    }
}
