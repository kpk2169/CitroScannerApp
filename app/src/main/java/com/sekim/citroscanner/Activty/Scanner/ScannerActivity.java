package com.sekim.citroscanner.Activty.Scanner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.sekim.citroscanner.R;

public class ScannerActivity extends AppCompatActivity {

    private final String TAG = "SCANNER";
    private final String PRODUCT = "products";
    private final String RECEIPT = "orders";
    private String mode;

    private Button btnNow;
    private ConstraintLayout clBtnChangeMode;
    private TextView tvMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);

        getIntentParams();
        findViewById();


    }

    private void getIntentParams(){
        try{
            Intent getIntent = getIntent();
            mode = getIntent.getStringExtra(String.valueOf(R.string.mode_tag));
        }catch (Exception e){
            e.printStackTrace();
            finish();
        }
    }

    private void findViewById(){
        try{
            btnNow = findViewById(R.id.btn_now_mode);
            btnNow.setEnabled(false);



            clBtnChangeMode = findViewById(R.id.cl_btn_exchange);
            tvMode = findViewById(R.id.tv_chage_mode);

            if( mode.equals(PRODUCT)){

            }else if ( mode.equals(RECEIPT)){

            }

            btnNow.setBackgroundTintList( ColorStateList.valueOf(ContextCompat.getColor( getApplicationContext(), R.color.citro_grey ))  );



        }catch (Exception e){
            e.printStackTrace();
        }
    }

}