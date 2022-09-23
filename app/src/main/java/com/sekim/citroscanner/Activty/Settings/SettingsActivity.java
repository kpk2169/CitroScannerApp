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
import android.widget.Toast;

import com.sekim.citroscanner.Activty.Login.LoginActivity;
import com.sekim.citroscanner.Activty.UserInfo.UserInfoActivity;
import com.sekim.citroscanner.R;
import com.sekim.citroscanner.Retrofit.RetrofitBuilder;
import com.sekim.citroscanner.Retrofit.User.GetUserInfoResult;
import com.sekim.citroscanner.Retrofit.User.UserAPI;
import com.sekim.citroscanner.Utils.GetAppInfo;
import com.sekim.citroscanner.Utils.PermissionCheck;
import com.sekim.citroscanner.Utils.PhoneCall;
import com.sekim.citroscanner.Utils.PreferenceManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SettingsActivity extends AppCompatActivity {

    private ConstraintLayout clCamera, clVersion, clCs;
    private Button btnMoveInfoManager, btnLogout;
    private TextView tvVersionName, tvUserName;
    private Switch swCameraPermission;
    private boolean cameraPermission;
    private Retrofit retrofit;
    private String userToken;
    private final String intro = " 님\n안녕하세요.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        findViewById();
        runGetUserInfo();
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

            userToken = PreferenceManager.getString(getApplicationContext(), PreferenceManager.USER_TOKEN );

            tvUserName = findViewById(R.id.tv_user_name);

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

    private void runGetUserInfo(){
        try{
            retrofit = RetrofitBuilder.RetrofitClient();
            UserAPI userAPI = retrofit.create( UserAPI.class );
            Call<GetUserInfoResult> userAPICall = userAPI.getUserInfo( userToken );
            userAPICall.enqueue(new Callback<GetUserInfoResult>() {
                @Override
                public void onResponse(Call<GetUserInfoResult> call, Response<GetUserInfoResult> response) {
                    if( response.isSuccessful() ){
                        GetUserInfoResult apiResult = response.body();
                        if( apiResult.getStatus().equals("success") ){
                            GetUserInfoResult.User.UserInfo userInfo = apiResult.getUser().getUserInfo();
                            String userName = userInfo.getName();

                            if( tvUserName != null ){
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        tvUserName.setText( userName + intro );
                                    }
                                });
                            }
                        }

                    }else{
                        Toast.makeText(getApplicationContext(), "인터넷 연결 상태를 확인해 주세요.", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<GetUserInfoResult> call, Throwable t) {

                }
            });


        }catch (Exception e){
            e.printStackTrace();
        }
    }

}