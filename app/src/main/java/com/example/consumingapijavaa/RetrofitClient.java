package com.example.consumingapijavaa;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static RetrofitClient instance = null;
    private API myApi;

    RetrofitClient(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://dev.farizdotid.com/api/purwakarta/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        myApi = retrofit.create(API.class);
    }
    public static synchronized RetrofitClient getInstance(){
        if (instance==null){
            instance = new RetrofitClient();
        }
        return instance;
    }

    public API getMyApi(){
        return myApi;
    }
}
