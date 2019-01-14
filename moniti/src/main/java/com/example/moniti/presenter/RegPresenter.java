package com.example.moniti.presenter;

import com.example.moniti.contract.RegCallback;
import com.example.moniti.contract.RegContract;
import com.example.moniti.model.RegModel;

import java.util.HashMap;

import butterknife.ButterKnife;

public class RegPresenter extends RegContract.getreg {
    private RegContract.IRegView view;
    private RegModel model;
    public RegPresenter(RegContract.IRegView view) {
        this.view = view;
        this.model=new RegModel();
    }
    @Override
    public void getreg(HashMap<String, String> parmas) {
        model.reg(parmas, new RegCallback() {
            @Override
            public void regsuccess(String result) {
                if (model!=null){
                    view.regsucess(result);
                }
            }
            @Override
            public void regfailure(String error) {
                view.regfailure(error);
            }
        });
    }
    public void destory(){
        if (view!=null){
            view=null;
        }
    }
}
