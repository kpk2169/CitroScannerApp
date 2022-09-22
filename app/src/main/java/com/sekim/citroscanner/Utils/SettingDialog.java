package com.sekim.citroscanner.Utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class SettingDialog {

    public static void show(Context context, String title , String message ){
        AlertDialog.Builder builder = new AlertDialog.Builder( context );
        builder.setMessage(message)
                .setPositiveButton("이동하기", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.setTitle(title);
        alertDialog.show();

    }

}
