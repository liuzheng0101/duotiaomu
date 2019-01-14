package com.example.moniti.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.moniti.R;

import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zxing.ZXingView;

public class Scan2Activity extends AppCompatActivity implements QRCodeView.Delegate{

    private ZXingView zXingView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan2);
        zXingView = findViewById(R.id.zxing);
        zXingView.setDelegate(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        zXingView.startCamera();
        zXingView.startSpotAndShowRect();
        zXingView.openFlashlight();
    }

    @Override
    protected void onStop() {
        super.onStop();
        zXingView.stopCamera();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        zXingView.onDestroy();
    }

    @Override
    public void onScanQRCodeSuccess(String result) {

    }

    @Override
    public void onCameraAmbientBrightnessChanged(boolean isDark) {

    }

    @Override
    public void onScanQRCodeOpenCameraError() {

    }
}
