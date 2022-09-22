package com.sekim.citroscanner.Utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;

public class SettingDialog {

    public static void show(Context context, String title , String message ){
        AlertDialog.Builder builder = new AlertDialog.Builder( context );
        builder.setMessage(message)
                .setPositiveButton("이동하기", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try{
                            Intent appDetail = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse( "package" + context.getPackageName() ) );
                            appDetail.addCategory(Intent.CATEGORY_DEFAULT);
                            appDetail.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity( appDetail );
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.setTitle(title);
        alertDialog.show();

    }

}
