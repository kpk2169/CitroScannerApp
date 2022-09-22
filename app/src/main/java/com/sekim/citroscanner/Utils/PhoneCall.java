package com.sekim.citroscanner.Utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class PhoneCall {

    public static void call(Context context, String phoneNum){
        try{
            Intent intent = new Intent( Intent.ACTION_DIAL, Uri.parse( "tel:" + phoneNum));
            context.startActivity( intent );
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
