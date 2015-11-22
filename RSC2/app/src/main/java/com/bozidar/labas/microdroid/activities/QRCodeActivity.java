package com.bozidar.labas.microdroid.activities;

import android.content.Intent;
import android.graphics.PointF;
import android.util.Log;

import com.bozidar.labas.microdroid.R;
import com.bozidar.labas.microdroid.mvp.model.response.Response;
import com.bozidar.labas.microdroid.network.RequestAPI;
import com.bozidar.labas.microdroid.utils.Constants;
import com.bozidar.labas.microdroid.utils.SharedPrefs;
import com.bozidar.labas.microdroid.utils.TokenManager;
import com.bozidar.microdroid.base.MicroActivity;
import com.bozidar.microdroid.model.User;
import com.bozidar.microdroid.network.ServiceFactory;
import com.dlazaro66.qrcodereaderview.QRCodeReaderView;

import butterknife.Bind;
import retrofit.Callback;
import retrofit.RetrofitError;

public class QRCodeActivity extends MicroActivity implements QRCodeReaderView.OnQRCodeReadListener, Callback<Response<String>> {


    @Bind(R.id.qrdecoderview)
    QRCodeReaderView mydecoderview;

    private boolean scanned = false;

    User user;

    SharedPrefs prefs = SharedPrefs.getInstance();

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
        user = prefs.loadObject(getResources().getString(R.string.user_data), this);
        mydecoderview.setOnQRCodeReadListener(this);

    }

    @Override
    public void onQRCodeRead(String text, PointF[] points) {
        if(!scanned){
            flagCaptured();
            startActivity(new Intent(this, MainActivity.class));
        }
        scanned = true;


        Log.d("read", text);
    }

    private void flagCaptured(){
        RequestAPI api = ServiceFactory.createRetrofitService(RequestAPI.class, Constants.ENDPOINT);
        String tokenFormat = TokenManager.formatToken(user.getToken());
        api.flagCaptured(tokenFormat, "test", this);
    }

    @Override
    public void cameraNotFound() {

    }

    @Override
    public void QRCodeNotFoundOnCamImage() {

    }

    @Override
    public void success(Response<String> stringResponse, retrofit.client.Response response) {
        Log.d("success", "success");
    }

    @Override
    public void failure(RetrofitError error) {
        Log.d("error", "error");
    }
}
