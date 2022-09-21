package com.sekim.citroscanner.Retrofit.Login;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

public class LoginRepo {

    @SerializedName("status")
    private String status;

    @SerializedName("message")
    private String message;

    @Nullable
    @SerializedName("data")
    LoginData loginData;

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    @Nullable
    public LoginData getLoginData() {
        return loginData;
    }

    private class LoginData {

        @SerializedName("access_token_name")
        private String access_token_name;

        @SerializedName("access_token")
        private String access_token;

        @SerializedName("expires_in")
        private int expires_in;

        public String getAccessTokenName() {
            return access_token_name;
        }

        public String getAccessToken() {
            return access_token;
        }

        public int getExpiresIn() {
            return expires_in;
        }
    }

}
