package com.example.mature.yuekao.activity;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lib_core.base.mvp.BaseMvpActivity;
import com.example.lib_core.base.mvp.BasePresenter;
import com.example.mature.yuekao.MainActivity;
import com.example.mature.yuekao.R;
import com.example.mature.yuekao.bean.Logininfo;
import com.example.mature.yuekao.contract.LoginContract;
import com.example.mature.yuekao.presenter.LoginPresenter;

import java.util.HashMap;

public class LoginActivity extends BaseMvpActivity<LoginContract.ILoginModel,LoginContract.LoginPresenter> implements LoginContract.ILoginView {
    EditText phone;
    EditText password;
    @Override
    protected void initData() {
        super.initData();
    }
    @Override
    protected void initView(){
        phone=findViewById(R.id.phone);
        password=findViewById(R.id.password);
    }
    @Override
    protected int bindLayoutId() {
        return R.layout.activity_login;
    }
    @Override
    public void success(Logininfo info) {
        Toast.makeText(LoginActivity.this,info.msg+"",Toast.LENGTH_SHORT).show();
        if("登录成功".equals(info.msg)){
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
        }
    }
    @Override
    public BasePresenter initPresenter() {
        return new LoginPresenter();
    }
    @Override
    public void showloading() {
    }
    @Override
    public void hideloading() {
    }
    @Override
    public void failure(String msg) {
        Toast.makeText(LoginActivity.this,msg,Toast.LENGTH_SHORT).show();
    }
    public void login(View view) {
        String iphone=phone.getText().toString();
        String pass=password.getText().toString();
        HashMap<String,String> params=new HashMap<>();
        params.put("phone",iphone);
        params.put("pwd",pass);
        presenter.getLogin(params);
    }
    public void reg(View view) {

    }
}
