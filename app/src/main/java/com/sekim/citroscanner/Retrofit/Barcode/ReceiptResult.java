package com.sekim.citroscanner.Retrofit.Barcode;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

public class ReceiptResult {

    @SerializedName("status")
    private String status;

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private OrderData orderData;

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public OrderData getOrderData() {
        return orderData;
    }

    public class OrderData {
        @SerializedName("order")
        private DetailOrderData detailData;

        public DetailOrderData getDetailOrderData() {
            return detailData;
        }

        public class DetailOrderData {
            @SerializedName("id")
            private int id;

            @SerializedName("store_id")
            private int storeId;

            @SerializedName("device_id")
            private int deviceId;

            @SerializedName("order_number")
            private String orderNumber;

            @SerializedName("order_code")
            private String orderCode;

            @SerializedName("total_price")
            private int totalPrice;

            @SerializedName("discount")
            private int discount;

            @SerializedName("delivery_price")
            private int deliveryPrice;

            @SerializedName("price_to_pay")
            private int priceToPay;

            @SerializedName("vat")
            private int vat;

            @Nullable
            @SerializedName("order_type")
            private String orderType;

            @Nullable
            @SerializedName("package")
            private boolean isPackage;

            @SerializedName("barcode")
            private String barcode;

            @SerializedName("pay_status")
            private String payStatus;

            @SerializedName("paid_at")
            private String paidAt;

            @Nullable
            @SerializedName("pickup_in")
            private int pickupIn;

            @Nullable
            @SerializedName("pickup_at")
            private String pickupAt;

            @Nullable
            @SerializedName("pickuped_at")
            private String pickupedAt;

            @Nullable
            @SerializedName("canceled_at")
            private String canceledAt;

            @SerializedName("paymethod")
            private String paymethod;

            @SerializedName("payment_service")
            private String paymentService;

            @SerializedName("payment_corp")
            private String paymentCorp;

            @Nullable
            @SerializedName("customer_id")
            private int customerId;

            @Nullable
            @SerializedName("customer_name")
            private String customerName;

            @Nullable
            @SerializedName("customer_phone")
            private String customerPhone;

            @Nullable
            @SerializedName("plus_reward")
            private int plusReward;

            @Nullable
            @SerializedName("minus_reward")
            private int minusReward;

            @SerializedName("order_status")
            private String orderStatus;

            @SerializedName("order_changed_at")
            private String orderChangedAt;

            @Nullable
            @SerializedName("memo")
            private String memo;

            @SerializedName("show_status")
            private boolean showStatus;

            @SerializedName("called_at")
            private String calledAt;

            @SerializedName("created_at")
            private String createdAt;

            @SerializedName("updated_at")
            private String updatedAt;

            @Nullable
            @SerializedName("deleted_at")
            private String deletedAt;

            @NonNull
            @Override
            public String toString() {

                String returnValue = "";

                try{
                    JSONObject receiptJson = new JSONObject();

                    receiptJson.put( "id" , id );
                    receiptJson.put( "store_id" , storeId );
                    receiptJson.put( "device_id" , deviceId );
                    receiptJson.put( "order_number" , orderNumber );
                    receiptJson.put( "order_code" , orderCode );
                    receiptJson.put( "total_price" , totalPrice );
                    receiptJson.put( "discount" , discount );
                    receiptJson.put( "delivery_price" , deliveryPrice );
                    receiptJson.put( "price_to_pay" , priceToPay );
                    receiptJson.put( "vat" , vat );
                    receiptJson.put( "order_type" , orderType );
                    receiptJson.put( "package" , isPackage );
                    receiptJson.put( "barcode" , barcode );
                    receiptJson.put( "pay_status" , payStatus );
                    receiptJson.put( "paid_at" , paidAt );
                    receiptJson.put( "pickup_in" , pickupIn );
                    receiptJson.put( "pickup_at" , pickupAt );
                    receiptJson.put( "pickuped_at" , pickupedAt );
                    receiptJson.put( "canceled_at" , canceledAt );
                    receiptJson.put( "paymethod" , paymethod );
                    receiptJson.put( "payment_service" , paymentService );
                    receiptJson.put( "payment_corp" , paymentCorp );
                    receiptJson.put( "customer_id" , customerId );
                    receiptJson.put( "customer_name" , customerName );
                    receiptJson.put( "customer_phone" , customerPhone );
                    receiptJson.put( "plus_reward" , plusReward );
                    receiptJson.put( "minus_reward" , orderStatus );
                    receiptJson.put( "order_changed_at" , orderChangedAt );
                    receiptJson.put( "memo" , memo );
                    receiptJson.put( "show_status" , showStatus );
                    receiptJson.put( "called_at" , calledAt );
                    receiptJson.put( "created_at" , createdAt );
                    receiptJson.put( "updated_at" , updatedAt );
                    receiptJson.put( "deleted_at" , deletedAt );

                    returnValue = receiptJson.toString();

                }catch (Exception e){
                    e.printStackTrace();
                }


                return returnValue;
            }
        }

    }

}
