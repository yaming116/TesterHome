package com.testerhome.android.core;

import android.app.Application;
import android.content.Context;

import com.testerhome.android.core.injection.TestScope;
import com.testerhome.android.core.injection.component.ApplicationComponent;
import com.testerhome.android.core.injection.component.DaggerApplicationComponent;
import com.testerhome.android.core.injection.module.ApplicationModule;
import com.testerhome.android.core.injection.module.DataModule;

import javax.inject.Inject;
import javax.inject.Named;

import okhttp3.OkHttpClient;

/**
 * Created by Sun on 2016/9/14.
 */
public class BaseApplication extends Application {

    ApplicationComponent mApplicationComponent;

    @Inject
    @Named(DataModule.FRESCO)
    protected OkHttpClient okHttpClient;

    @Override
    public void onCreate() {
        super.onCreate();
        initDagger();

    }

    private void initDagger(){
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .dataModule(new DataModule())
                .build();
        mApplicationComponent.inject(this);
    }


    public static BaseApplication get(Context context) {
        return (BaseApplication) context.getApplicationContext();
    }


    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }

    @TestScope
    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }
}



