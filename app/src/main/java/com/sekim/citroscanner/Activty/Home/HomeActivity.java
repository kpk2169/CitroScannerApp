package com.sekim.citroscanner.Activty.Home;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.sekim.citroscanner.R;

public class HomeActivity extends AppCompatActivity {


    private long preTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
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