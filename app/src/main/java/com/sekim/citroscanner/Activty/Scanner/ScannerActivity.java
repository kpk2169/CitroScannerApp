package com.sekim.citroscanner.Activty.Scanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.sekim.citroscanner.R;

public class ScannerActivity extends AppCompatActivity {

    private final String TAG = "SCANNER";
    private String mode;

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



        }catch (Exception e){
            e.printStackTrace();
        }
    }

}