package com.sekim.citroscanner.Retrofit.Logout;

import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface LogoutAPI {

    @POST("/logout")
    Call<LogoutRepo> logout (@Header("Authorization") String authToken);

}
