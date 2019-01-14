package com.example.mature.yuekao.model;

import android.os.Handler;
import android.text.TextUtils;

import com.example.lib_core.net.OKhttpCallback;
import com.example.lib_core.net.OkhttpUtils;
import com.example.mature.yuekao.Api.UserApi;
import com.example.mature.yuekao.bean.Logininfo;
import com.example.mature.yuekao.contract.LoginContract;
import com.example.mature.yuekao.net.LoginCallback;
import com.google.gson.Gson;

import java.util.HashMap;

public class LoginModel implements LoginContract.ILoginModel {
    private Handler handler=new Handler();
    @Override
    public void getLogin(HashMap<String, String> params, final LoginCallback callback) {
        OkhttpUtils.getInstance().doPost(UserApi.LOGIN_API, params, new OKhttpCallback() {
            @Override
            public void failure(final String error) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.failure(error);
                    }
                });
            }
            @Override
            public void success(String result) {
                if (!TextUtils.isEmpty(result)){
                    paserResult(callback,result);
                }
            }
        });
    }
    private void paserResult(final LoginCallback callback, String result) {
        final Logininfo logininfo=new Gson().fromJson(result,Logininfo.class);
        handler.post(new Runnable() {
            @Override
            public void run() {
                callback.success(logininfo);
            }
        });
    }
}
