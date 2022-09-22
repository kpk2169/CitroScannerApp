package com.sekim.citroscanner.Retrofit.User;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

public class GetUserInfoResult {

    @SerializedName("status")
    private String status;

    @SerializedName("message")
    private String message;

    @Nullable
    @SerializedName("data")
    private User user;

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public User getUser() {
        return user;
    }

    public class User {
        @SerializedName("user")
        private UserInfo userInfo;

        public UserInfo getUserInfo() {
            return userInfo;
        }

        public class UserInfo {
            @SerializedName("id")
            private int id;

            @SerializedName("name")
            private String name;

            @SerializedName("email")
            private String email;

            @Nullable
            @SerializedName("email_verified_at")
            private String emailVerifiedAt;

            @SerializedName("required_email_verify")
            private Boolean requiredEmailVerify;

            @Nullable
            @SerializedName("phone")
            private String phone;

            @SerializedName("active")
            private Boolean active;

            @Nullable
            @SerializedName("memo")
            private String memo;

            public int getId() {
                return id;
            }

            public String getName() {
                return name;
            }

            public String getEmail() {
                return email;
            }

            public String getEmailVerifiedAt() {
                return emailVerifiedAt;
            }

            public Boolean getRequiredEmailVerify() {
                return requiredEmailVerify;
            }

            public String getPhone() {
                return phone;
            }

            public Boolean getActive() {
                return active;
            }

            public String getMemo() {
                return memo;
            }
        }
    }


}
