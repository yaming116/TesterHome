package com.testerhome.android.core.ui.base;

/**
 * Created by Sun on 2016/9/13.
 *
 * base presenter,
 */
public interface Presenter<V extends MvpView> {

    void attachView(V mvpView);

    void detachView();
}
