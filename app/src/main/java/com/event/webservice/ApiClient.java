package com.event.webservice;

import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiClient {

    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(APIConstant.ifsc_url)
                    .client(getRequestHeader())
                    .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                    .build();
        }
        return retrofit;
    }

    public static Retrofit getClientPincode() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(APIConstant.pincode_url)
                    .client(getRequestHeader())
                    .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                    .build();
        }
        return retrofit;
    }

    private static OkHttpClient getRequestHeader() {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(80, TimeUnit.SECONDS)
                .connectTimeout(80, TimeUnit.SECONDS)
                .build();
        return okHttpClient;
    }


}
