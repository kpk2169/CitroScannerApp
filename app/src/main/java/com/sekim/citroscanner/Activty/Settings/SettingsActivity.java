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

import com.sekim.citroscanner.R;
import com.sekim.citroscanner.Utils.PermissionCheck;

public class SettingsActivity extends AppCompatActivity {

    private ConstraintLayout clCamera, clVersion, clCs;
    private Button btnMoveInfoManager, btnLogout;
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



        }catch (Exception e){
            e.printStackTrace();
        }
    }

}