package com.testerhome.android.inject.module;

import android.app.Activity;
import android.content.Context;

import com.testerhome.android.core.injection.ActivityContext;
import com.testerhome.android.core.injection.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Sun on 2016/9/14.
 */
@Module
public class ActivityModule {
    protected final Activity activity;

    public ActivityModule(Activity activity){
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    Activity provideActivity(){
        return activity;
    }

    @Provides
    @ActivityContext
    Context provideContext(){
        return activity;
    }
}
