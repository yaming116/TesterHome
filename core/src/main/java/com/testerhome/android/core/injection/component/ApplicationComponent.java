package com.testerhome.android.core.injection.component;

import android.app.Application;
import android.content.Context;

import com.testerhome.android.core.BaseApplication;
import com.testerhome.android.core.data.DataManager;
import com.testerhome.android.core.injection.ApplicationContext;
import com.testerhome.android.core.injection.module.ApplicationModule;
import com.testerhome.android.core.injection.module.DataModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Sun on 2016/9/14.
 */
@Singleton
@Component(modules = {ApplicationModule.class, DataModule.class})
public interface ApplicationComponent {

    void inject(BaseApplication application);

    @ApplicationContext
    Context getContext();

    Application getApplication();

    DataManager getDataManager();
}
