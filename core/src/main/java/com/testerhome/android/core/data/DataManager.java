package com.testerhome.android.core.data;

import com.testerhome.android.auth.AuthConstants;
import com.testerhome.android.core.data.model.HelloUser;
import com.testerhome.android.core.data.model.OAuth;
import com.testerhome.android.core.data.remote.TesterHomeService;
import com.testerhome.android.core.data.response.HelloUserResponse;

import javax.annotation.Signed;
import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Retrofit;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Sun on 2016/9/13.
 */
@Singleton
public class DataManager {

    private final TesterHomeService testerHomeService;

    @Inject
    public DataManager(TesterHomeService testerHomeService){
        this.testerHomeService = testerHomeService;
    }

    public Observable<HelloUser> getHelloUser(String token){
        return testerHomeService.getHello(token)
                .map(new Func1<HelloUserResponse, HelloUser>(){
                    @Override
                    public HelloUser call(HelloUserResponse helloUserResponse) {
                        return helloUserResponse.getUser();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<OAuth> getOAuth(String code){
        return testerHomeService.oAuth(AuthConstants.CLIENT_ID, code,
                "authorization_code", AuthConstants.CALL_BACK_URL, AuthConstants.SECRET)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<OAuth> refreshOAuth(String refreshToken){
        return testerHomeService.refreshOAuth(AuthConstants.CLIENT_ID, refreshToken,
                "refresh_token", AuthConstants.SECRET)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
