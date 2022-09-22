package com.sekim.citroscanner.Activty.Splash;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.sekim.citroscanner.Activty.Home.HomeActivity;
import com.sekim.citroscanner.Activty.Login.LoginActivity;
import com.sekim.citroscanner.Preference.PreferenceManager;
import com.sekim.citroscanner.R;

public class SplashActivity extends AppCompatActivity {

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


}