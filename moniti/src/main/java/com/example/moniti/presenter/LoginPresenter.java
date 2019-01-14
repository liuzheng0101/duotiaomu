package com.example.moniti.presenter;

import com.example.moniti.bean.LoginInfo;
import com.example.moniti.contract.LoginCallback;
import com.example.moniti.contract.UserContract;
import com.example.moniti.model.LoginModel;

import java.util.HashMap;

public class LoginPresenter extends UserContract.getlogin {
    private UserContract.ILoginView view;
    private LoginModel model;
    public LoginPresenter(UserContract.ILoginView view) {
        this.view = view;
        this.model=new LoginModel();
    }
    @Override
    public void getlogin(HashMap<String, String> parmas) {
        model.login(parmas, new LoginCallback() {
            @Override
            public void logginsuccess(LoginInfo loginInfo) {
                view.loginsucess(loginInfo);
            }
            @Override
            public void loginfailure(String error) {
                view.loginfailure(error);
            }
        });
    }
}
