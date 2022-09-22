package com.sekim.citroscanner.Activty.Home;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sekim.citroscanner.Activty.Scanner.ScannerActivity;
import com.sekim.citroscanner.Activty.Settings.SettingsActivity;
import com.sekim.citroscanner.R;
import com.sekim.citroscanner.Utils.PermissionCheck;
import com.sekim.citroscanner.Utils.SettingDialog;

public class HomeActivity extends AppCompatActivity {

    private ConstraintLayout btnProductLayout, btnReceiptLayout;
    private ImageView btnSettings;
    private TextView tvCsNumber;
    private long preTime;
    private boolean cameraPermission;
    private String dialogMessage = "해당 기능을 사용하기 위해서는\\n카메라 권한이 필요합니다.\\n설정 화면으로 이동하시겠습니까?";
    private String dialogTitle = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        findViewById();
    }

    @Override
    protected void onResume() {
        super.onResume();
        cameraPermission = PermissionCheck.check( getApplicationContext() , Manifest.permission.CAMERA );
    }

    private void findViewById(){
        try{
            btnProductLayout = findViewById(R.id.cl_search_product);
            btnReceiptLayout = findViewById(R.id.cl_search_receipt);

            btnSettings = findViewById(R.id.btn_move_setting);

            tvCsNumber = findViewById(R.id.tv_home_cs);

            tvCsNumber.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent phoneCallIntent = new Intent(Intent.ACTION_DIAL , Uri.parse("tel:" + String.valueOf(R.string.cs_number)));
                    startActivity(phoneCallIntent);
                }
            });

            btnProductLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    moveActivityWithParams("product");
                }
            });

            btnReceiptLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    moveActivityWithParams("receipt");
                }
            });

            btnSettings.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent( getApplicationContext(), SettingsActivity.class ));
                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void moveActivityWithParams( String mode ){
        try{
            if( cameraPermission ){
                Intent scannerIntent = new Intent( getApplicationContext(), ScannerActivity.class);
                scannerIntent.putExtra(String.valueOf(R.string.mode_tag), mode);
                startActivity( scannerIntent );
            }else{
                SettingDialog.show( getApplicationContext() , dialogTitle, dialogMessage );
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - preTime >= 2000) {
            preTime = System.currentTimeMillis();
            Toast.makeText(getApplicationContext(), "뒤로가기 버튼을 한 번더 누르면 종료합니다.", Toast.LENGTH_SHORT).show();
        } else if (System.currentTimeMillis() - preTime < 2000) {
            finish();
            return;
        }
    }
}