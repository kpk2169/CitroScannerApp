package com.sekim.citroscanner.Retrofit.Barcode;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

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

    private class OrderData {
        @SerializedName("order")
        private DetailData detailData;

        public DetailData getDetailData() {
            return detailData;
        }

        private class DetailData {
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
            private String order_changed_at;

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








        }

    }

}
