package com.example.mature.yuekao.bean;

import java.util.List;

public class Logininfo {
    public String msg;
    public String code;
    public List<login> data;
    public class login{
        public String mobile;
        public String uid;
    }
}
