package com.sekim.citroscanner.Retrofit;

import com.sekim.citroscanner.R;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    public Retrofit RetrofitClient(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(String.valueOf(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

}
