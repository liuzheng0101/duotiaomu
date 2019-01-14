package com.example.lib_core.net;

import android.os.Handler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class OkhttpUtils {
    private Handler handler=new Handler();
    private OkHttpClient okHttpClient;
    private static OkhttpUtils instance;
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
    private OkhttpUtils(){
        HttpLoggingInterceptor loggingInterceptor=new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient=new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .readTimeout(5,TimeUnit.SECONDS)
                .connectTimeout(5,TimeUnit.SECONDS)
                .writeTimeout(5,TimeUnit.SECONDS)
                .build();
    }
    public void doPost(String url, HashMap<String,String> params, final OKhttpCallback callback){
        FormBody.Builder builder=new FormBody.Builder();
        for (Map.Entry<String,String> p:params.entrySet()){
            builder.add(p.getKey(),p.getValue());
        }
        Request request=new Request.Builder().url(url).post(builder.build()).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(final Call call, IOException e) {
                if (callback!=null){
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.failure("网络异常");
                        }
                    });
                }
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (callback!=null){
                    if (response.code()==200){
                        final String result=response.body().string();
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                callback.success(result);
                            }
                        });
                    }
                }
            }
        });
    }

    public void doGet(String url, HashMap<String,String> params, final OKhttpCallback callback){
        StringBuilder builder=new StringBuilder();
        for (Map.Entry<String,String> p:params.entrySet()){
            builder.append(p.getKey()).append("=").append(p.getValue()).append("&");
        }
        final Request request=new Request.Builder().url(url+"?"+builder.toString()).get().build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (callback!=null){
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.failure("网络异常");
                        }
                    });
                }
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result=request.body().toString();
                if (callback!=null){
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.success(result);
                        }
                    });
                }
            }
        });
    }
}
