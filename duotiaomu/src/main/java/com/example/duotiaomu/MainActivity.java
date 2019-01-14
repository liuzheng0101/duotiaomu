package com.example.duotiaomu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.duotiaomu.adapter.UserAdapter;
import com.example.duotiaomu.bean.UserInfo;
import com.example.duotiaomu.contract.UserContract;
import com.example.duotiaomu.presenter.UserPresenter;
import com.google.gson.Gson;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements UserContract.IUserView {
    private RecyclerView rv;
    private ImageView iv;
    private EditText search;
    private UserAdapter userAdapter;
    private UserPresenter userPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv =findViewById(R.id.rv);
        iv =findViewById(R.id.iv);
        search =findViewById(R.id.search);
        initView();
    }
    private void initView() {
        HashMap<String,String> params=new HashMap<>();
        rv.setLayoutManager(new LinearLayoutManager(this));
        userPresenter=new UserPresenter(this);
        userAdapter=new UserAdapter(this);
        userPresenter.getUserList(params);
        rv.setAdapter(userAdapter);

    }

    @Override
    public void failure(String msg) {
        Toast.makeText(MainActivity.this,msg,Toast.LENGTH_SHORT).show();
    }
    @Override
    public void success(String result) {
        UserInfo userInfo=new Gson().fromJson(result,UserInfo.class);
        userAdapter.setProduct(userInfo.getData());
    }
}
