package com.frost.themoviedb.di.module;

import android.content.Context;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.frost.themoviedb.common.Constants;
import com.frost.themoviedb.di.qualifiers.ApplicationContext;
import com.frost.themoviedb.di.scopes.ApplicationScope;
import com.frost.themoviedb.manager.ApiManager;
import com.frost.themoviedb.network.ApiService;
import com.frost.themoviedb.network.AuthInterceptor;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.File;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {

    private static final int DISK_CACHE_SIZE = 10 * 1024 * 1024;

    @Provides
    @ApplicationScope
    Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .baseUrl(Constants.BASE_URL)
                .build();
    }

    @Provides
    @ApplicationScope
    ApiService provideApiService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }

    @Provides
    @ApplicationScope
    OkHttpClient provideOkHttpClient(@ApplicationContext Context context) {
//    OkHttpClient provideOkHttpClient(Context context) {
        return new OkHttpClient.Builder()
                .cache(new Cache(new File(context.getCacheDir(), "http"), DISK_CACHE_SIZE))
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .addNetworkInterceptor(new StethoInterceptor())
                .addInterceptor(new AuthInterceptor())
                .build();
    }

    @Provides
    ApiManager provideApiManager(ApiService apiService) {
        return new ApiManager(apiService);
    }
}
