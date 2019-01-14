package com.example.lib_core.net;

public interface OKhttpCallback {
    void failure(String error);
    void success(String result);
}
