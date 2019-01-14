package com.example.moniti.contract;

import com.example.moniti.bean.LoginInfo;

import java.util.HashMap;

public interface UserContract {
    public abstract class getlogin{
        public abstract void getlogin(HashMap<String,String> parmas);
    }
    public interface ILoginModel{
        void login(HashMap<String,String> params,LoginCallback loginCallback);
    }
    public interface ILoginView{
        void loginsucess(LoginInfo info);
        void loginfailure(String result);
    }
}
