package com.sekim.citroscanner.Retrofit.Logout;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

public class LogoutRepo {

    @SerializedName("message")
    private String message;

    @Nullable
    @SerializedName("status")
    private String status;

}
