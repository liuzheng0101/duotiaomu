package com.example.duotiaomu.contract;

import com.example.duotiaomu.model.UserModel;

import java.util.HashMap;

public interface UserContract {
    public abstract class IUserPresenter{
        public abstract void getUserList(HashMap<String,String> params);
    }

    public interface IUserModel{
        void getUserList(HashMap<String,String> params, UserModel.ModelCallback callback);
    }
    public interface IUserView{
        void failure(String msg);
        void success(String result);
    }
}
