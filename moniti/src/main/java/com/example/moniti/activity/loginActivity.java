package com.example.moniti.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.moniti.MainActivity;
import com.example.moniti.R;
import com.example.moniti.bean.LoginInfo;
import com.example.moniti.contract.UserContract;
import com.example.moniti.presenter.LoginPresenter;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class loginActivity extends AppCompatActivity implements UserContract.ILoginView {

    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.password)
    EditText password;
    private LoginPresenter presenter;
    @BindView(R.id.QQlogin)
    Button QQlogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        presenter=new LoginPresenter(this);
        QQlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Build.VERSION.SDK_INT>=23){//QQ需要申请写入权限
                    String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.CALL_PHONE,Manifest.permission.READ_LOGS,Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.SET_DEBUG_APP,Manifest.permission.SYSTEM_ALERT_WINDOW,Manifest.permission.GET_ACCOUNTS,Manifest.permission.WRITE_APN_SETTINGS};
                    ActivityCompat.requestPermissions(loginActivity.this,mPermissionList,123);
                }else{
                    UMShareAPI.get(loginActivity.this).getPlatformInfo(loginActivity.this, SHARE_MEDIA.QQ, authListener);
                }
            }
        });
    }

    public void reg(View view) {
        startActivity(new Intent(loginActivity.this,RegActivity.class));
    }

    public void login(View view) {
        HashMap<String,String> params=new HashMap<>();
        String iphone=phone.getText().toString();
        String pass=password.getText().toString();
        params.put("mobile",iphone);
        params.put("password",pass);
        presenter.getlogin(params);
    }
    @Override
    public void loginsucess(LoginInfo info) {
        Toast.makeText(this,info.msg,Toast.LENGTH_SHORT).show();
        if ("登录成功".equals(info.msg)){
            startActivity(new Intent(loginActivity.this,MainActivity.class));
        }
    }
    @Override
    public void loginfailure(String result) {
        Toast.makeText(this,result,Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==123){
            UMShareAPI.get(this).getPlatformInfo(this, SHARE_MEDIA.QQ, authListener);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
    UMAuthListener authListener = new UMAuthListener() {
        /**
         * @desc 授权开始的回调
         * @param platform 平台名称
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @desc 授权成功的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param data 用户资料返回
         */
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            Toast.makeText(loginActivity.this, "成功了", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(loginActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }

        /**
         * @desc 授权失败的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {

            Toast.makeText(loginActivity.this, "失败：" + t.getMessage(),                                  Toast.LENGTH_LONG).show();
        }

        /**
         * @desc 授权取消的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         */
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(loginActivity.this, "取消了", Toast.LENGTH_LONG).show();
        }
    };
}
