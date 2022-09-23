package com.sekim.citroscanner.Activty.Settings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import com.sekim.citroscanner.Activty.Login.LoginActivity;
import com.sekim.citroscanner.Activty.UserInfo.UserInfoActivity;
import com.sekim.citroscanner.R;
import com.sekim.citroscanner.Utils.GetAppInfo;
import com.sekim.citroscanner.Utils.PermissionCheck;
import com.sekim.citroscanner.Utils.PhoneCall;
import com.sekim.citroscanner.Utils.PreferenceManager;

public class SettingsActivity extends AppCompatActivity {

    private ConstraintLayout clCamera, clVersion, clCs;
    private Button btnMoveInfoManager, btnLogout;
    private TextView tvVersionName;
    private Switch swCameraPermission;
    private boolean cameraPermission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        findViewById();

    }

    @Override
    protected void onResume() {
        super.onResume();
        cameraPermission = PermissionCheck.check( getApplicationContext() , Manifest.permission.CAMERA );
        changeSwitchStatus(cameraPermission);
    }

    private void changeSwitchStatus( boolean status){
        try{
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if( swCameraPermission != null ){
                        swCameraPermission.setChecked(status);
                    }
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void findViewById(){
        try{

            clCamera = findViewById(R.id.cl_camera_permission);
            clCamera.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try{
                        Intent appDetail = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse( "package" + getPackageName() ) );
                        appDetail.addCategory(Intent.CATEGORY_DEFAULT);
                        appDetail.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity( appDetail );
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });

            clVersion = findViewById(R.id.cl_version);
            clCs = findViewById(R.id.cl_cs_info);
            clCs.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PhoneCall.call( getApplicationContext(), String.valueOf(R.string.cs_number).replace("-", "") );
                }
            });

            btnMoveInfoManager = findViewById(R.id.btn_move_info_manager);
            btnMoveInfoManager.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try{
                        startActivity( new Intent( getApplicationContext(), UserInfoActivity.class));
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });

            btnLogout = findViewById(R.id.btn_logout);
            btnLogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try{
                        PreferenceManager.setString( getApplicationContext() , PreferenceManager.USER_TOKEN, "");
                        finishAffinity();
                        startActivity(new Intent( getApplicationContext(), LoginActivity.class));
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });

            tvVersionName = findViewById(R.id.tv_version_name);
            String versionName = GetAppInfo.version(getApplicationContext());
            if( versionName != null && !versionName.equals("")){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvVersionName.setText(versionName);
                    }
                });
            }

            swCameraPermission = findViewById(R.id.sw_camera_permission);
            swCameraPermission.setChecked( PermissionCheck.check( getApplicationContext() , Manifest.permission.CAMERA ) );



        }catch (Exception e){
            e.printStackTrace();
        }
    }

}