package com.example.hammad13060.okhttpexample;

import android.app.Application;

import com.example.hammad13060.okhttpexample.httpHelper.OkHTTPHelper;

/**
 * Created by hammad13060 on 27/04/16.
 */
public class GlobalApplication extends Application {
    private OkHTTPHelper okHTTPHelper = null;

    @Override
    public void onCreate() {
        super.onCreate();
        createOkHTTPHelperInstance();
    }

    public void createOkHTTPHelperInstance() {
        if (okHTTPHelper == null) {
            okHTTPHelper = OkHTTPHelper.getInstance();
        }
    }

    public OkHTTPHelper getOkHTTPHelper() {
        createOkHTTPHelperInstance();
        return okHTTPHelper;
    }
}
