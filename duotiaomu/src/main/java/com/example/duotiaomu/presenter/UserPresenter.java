package com.example.duotiaomu.presenter;

import com.example.duotiaomu.contract.UserContract;
import com.example.duotiaomu.model.UserModel;

import java.util.HashMap;

public class UserPresenter extends UserContract.IUserPresenter {
    private UserContract.IUserView view;
    private UserModel model;
    public UserPresenter(UserContract.IUserView view) {
        this.view = view;
        this.model=new UserModel();
    }
    @Override
    public void getUserList(HashMap<String, String> params) {
        model.getUserList(params, new UserModel.ModelCallback() {
            @Override
            public void failure(String msg) {
                if (view!=null){
                    view.failure(msg);
                }
            }
            @Override
            public void success(String result) {
                if (view!=null){
                    view.success(result);
                }
            }
        });
    }
}
