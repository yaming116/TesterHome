package com.testerhome.android;

import com.testerhome.android.core.injection.ActivityScope;
import com.testerhome.android.core.injection.component.ApplicationComponent;
import com.testerhome.android.inject.module.ActivityModule;

import dagger.Component;

/**
 * Created by Sun on 2016/9/14.
 */
@ActivityScope
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);
}
