package com.testerhome.android.core.injection;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * object life scope
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivityScope {
}