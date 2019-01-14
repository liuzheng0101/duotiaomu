package com.example.moniti.contract;

import com.example.moniti.bean.LoginInfo;

public interface LoginCallback {
    void logginsuccess(LoginInfo loginInfo);
    void loginfailure(String error);
}
