package com.example.mature.yuekao.presenter;

import com.example.mature.yuekao.bean.Logininfo;
import com.example.mature.yuekao.contract.LoginContract;
import com.example.mature.yuekao.model.LoginModel;
import com.example.mature.yuekao.net.LoginCallback;

import java.util.HashMap;

public class LoginPresenter extends LoginContract.LoginPresenter {

    @Override
    public void getLogin(HashMap<String, String> parmas) {
        model.getLogin(parmas, new LoginCallback() {
            @Override
            public void success(Logininfo logininfo) {
                view.success(logininfo);
            }
            @Override
            public void failure(String error) {
                view.failure(error);
            }
        });
    }
}
