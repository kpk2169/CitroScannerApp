package com.sekim.citroscanner.Retrofit.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;

public interface UserAPI {

    @GET("/users/me")
    Call<GetUserInfoResult> getUserInfo( @Header("Authorization") String userToken );

    @PATCH("/users/me")
    Call<GetUserInfoResult> changePassword(@Header("Authorization") String userToken, @Body PasswordParams passwordParams );

}
