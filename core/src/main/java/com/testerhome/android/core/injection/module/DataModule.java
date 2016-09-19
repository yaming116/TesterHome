package com.testerhome.android.core.injection.module;

import android.app.Application;
import android.support.annotation.StringDef;
import android.widget.Toast;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.testerhome.android.core.BuildConfig;
import com.testerhome.android.core.data.DataManager;
import com.testerhome.android.core.data.remote.TesterHomeService;

import java.io.File;
import java.io.IOException;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.jakewharton.byteunits.DecimalByteUnit.MEGABYTES;

/**
 * Created by Sun on 2016/9/14.
 */

@Module
public class DataModule {
    public static final String RETROFIT = "Retrofit";
    public static final String FRESCO = "Fresco";
    static final int DISK_CACHE_SIZE = (int) MEGABYTES.toBytes(50);

    @Provides
    @Singleton
    TesterHomeService provideTesterHomeService(Retrofit restAdapter){
        return restAdapter.create(TesterHomeService.class);
    }

    @Provides
    @Singleton
    @Named(RETROFIT)
    OkHttpClient provideOkHttpClient(Application app) {
        OkHttpClient.Builder builder = createOkHttpClient(app);
//        if (BuildConfig.DEBUG) {
            //add logging interceptor
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            //add stetho interceptor
        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                return chain.proceed(chain.request().newBuilder().addHeader("Authorization", "ddd").build());
            }
        });
        builder.addInterceptor(logging);
        builder.addInterceptor(new StethoInterceptor());
//        }
        return builder.build();
    }

    @Provides
    @Singleton
    @Named(FRESCO)
    OkHttpClient provideFrescoHttpClient(){
        return new OkHttpClient();
    }

    @Provides
    @Singleton
    public Retrofit provideRestAdapter(@Named(RETROFIT) OkHttpClient okHttpClient) {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.client(okHttpClient)
                .baseUrl(TesterHomeService.BASE_API)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create());
        return builder.build();
    }

    static OkHttpClient.Builder createOkHttpClient(Application app) {
        // Install an HTTP cache in the application cache directory.
        File cacheDir = new File(app.getCacheDir(), "http");
        Cache cache = new Cache(cacheDir, DISK_CACHE_SIZE);

        return new OkHttpClient.Builder()
                .cache(cache);
    }

}
