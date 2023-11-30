package com.example.moviesrx;

import android.app.Application;

import com.example.moviesrx.network.AdvancedApiClient;

import retrofit2.Retrofit;
import com.example.moviesrx.network.MovieApiInterface;

public class RetroApp extends Application {
    public MovieApiInterface service;

    private static RetroApp instance;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
        initRetrofit();
    }

    public static RetroApp getInstance() {
        return instance;
    }

    private void initRetrofit() {

        Retrofit retrofit = AdvancedApiClient.getClient();

        service = retrofit.create(MovieApiInterface.class);
    }
}



