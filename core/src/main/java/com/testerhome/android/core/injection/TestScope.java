package com.testerhome.android.core.injection;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by Sun on 2016/9/14.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface TestScope {
}
