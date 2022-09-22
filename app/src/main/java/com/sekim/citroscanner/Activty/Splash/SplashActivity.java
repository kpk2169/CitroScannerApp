package com.sekim.citroscanner.Activty.Splash;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.sekim.citroscanner.Activty.Home.HomeActivity;
import com.sekim.citroscanner.Activty.Login.LoginActivity;
import com.sekim.citroscanner.Preference.PreferenceManager;
import com.sekim.citroscanner.R;

public class SplashActivity extends AppCompatActivity {

    private String[] permissions = {
            Manifest.permission.CAMERA
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        init();

    }

    private void init(){
        if(!PreferenceManager.getBoolean(this, PreferenceManager.IS_FIRST)){

            PreferenceManager.setBoolean(this, PreferenceManager.IS_FIRST, true);
            // 카메라 권한 요청
            ActivityCompat.requestPermissions(this, (String []) permissions, 1  );
        }else if( !PreferenceManager.getString( this, PreferenceManager.USER_TOKEN).equals("") ) {
            moveActivity(HomeActivity.class, 1000 );
        }else{
            moveActivity(LoginActivity.class, 1000);
        }
    }

    private void moveActivity(Class<?> activityClass, int time ){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity( new Intent( getApplicationContext(),activityClass ));
                finish();
            }
        }, time );
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        moveActivity(LoginActivity.class, 1000 );
    }
}