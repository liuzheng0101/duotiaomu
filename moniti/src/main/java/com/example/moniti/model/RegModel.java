package com.example.moniti.model;

import com.example.moniti.api.UserApi;
import com.example.moniti.contract.OkhttpCallback;
import com.example.moniti.contract.RegCallback;
import com.example.moniti.contract.RegContract;
import com.example.moniti.net.OkhttpUtils;

import java.util.HashMap;

public class RegModel implements RegContract.IRegModel {
    @Override
    public void reg(HashMap<String, String> params, final RegCallback regCallback) {
        OkhttpUtils.getInstance().doPost(UserApi.REG_API, params, new OkhttpCallback() {
            @Override
            public void success(String result) {
                if (regCallback!=null){
                    regCallback.regsuccess(result);
                }
            }
            @Override
            public void failure(String error) {
                if (regCallback!=null){
                    regCallback.regfailure(error);
                }
            }
        });
    }
}
