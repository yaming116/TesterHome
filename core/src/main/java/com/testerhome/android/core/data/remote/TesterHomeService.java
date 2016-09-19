package com.testerhome.android.core.data.remote;

import com.testerhome.android.auth.AuthConstants;
import com.testerhome.android.core.data.model.OAuth;
import com.testerhome.android.core.data.response.HelloUserResponse;

import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Sun on 2016/9/13.
 */
public interface TesterHomeService {
    public static final String BASE_API = "https://testerhome.com/api/v3/";
    public static final String WIKI_URL = "https://testerhome.com/wiki";


    @GET("hello.json")
    public Observable<HelloUserResponse> getHello(@Query("access_token") String accessToken);

    @POST(AuthConstants.ACCESS_TOKEN_URL)
    @FormUrlEncoded
    public Observable<OAuth> oAuth(@Field("client_id") String clientId, @Field("code") String code,
                                   @Field("grant_type") String grantType, @Field("redirect_uri") String redirectUri,
                                   @Field("client_secret") String clientSecret);

    @POST(AuthConstants.ACCESS_TOKEN_URL)
    @FormUrlEncoded
    public Observable<OAuth> refreshOAuth(@Field("client_id") String clientId,
                                            @Field("refresh_token") String refreshToken,
                                            @Field("grant_type") String grantType,
                                            @Field("client_secret") String clientSecret);
}
