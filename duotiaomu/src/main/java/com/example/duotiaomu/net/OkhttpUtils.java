package com.example.duotiaomu.net;

import android.os.Handler;

import com.example.duotiaomu.contract.OkhttpCallback;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class OkhttpUtils {
    private static OkhttpUtils instance;
    private Handler handler=new Handler();
    private OkHttpClient okHttpClient;

    public static OkhttpUtils getInstance() {
        if (instance==null){
            synchronized (OkhttpUtils.class){
                if (instance==null){
                    instance=new OkhttpUtils();
                }
            }
        }
        return instance;
    }

    public OkhttpUtils(){
        HttpLoggingInterceptor loggingInterceptor=new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient=new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .writeTimeout(5,TimeUnit.SECONDS)
                .readTimeout(5,TimeUnit.SECONDS)
                .connectTimeout(5,TimeUnit.SECONDS)
                .build();
    }
    public void doPost(String url,HashMap<String,String> params, final OkhttpCallback okhttpCallback){
        final FormBody.Builder builder=new FormBody.Builder();
        for (Map.Entry<String,String> p:params.entrySet()){
            builder.add(p.getKey(),p.getValue());
        }
        final Request request=new Request.Builder().url(url).post(builder.build()).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (okhttpCallback!=null){
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            okhttpCallback.failure("w网络异常");
                        }
                    });
                }
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(okhttpCallback!=null){
                    if (200==response.code()){
                        final String result=response.body().string();
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                okhttpCallback.success(result);
                            }
                        });
                    }
                }
            }
        });
    }
    public void doGet(String url,HashMap<String,String> params,final OkhttpCallback okhttpCallback){
        StringBuilder builder=new StringBuilder();
        if (params!=null&&params.size()>0){
            for (Map.Entry<String,String> p:params.entrySet()){
                builder.append(p.getKey()).append("=").append(p.getValue()).append("&");
            }
        }
        Request request=new Request.Builder().url(url+"?"+ builder.toString()).get().build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (okhttpCallback != null) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            okhttpCallback.failure("网络异常");
                        }
                    });
                }
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                if (okhttpCallback != null) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            okhttpCallback.success(result);
                        }
                    });
                }
            }
        });
    }
}
