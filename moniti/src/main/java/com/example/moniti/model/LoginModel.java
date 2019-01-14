package com.example.moniti.model;

import android.os.Handler;
import android.text.TextUtils;

import com.example.moniti.api.UserApi;
import com.example.moniti.bean.LoginInfo;
import com.example.moniti.contract.LoginCallback;
import com.example.moniti.contract.OkhttpCallback;
import com.example.moniti.contract.UserContract;
import com.example.moniti.net.OkhttpUtils;
import com.google.gson.Gson;

import java.util.HashMap;

public class LoginModel implements UserContract.ILoginModel {
    private Handler handler=new Handler();
    @Override
    public void login(HashMap<String, String> params, final LoginCallback loginCallback) {
        OkhttpUtils.getInstance().doPost(UserApi.LOGIN_API, params, new OkhttpCallback() {
            @Override
            public void success(String result) {
                if (!TextUtils.isEmpty(result)){
                    paserReusult(loginCallback,result);
                }
            }
            @Override
            public void failure(final String error) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        loginCallback.loginfailure(error);
                    }
                });
            }
        });
    }
    private void paserReusult(final LoginCallback loginCallback, String result) {
        final LoginInfo info=new Gson().fromJson(result,LoginInfo.class);
        handler.post(new Runnable() {
            @Override
            public void run() {
                loginCallback.logginsuccess(info);
            }
        });
    }
}
