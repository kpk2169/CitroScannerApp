package com.sekim.citroscanner.Activty.Result.Product;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.sekim.citroscanner.Activty.Scanner.ScannerActivity;
import com.sekim.citroscanner.R;
import com.sekim.citroscanner.Retrofit.User.StringFormatter;
import com.sekim.citroscanner.Utils.BarcodeBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

public class ShowProductActivity extends AppCompatActivity {

    private TextView tvName, tvBody, tvExcerpt, tvPrice;
    private ImageView imgProduct, imgBarcode;
    private Button btnHome, btnBack;

    private RecyclerView recyclerViewInfos;

    private JSONObject productData;
    private JSONArray productInfos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_product);

        getIntentParams();
        findViewById();
        setProductData();
    }

    private void getIntentParams(){
        try{
            Intent getIntent = getIntent();
            String getResultData = getIntent.getStringExtra("result");

            productData = new JSONObject( getResultData );

            if( productData != null ){
                productInfos = productData.getJSONArray("infos");
            }

        }catch (Exception e){
            e.printStackTrace();
            finish();
        }
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

            btnBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try{
                        startActivity(new Intent( getApplicationContext(), ScannerActivity.class ));
                        finish();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });

            btnHome = findViewById(R.id.btn_move_home);
            btnHome.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });

            recyclerViewInfos = findViewById(R.id.recycler_product_infos);
            if( productInfos.length() > 0 ){
                recyclerViewInfos.setVisibility( View.VISIBLE );

                recyclerViewInfos.setLayoutManager( new LinearLayoutManager(this));
                ProductInfosAdapter productInfosAdapter = new ProductInfosAdapter( productInfos );
                recyclerViewInfos.setAdapter( productInfosAdapter );
            }


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

    private void setProductData(){
        try{

            String name = productData.getString("name");
            int productPrice = productData.getInt("price");
            String price = StringFormatter.getMoneyFormat( productPrice );
            String body = productData.getString("body");
            String excerpt = productData.getString("excerpt");
            String barcodeStr = productData.getString("barcode");

            setTextView( tvName, name );
            setTextView( tvPrice , price );
            setTextView( tvBody, body );
            setTextView( tvExcerpt, excerpt );

            createBarcodeImage(barcodeStr);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void setTextView( TextView targetTextView, String data ){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try{
                    if( data.equals("")){
                        targetTextView.setVisibility(View.GONE);
                    }else{
                        targetTextView.setVisibility( View.VISIBLE );
                        targetTextView.setText(data);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    private void createBarcodeImage( String barcodeData  ){
        try{
            Bitmap bitmap = BarcodeBuilder.createBarcodeBitmap( BarcodeFormat.CODE_128, barcodeData);
            if( imgBarcode != null && bitmap != null ){
                imgBarcode.setImageBitmap(bitmap);
            }else{
                imgBarcode.setVisibility(View.GONE);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}