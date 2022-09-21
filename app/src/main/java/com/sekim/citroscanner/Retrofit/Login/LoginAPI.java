package com.sekim.citroscanner.Retrofit.Login;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginAPI {

    @POST("/login")
    Call<LoginResult> login (
            @Body LoginData loginData
    );

}
