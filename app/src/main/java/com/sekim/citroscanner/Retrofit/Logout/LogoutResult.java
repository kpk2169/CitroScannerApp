package com.sekim.citroscanner.Retrofit.Logout;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

public class LogoutResult {

    @SerializedName("message")
    private String message;

    @Nullable
    @SerializedName("status")
    private String status;

}
