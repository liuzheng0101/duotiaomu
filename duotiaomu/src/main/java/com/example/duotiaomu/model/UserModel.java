package com.example.duotiaomu.model;

import com.example.duotiaomu.api.UserApi;
import com.example.duotiaomu.contract.OkhttpCallback;
import com.example.duotiaomu.contract.UserContract;
import com.example.duotiaomu.net.OkhttpUtils;

import java.util.HashMap;

public class UserModel implements UserContract.IUserModel {

    @Override
    public void getUserList(HashMap<String, String> params, final ModelCallback callback) {
        OkhttpUtils.getInstance().doPost(UserApi.DATA_API, params, new OkhttpCallback() {
            @Override
            public void success(String result) {
                if (callback!=null){
                    callback.success(result);
                }
            }

            @Override
            public void failure(String error) {
                if (callback!=null){
                    callback.failure(error);
                }
            }
        });
    }
    public interface ModelCallback{
        void failure(String msg);
        void success(String result);
    }
}
