package com.testerhome.android.core.injection.module;

import android.app.Application;
import android.content.Context;

import com.testerhome.android.core.data.DataManager;
import com.testerhome.android.core.injection.ApplicationContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Sun on 2016/9/14.
 */
@Module
public class ApplicationModule {
    protected final Application application;

    public ApplicationModule(Application application){
        this.application = application;
    }

    @Provides
    @Singleton
    Application provideApplication(){
        return application;
    }

    @Provides
    @Singleton
    @ApplicationContext
    Context provideContext(){
        return application;
    }
}
