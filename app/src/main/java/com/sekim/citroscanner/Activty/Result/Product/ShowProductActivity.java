package com.sekim.citroscanner.Activty.Result.Product;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.sekim.citroscanner.R;

public class ShowProductActivity extends AppCompatActivity {

    private TextView tvName, tvBody, tvExcerpt, tvPrice;
    private ImageView imgProduct, imgBarcode;
    private Button btnHome, btnBack;

    private RecyclerView recyclerViewInfos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_product);

        findViewById();

    }

    private void findViewById(){
        try{

            tvName = findViewById(R.id.tv_product_title);
            tvBody = findViewById(R.id.tv_product_body);
            tvExcerpt = findViewById(R.id.tv_product_excerpt);
            tvPrice = findViewById(R.id.tv_product_price);

            imgBarcode = findViewById(R.id.img_product_barcode);

            imgProduct = findViewById(R.id.img_product);
            setResizeImageView();

            btnBack = findViewById(R.id.btn_pre_screen);
            btnHome = findViewById(R.id.btn_move_home);

            recyclerViewInfos = findViewById(R.id.recycler_product_infos);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void setResizeImageView(){
        try{
            if( imgProduct != null){
                DisplayMetrics metrics = new DisplayMetrics();
                WindowManager windowManager = ( WindowManager ) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
                windowManager.getDefaultDisplay().getMetrics(metrics);

                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) imgProduct.getLayoutParams();
                params.width = metrics.widthPixels;
                params.height = (int) (metrics.widthPixels * 0.75);

                imgProduct.setLayoutParams( params );

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void createBarcodeImage( String barcodeData  ){
        try{

            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.encodeBitmap(  barcodeData, BarcodeFormat.CODE_128, 200, 100 );
            if( imgBarcode != null ){
                imgBarcode.setImageBitmap(bitmap);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}