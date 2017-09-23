package com.frost.themoviedb.network;

import java.io.IOException;

import javax.inject.Singleton;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

@Singleton
public class AuthInterceptor implements Interceptor {

    private static final String HARDCODED_API_KEY = "fbe4e6280f6a460beaad8ebe2bc130ac";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();

        builder.addHeader("api_key", HARDCODED_API_KEY);
        return chain.proceed(builder.build());
    }
}