package com.frost.themoviedb.network;

import android.content.Context;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

@Singleton
public class AuthInterceptor implements Interceptor {

//    private @ApplicationContext Context context;
//    private static final String HARDCODED_API_KEY = "fbe4e6280f6a460beaad8ebe2bc130ac";
//
//    @Inject
//    public AuthInterceptor(@ApplicationContext Context context) {
//        this.context = context;
//        this.prefManager = prefManager;
//    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
//        final String token = prefManager.getAccessToken();
//        if (!TextUtils.isEmpty(token)) {
//            builder.addHeader("Authorization-token", token);
//        }
//        if (!TextUtils.isEmpty(token)) {
//            builder.addHeader("Device-id", Utils.getDeviceId(context));
//        }
        return chain.proceed(builder.build());
    }
}