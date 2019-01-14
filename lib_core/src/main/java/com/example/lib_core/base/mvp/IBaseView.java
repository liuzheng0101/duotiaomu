package com.example.lib_core.base.mvp;

public interface IBaseView {
    BasePresenter initPresenter();
    void showloading();
    void hideloading();
    void failure(String msg);
}
