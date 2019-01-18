package com.example.cart1.bean;

import java.util.List;

public class UserInfo {
    public String code;
    public String msg;
    public List<Cart> data;
    public class Cart{

        public boolean isChecked;  //一级

        public String sellerName;
        public String sellerid;
        public List<Product> list;
        public class Product{

            public boolean isCheckedtwo;  //二级

            public String title;
            public String images;
            public double price;
            public String pid;

            public int pos;            //替换位置id
            public int productNum=1;   //默认商品数量
        }
    }
}
