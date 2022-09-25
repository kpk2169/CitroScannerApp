package com.sekim.citroscanner.Activty.Scanner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.sekim.citroscanner.R;

public class ScannerActivity extends AppCompatActivity {

    private final String TAG = "SCANNER";
    private final String PRODUCT = "products";
    private final String nowBtnMsg = " 확인하는 중...";
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
            clBtnChangeMode.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try{
                        if( mode.equals(PRODUCT)){
                            mode = RECEIPT;
                        }else if( mode.equals(RECEIPT) ){
                            mode = PRODUCT;
                        }
                        checkMode();

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
            tvMode = findViewById(R.id.tv_chage_mode);

            checkMode();




        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void checkMode(){
        if( mode.equals(PRODUCT)){
            changeBtnColor( R.color.citro_green , R.color.citro_orange );
            changeBtnText("상품", "영수증조회");
        }else if ( mode.equals(RECEIPT)){
            changeBtnColor( R.color.citro_orange , R.color.citro_green );
            changeBtnText("영수증", "상품조회");
        }
    }

    private void changeBtnColor( int leftColor, int rightColor ){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    btnNow.setBackgroundTintList( ColorStateList.valueOf( ContextCompat.getColor( getApplicationContext(), leftColor ) ) );
                    clBtnChangeMode.setBackgroundTintList( ColorStateList.valueOf( ContextCompat.getColor( getApplicationContext(), rightColor ) ) );
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
    private void changeBtnText( String leftStr, String rightStr ){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try{
                    btnNow.setText( leftStr+ nowBtnMsg);
                    tvMode.setText( rightStr );
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

}