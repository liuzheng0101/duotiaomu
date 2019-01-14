package com.example.mature.yuekao.contract;

import com.example.lib_core.base.mvp.BasePresenter;
import com.example.lib_core.base.mvp.IBaseModel;
import com.example.lib_core.base.mvp.IBaseView;
import com.example.mature.yuekao.bean.Logininfo;
import com.example.mature.yuekao.model.LoginModel;
import com.example.mature.yuekao.net.LoginCallback;

import java.util.HashMap;
import java.util.List;

public interface LoginContract {
    abstract class LoginPresenter extends BasePresenter<ILoginModel,ILoginView>{
        @Override
        public ILoginModel getModel() {
            return new LoginModel();
        }
        public abstract void getLogin(HashMap<String,String> parmas);
    }

    interface ILoginModel extends IBaseModel{
        void getLogin(HashMap<String,String> params, LoginCallback callback);
    }

    interface ILoginView extends IBaseView {
        void success(Logininfo info);
        void failure(String error);
    }
}
