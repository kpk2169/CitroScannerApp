package com.sekim.citroscanner.Activty.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.sekim.citroscanner.R;
import com.sekim.citroscanner.Utils.PatternList;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail, etPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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