package com.sekim.citroscanner.Activty.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sekim.citroscanner.Activty.Home.HomeActivity;
import com.sekim.citroscanner.R;
import com.sekim.citroscanner.Retrofit.Login.LoginAPI;
import com.sekim.citroscanner.Retrofit.Login.LoginParams;
import com.sekim.citroscanner.Retrofit.Login.LoginResult;
import com.sekim.citroscanner.Retrofit.RetrofitClient;
import com.sekim.citroscanner.Utils.PatternList;
import com.sekim.citroscanner.Utils.PreferenceManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail, etPassword;
    private Button btnLogin;
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        retrofit = RetrofitClient.RetrofitClient();

        findViewById();

    }

    private void checkEmail(){
        try{
            if( etEmail != null ){
                String getEmail = etEmail.getText().toString();
                Pattern emailPattern = PatternList.getEmailPattern();

                Matcher matcher = emailPattern.matcher(getEmail);

                if( matcher.matches() ){
                    changeTextViewColor(  etEmail, R.color.citro_green );
                }else{
                    changeTextViewColor(  etEmail, R.color.red );
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void changeTextViewColor(TextView targetTv, int colorCode ){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                targetTv.setTextColor(colorCode);
            }
        });
    }

    private void findViewById(){
        try{
            etEmail = findViewById(R.id.et_email);

            etEmail.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    checkEmail();
                }
            });

            etPassword = findViewById(R.id.et_password);
            btnLogin = findViewById(R.id.btn_login);

            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String getId = "";
                    String getPw = "";
                    try{
                        getId = etEmail.getText().toString();
                        getPw = etPassword.getText().toString();

                        if( !getId.equals("") && !getPw.equals("")){
                            LoginAPI loginAPI = retrofit.create(LoginAPI.class);
                            Call<LoginResult> loginResultCall = loginAPI.login( new LoginParams( getId, getPw ));
                            loginResultCall.enqueue(new Callback<LoginResult>() {
                                @Override
                                public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {


                                    if( response.isSuccessful() ){
                                        LoginResult loginResult = response.body();

                                        if( loginResult.getStatus().equals("success") ){
                                            String userToken = loginResult.getLoginData().getAccessToken();
                                            PreferenceManager.setString( getApplicationContext(), PreferenceManager.USER_TOKEN ,userToken);
                                            Toast.makeText(getApplicationContext(), "로그인 성공", Toast.LENGTH_SHORT).show();

                                            startActivity( new Intent( getApplicationContext(), HomeActivity.class));
                                            finish();

                                        }else{
                                            Toast.makeText(getApplicationContext(), loginResult.getMessage(), Toast.LENGTH_SHORT).show();
                                        }

                                    }else{
                                        Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_SHORT).show();
                                    }

                                }

                                @Override
                                public void onFailure(Call<LoginResult> call, Throwable t) {
                                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }else{
                            Toast.makeText(getApplicationContext(), String.valueOf(R.string.warn_login ) , Toast.LENGTH_SHORT).show();
                        }

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });



        }catch (Exception e){
            e.printStackTrace();
        }
    }



}