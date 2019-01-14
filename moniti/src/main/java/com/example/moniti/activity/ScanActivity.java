package com.example.moniti.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.moniti.MainActivity;
import com.example.moniti.R;

import java.lang.ref.WeakReference;

import cn.bingoogolapple.qrcode.zxing.QRCodeEncoder;

public class ScanActivity extends AppCompatActivity {

    private ImageView mImageView;
    private EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        initView();
    }

    private void initView() {
        mImageView = findViewById(R.id.image_qrcode);
        mEditText = findViewById(R.id.edit);
        findViewById(R.id.saomiao).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermission();
            }
        });

        findViewById(R.id.shengcheng).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createQRCode();
            }
        });
    }

    private void checkPermission() {
        //第一步，判断系统版本是否为6.0以上
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            //第二步：checkSelfPermission判断有没有此权限
            //第一个参数：上下文
            //第二个参数：我们想要判断的权限，此处为相机权限
            //PackageManager.PERMISSION_GRANTED 条件，权限有没有被授予
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                //如果没授予，则申请权限
                //第一个：上下文
                //第二个：要申请的权限数组，此处为相机权限
                //第三个：请求码，startActivityForResult一样
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},100);
            }
        }else{
            startActivity(new Intent(ScanActivity.this, Scan2Activity.class));
        }
    }

    /**
     * 接受权限请求结果
     * @param requestCode 请求码
     * @param permissions 我们请求的权限数组
     * @param grantResults 返回结果数组
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //如果requestCode匹配，切权限申请通过
        if(requestCode == 100 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            startActivity(new Intent(ScanActivity.this, Scan2Activity.class));
        }
    }

    private void createQRCode() {
        QRTask qrTask = new QRTask(this, mImageView, mEditText);
        qrTask.execute(mEditText.getText().toString());
    }

    static class QRTask extends AsyncTask<String, Void, Bitmap> {
        private WeakReference<Context> mContext;
        private WeakReference<ImageView> mImageView;

        public QRTask(Context context, ImageView image, EditText ed) {
            mContext = new WeakReference<>(context);
            mImageView = new WeakReference<>(image);
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            String str = params[0];
            if (TextUtils.isEmpty(str)) {
                return null;
            }
//            int size = mContext.get().getResources().getDimensionPixelOffset(R.dimen.qr_code_size);
            return QRCodeEncoder.syncEncodeQRCode(str, 200);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if (bitmap != null) {
                mImageView.get().setImageBitmap(bitmap);
            } else {
                Toast.makeText(mContext.get(), "生成失败", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
