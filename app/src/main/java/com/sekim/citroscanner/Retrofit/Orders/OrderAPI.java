package com.sekim.citroscanner.Retrofit.Orders;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface OrderAPI {

    @GET("orders/barcodes/{barcode}")
    Call<OrderResult> inquiry(@Header("Authorization") String userToken , @Path("barcode") String barcode );

}
