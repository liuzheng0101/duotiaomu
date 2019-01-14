package com.example.moniti.contract;

import com.example.moniti.bean.LoginInfo;

import java.util.HashMap;

public interface RegContract {
    public abstract class getreg{
        public abstract void getreg(HashMap<String,String> parmas);
    }
    public interface IRegModel{
        void reg(HashMap<String,String> params,RegCallback regCallback);
    }
    public interface IRegView{
        void regsucess(String result);
        void regfailure(String result);
    }
}
