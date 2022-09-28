package com.sekim.citroscanner.Activty.UserInfo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.sekim.citroscanner.R;
import com.sekim.citroscanner.Retrofit.RetrofitBuilder;
import com.sekim.citroscanner.Retrofit.User.GetUserInfoResult;
import com.sekim.citroscanner.Retrofit.User.UserAPI;
import com.sekim.citroscanner.Utils.PreferenceManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UserInfoActivity extends AppCompatActivity {

    private TextView tvName, tvPhone, tvEmail, tvMemo;
    private Button btnOpenModal;
    private Retrofit retrofit;
    private String userToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        findViewById();
        runGetUserInfo();

    }

    private void findViewById(){
        try{

            tvName = findViewById(R.id.tv_user_position);
            tvEmail = findViewById(R.id.tv_email);
            tvMemo = findViewById(R.id.tv_memo);
            tvPhone = findViewById(R.id.tv_phone);

            btnOpenModal = findViewById(R.id.btn_change_passwords);
            btnOpenModal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try{
                        // 커스텀 다이얼로그 비밀번호, 비밀번호 확인, 취소 , 변경경
                    }catch (Exception e){
                       e.printStackTrace();
                    }
                }
            });

            userToken = PreferenceManager.getString( getApplicationContext() , PreferenceManager.USER_TOKEN );


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void runGetUserInfo(){
        try {
            retrofit = RetrofitBuilder.RetrofitClient();
            UserAPI userAPI = retrofit.create( UserAPI.class );
            Call<GetUserInfoResult> resultCall = userAPI.getUserInfo(userToken);
            resultCall.enqueue(new Callback<GetUserInfoResult>() {
                @Override
                public void onResponse(Call<GetUserInfoResult> call, Response<GetUserInfoResult> response) {
                    if( response.isSuccessful()){
                        GetUserInfoResult userInfoResult = response.body();
                        if( userInfoResult.getStatus().equals("success") ) {
                            GetUserInfoResult.User.UserInfo userInfo = userInfoResult.getUser().getUserInfo();

                            String userName = userInfo.getName();
                            String mail = userInfo.getEmail();
                            String phone = userInfo.getPhone();
                            String memo = userInfo.getMemo();

                            setTextViewText( tvName, userName );
                            setTextViewText( tvEmail, mail );
                            setTextViewText( tvPhone, phone );
                            setTextViewText( tvMemo, memo );

                        }else{
                            Toast.makeText( getApplicationContext() , userInfoResult.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }else{
                        Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<GetUserInfoResult> call, Throwable t) {
                    Toast.makeText( getApplicationContext(), String.valueOf(R.string.api_err_msg), Toast.LENGTH_SHORT ).show();
                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void setTextViewText( TextView targetTextView, String text ){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try{
                    if( targetTextView != null ){
                        if( text != null && !text.equals("") ){
                            targetTextView.setText( text );
                        }else{
                            targetTextView.setText("");
                        }

                    }
                }catch (Exception e){

                }
            }
        });
    }

}