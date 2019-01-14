package com.example.mature.yuekao.net;

import com.example.mature.yuekao.bean.Logininfo;

public interface LoginCallback {
    void success(Logininfo logininfo);
    void failure(String error);
}
