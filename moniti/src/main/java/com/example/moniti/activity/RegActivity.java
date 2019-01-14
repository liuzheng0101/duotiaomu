package com.example.moniti.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.moniti.R;
import com.example.moniti.contract.RegContract;
import com.example.moniti.presenter.RegPresenter;

import java.util.HashMap;

public class RegActivity extends AppCompatActivity implements RegContract.IRegView {

    private EditText password;
    private EditText phone;
    private RegPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        phone=findViewById(R.id.phone);
        password=findViewById(R.id.password);
        initData();
    }

    private void initData() {
        presenter=new RegPresenter(this);
    }

    public void login(View view) {
        startActivity(new Intent(RegActivity.this,loginActivity.class));
    }
    @Override
    public void regsucess(String result) {
        Toast.makeText(this,result,Toast.LENGTH_SHORT).show();
    }
    @Override
    public void regfailure(String result) {
        Toast.makeText(this,result,Toast.LENGTH_SHORT).show();
    }
    public void reg(View view) {
        HashMap<String,String> params=new HashMap<>();
        String iphone=phone.getText().toString();
        String pass=password.getText().toString();
        params.put("mobile",iphone);
        params.put("password",pass);
        presenter.getreg(params);
    }
}
