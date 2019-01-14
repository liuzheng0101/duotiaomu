package com.example.lib_core.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity {
    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(bindLayoutId());
        unbinder=ButterKnife.bind(this);
        initView();
        initData();
    }

    protected abstract void initView();

    protected abstract void initData();

    protected abstract int bindLayoutId();
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder!=null){
            unbinder.unbind();
        }
    }
}
