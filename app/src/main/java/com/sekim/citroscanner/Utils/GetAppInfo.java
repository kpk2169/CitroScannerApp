package com.sekim.citroscanner.Utils;

import android.content.Context;
import android.content.pm.PackageInfo;

public class GetAppInfo {

    public static String version(Context context){
        String version = null;

        try{
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo( context.getPackageName(), 0);
            version = packageInfo.versionName;
        }catch (Exception e){
            e.printStackTrace();
        }

        return version;
    }
}
