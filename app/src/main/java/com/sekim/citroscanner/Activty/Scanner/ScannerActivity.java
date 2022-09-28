package com.sekim.citroscanner.Activty.Scanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Point;
import android.graphics.Rect;
import android.media.Image;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.Surface;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.mlkit.vision.barcode.BarcodeScanner;
import com.google.mlkit.vision.barcode.BarcodeScannerOptions;
import com.google.mlkit.vision.barcode.BarcodeScanning;
import com.google.mlkit.vision.barcode.common.Barcode;
import com.google.mlkit.vision.common.InputImage;
import com.google.zxing.client.result.ProductParsedResult;
import com.sekim.citroscanner.Activty.Result.Product.ShowProductActivity;
import com.sekim.citroscanner.Activty.Result.Receipt.ShowReceiptActivity;
import com.sekim.citroscanner.R;
import com.sekim.citroscanner.Retrofit.Barcode.BarcodeAPI;
import com.sekim.citroscanner.Retrofit.Barcode.ProductResult;
import com.sekim.citroscanner.Retrofit.Barcode.ReceiptResult;
import com.sekim.citroscanner.Retrofit.RetrofitBuilder;
import com.sekim.citroscanner.Utils.PreferenceManager;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ScannerActivity extends AppCompatActivity {

    private final String TAG = "SCANNER";
    private final String PRODUCT = "products";
    private final String nowBtnMsg = " 확인하는 중...";
    private final String RECEIPT = "orders";
    private String mode;

    private Button btnNow;
    private ConstraintLayout clBtnChangeMode;
    private TextView tvMode;
    private String userToken;
    private Retrofit retrofit;
    private BarcodeAPI barcodeAPI;

    private PreviewView scanPreview;
    private ListenableFuture cameraProviderFuture;
    private ExecutorService cameraExecutor;
    private MyImageAnalyzer myImageAnalyzer;

    private static String preBarcode = "";
    private static long preTime = 0;

    private static Ringtone rt;
    private static Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);

        getIntentParams();
        cameraInit();
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
    private void cameraInit(){
        try{
            scanPreview = findViewById(R.id.scanner_preview);
            cameraExecutor = Executors.newSingleThreadExecutor();
            cameraProviderFuture = ProcessCameraProvider.getInstance(this);
            myImageAnalyzer = new MyImageAnalyzer(this.getSupportFragmentManager());

            cameraProviderFuture.addListener(new Runnable() {
                @Override
                public void run() {
                    try {
                        ProcessCameraProvider processCameraProvider = ( ProcessCameraProvider ) cameraProviderFuture.get();

                        bindPreview(processCameraProvider);

                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }, ContextCompat.getMainExecutor(this));


            vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void bindPreview(ProcessCameraProvider processCameraProvider) {
        Preview preview = new Preview.Builder().build();
        CameraSelector cameraSelector = new CameraSelector.Builder()
                .requireLensFacing(CameraSelector.LENS_FACING_FRONT)
                .build();

        ImageCapture imageCapture =new ImageCapture.Builder().build();
        ImageAnalysis imageAnalysis = new ImageAnalysis.Builder()
                .setTargetRotation(Surface.ROTATION_270)
                .setBackpressureStrategy(ImageAnalysis.STRATEGY_BLOCK_PRODUCER)
                .build();
        preview.setSurfaceProvider(scanPreview.getSurfaceProvider());

        imageAnalysis.setAnalyzer(cameraExecutor, myImageAnalyzer);

        processCameraProvider.unbindAll();
        processCameraProvider.bindToLifecycle(this, cameraSelector, preview, imageCapture, imageAnalysis);

    }

    private void closeCamera(){
        if (cameraProviderFuture != null && cameraExecutor != null){
            cameraProviderFuture.cancel(true);
            cameraProviderFuture = null;
            cameraExecutor.shutdown();
            cameraExecutor = null;
        }
    }

    private void findViewById(){
        try{
            userToken = PreferenceManager.getString(getApplicationContext(), PreferenceManager.USER_TOKEN);



            retrofit = RetrofitBuilder.RetrofitClient();
            barcodeAPI = retrofit.create(BarcodeAPI.class);

            btnNow = findViewById(R.id.btn_now_mode);
            btnNow.setEnabled(false);

            clBtnChangeMode = findViewById(R.id.cl_btn_exchange);
            clBtnChangeMode.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try{
                        if( mode.equals(PRODUCT)){
                            mode = RECEIPT;
                        }else if( mode.equals(RECEIPT) ){
                            mode = PRODUCT;
                        }
                        checkMode();

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
            tvMode = findViewById(R.id.tv_chage_mode);

            checkMode();




        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void checkMode(){
        if( mode.equals(PRODUCT)){
            changeBtnColor( R.color.citro_green , R.color.citro_orange );
            changeBtnText("상품", "영수증조회");
        }else if ( mode.equals(RECEIPT)){
            changeBtnColor( R.color.citro_orange , R.color.citro_green );
            changeBtnText("영수증", "상품조회");
        }
    }

    private void changeBtnColor( int leftColor, int rightColor ){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    btnNow.setBackgroundTintList( ColorStateList.valueOf( ContextCompat.getColor( getApplicationContext(), leftColor ) ) );
                    clBtnChangeMode.setBackgroundTintList( ColorStateList.valueOf( ContextCompat.getColor( getApplicationContext(), rightColor ) ) );
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    private void changeBtnText( String leftStr, String rightStr ){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try{
                    btnNow.setText( leftStr+ nowBtnMsg);
                    tvMode.setText( rightStr );
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    private void runProductBarcodeAPI( String barcode ){
        try{
            Call<ProductResult> barcodeAPICall = barcodeAPI.getProductInfo( userToken, barcode );
            barcodeAPICall.enqueue(new Callback<ProductResult>() {
                @Override
                public void onResponse(Call<ProductResult> call, Response<ProductResult> response) {
                    if ( response.isSuccessful() ){
                        ProductResult apiResult = response.body();
                        if( apiResult.getStatus().equals("success") ){
                            String params = apiResult.getProductData().getProductData().toString();
                            Intent resultIntent = new Intent( getApplicationContext(), ShowProductActivity.class);
                            resultIntent.putExtra("result", params );
                            startActivity(resultIntent);
                            finish();
                        }else{
                            Toast.makeText(getApplicationContext(), String.valueOf(R.string.api_err_msg), Toast.LENGTH_SHORT).show();
                            preBarcode = "";
                        }

                    }
                }

                @Override
                public void onFailure(Call<ProductResult> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), String.valueOf(R.string.api_err_msg), Toast.LENGTH_SHORT).show();
                    //  바코드 초기화
                    preBarcode = "";
                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void runReceiptBarcodeAPI( String barcode ){
        try{
            Call<ReceiptResult> barcodeAPICall = barcodeAPI.getReceipt( userToken, barcode );
            barcodeAPICall.enqueue(new Callback<ReceiptResult>() {
                @Override
                public void onResponse(Call<ReceiptResult> call, Response<ReceiptResult> response) {
                    if ( response.isSuccessful() ){
                        ReceiptResult apiResult = response.body();
                        if( apiResult.getStatus().equals("success") ){
                            String params = apiResult.getOrderData().getDetailOrderData().toString();
                            Intent resultIntent = new Intent( getApplicationContext(), ShowReceiptActivity.class);
                            resultIntent.putExtra("result", params );
                            startActivity(resultIntent);
                            finish();
                        }else{
                            Toast.makeText(getApplicationContext(), String.valueOf(R.string.api_err_msg), Toast.LENGTH_SHORT).show();
                            preBarcode = "";
                        }

                    }
                }

                @Override
                public void onFailure(Call<ReceiptResult> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), String.valueOf(R.string.api_err_msg), Toast.LENGTH_SHORT).show();
                    //  바코드 초기화
                    preBarcode = "";
                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public class MyImageAnalyzer implements ImageAnalysis.Analyzer{

        private FragmentManager fragmentManager;

        public MyImageAnalyzer(FragmentManager fragmentManager){
            this.fragmentManager = fragmentManager;
        }

        @Override
        public void analyze(@NonNull ImageProxy image) {
            scanBarcode(image);
        }

        private void scanBarcode( ImageProxy image ){
            @SuppressLint("UnsafeOptInUsageError") Image getInputImage = image.getImage();
            InputImage inputImage = InputImage.fromMediaImage(  getInputImage, image.getImageInfo().getRotationDegrees());

            BarcodeScannerOptions options = new BarcodeScannerOptions.Builder().setBarcodeFormats(Barcode.FORMAT_ALL_FORMATS).build();

            BarcodeScanner scanner = BarcodeScanning.getClient(options);

            // image analyzer
            Task<List<Barcode>> result = scanner.process(inputImage).addOnSuccessListener(new OnSuccessListener<List<Barcode>>() {
                @Override
                public void onSuccess(List<Barcode> barcodes) {

                    readerBarcodeData(barcodes);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d("cameraFail::", e.toString());
                }
            }).addOnCompleteListener(new OnCompleteListener<List<Barcode>>() {
                @Override
                public void onComplete(@NonNull Task<List<Barcode>> task) {
                    image.close();
                }
            });



        }

        private void playRingTone(){
            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            rt = RingtoneManager.getRingtone( getApplicationContext(), notification);
            rt.play();

        }

        private void playVibrator(){
            try{
                vibrator.vibrate(100);
            }catch (Exception e){
                e.printStackTrace();
            }

        }

        //get information barcode
        private void readerBarcodeData(List<Barcode> barcodes) {

            for (Barcode barcode: barcodes) {
                Rect bounds = barcode.getBoundingBox();
                Point[] corners = barcode.getCornerPoints();

                String rawValue = barcode.getRawValue();

                int valueType = barcode.getValueType();

                String getValue = barcode.getDisplayValue();

                Date nowTime = new Date();
                if (  nowTime.getTime() - preTime > 1000 ){
                    Log.d("======timeCheck", nowTime.getTime() + "  preTime :" + preTime + "   \n\tdiff : " + ( nowTime.getTime() - preTime ) );
                }

                preTime = nowTime.getTime();

                Log.d("============barcode", "직전에 인식된 바코드 " + preBarcode +" 지금 인식된 바코드" + getValue + "    rawValue : " + rawValue);

                if( !preBarcode.equals(getValue) ){


                    Log.d("============barcode", getValue);

                    playRingTone();
                    playVibrator();

                    if( mode.equals(RECEIPT)){
                        runReceiptBarcodeAPI(getValue);
                    }else{
                        runProductBarcodeAPI(getValue);
                    }
                    preBarcode = getValue;

                }

            }
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        closeCamera();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        closeCamera();
    }
}