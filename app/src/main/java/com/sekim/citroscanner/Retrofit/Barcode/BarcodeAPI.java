package com.sekim.citroscanner.Retrofit.Barcode;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface BarcodeAPI {

    @GET("/orders/barcodes/{barcode}")
    Call<ReceiptResult> getReceipt(@Header("Authorization") String userToken , @Path("barcode") String barcode  );

    @GET("/products/barcodes/{barcode}")
    Call<ReceiptResult> getProductInfo(@Header("Authorization") String userToken , @Path("barcode") String barcode  );
}
