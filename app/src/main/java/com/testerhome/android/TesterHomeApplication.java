package com.testerhome.android;

import android.app.Application;
import android.os.Build;
import android.webkit.WebView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.backends.okhttp3.OkHttpImagePipelineConfigFactory;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.testerhome.android.core.BaseApplication;

import javax.inject.Inject;

import okhttp3.OkHttpClient;

/**
 * Created by Sun on 2016/9/7.
 */
public class TesterHomeApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        fresco();
    }

    private void fresco(){
        // initialize fresco with OK HTTP
        ImagePipelineConfig config = OkHttpImagePipelineConfigFactory
                .newBuilder(this, okHttpClient)
                .build();
        Fresco.initialize(this, config);

        if (BuildConfig.DEBUG && Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
    }
}
