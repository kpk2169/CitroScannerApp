package com.sekim.citroscanner.Utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.content.ContextCompat;

public class PermissionCheck {

    public static boolean check(Context context, String permission){

        boolean result = false;

        try {
            if(ContextCompat.checkSelfPermission(  context , permission ) != PackageManager.PERMISSION_GRANTED){
                result = true;
            }else{
                result = false;
            }


        }catch (Exception e){
            e.printStackTrace();
            result = false;
        }

        return result;
    }
}
