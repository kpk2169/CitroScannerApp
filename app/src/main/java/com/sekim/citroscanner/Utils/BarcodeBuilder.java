package com.sekim.citroscanner.Utils;

import android.graphics.Bitmap;

import com.google.zxing.BarcodeFormat;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class BarcodeBuilder {

    public static Bitmap createBarcodeBitmap( BarcodeFormat barcodeFormat, String data){
        Bitmap bitmap = null;
        try{
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            bitmap = barcodeEncoder.encodeBitmap(  data, barcodeFormat, 200, 100 );
        }catch (Exception e){
            e.printStackTrace();
        }

        return bitmap;
    }

}
