package com.bozidar.labas.microdroid.activities;

import android.graphics.PointF;
import android.util.Log;

import com.bozidar.labas.microdroid.R;
import com.bozidar.microdroid.base.MicroActivity;
import com.dlazaro66.qrcodereaderview.QRCodeReaderView;

import butterknife.Bind;

public class QRCodeActivity extends MicroActivity implements QRCodeReaderView.OnQRCodeReadListener {


    @Bind(R.id.qrdecoderview)
    QRCodeReaderView mydecoderview;

    @Override
    public int setupToolbar() {
        return 0;
    }

    @Override
    public int setupLayoutRes() {
        return R.layout.activity_qrcode;
    }

    @Override
    public int setupMenuRes() {
        return 0;
    }

    @Override
    public void init() {
        mydecoderview.setOnQRCodeReadListener(this);
    }

    @Override
    public void onQRCodeRead(String text, PointF[] points) {
        Log.d("read", text);
    }

    @Override
    public void cameraNotFound() {

    }

    @Override
    public void QRCodeNotFoundOnCamImage() {

    }
}
