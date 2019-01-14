package com.example.moniti.contract;

public interface OkhttpCallback {
    void success(String result);
    void failure(String error);
}
