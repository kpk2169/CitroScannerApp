package com.sekim.citroscanner.Retrofit.Barcode;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


public class ProductResult {

    @SerializedName("status")
    private String status;

    @SerializedName("message")
    private String message;

    @Nullable
    @SerializedName("data")
    private ProductResultData productData;

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    @Nullable
    public ProductResultData getProductData() {
        return productData;
    }

    public class ProductResultData {
        @SerializedName("product")
        private ProductData productData;

        public ProductData getProductData() {
            return productData;
        }
    }

    public class ProductData{
        @SerializedName("id")
        private Integer id;

        @SerializedName("store_id")
        private Integer storeId;

        @Nullable
        @SerializedName("product_group_id")
        private Integer productGroupId;

        @SerializedName("type")
        private String type;

        @SerializedName("name")
        private String name;

        @SerializedName("price")
        private Integer price;

        @SerializedName("infos")
        private ArrayList<ProductInfo> productInfoList;

        @SerializedName("body")
        private String body;

        @SerializedName("excerpt")
        private String excerpt;

        @SerializedName("barcode")
        private String barcode;

        public Integer getId() {
            return id;
        }

        public Integer getStoreId() {
            return storeId;
        }

        @Nullable
        public Integer getProductGroupId() {
            return productGroupId;
        }

        public String getType() {
            return type;
        }

        public String getName() {
            return name;
        }

        public Integer getPrice() {
            return price;
        }

        public ArrayList<ProductInfo> getProductInfoList() {
            return productInfoList;
        }

        public String getBody() {
            return body;
        }

        public String getExcerpt() {
            return excerpt;
        }

        public String getBarcode() {
            return barcode;
        }
    }

    public class ProductInfo{
        @SerializedName("title")
        private String title;

        @SerializedName("body")
        private String body;

        public String getTitle() {
            return title;
        }

        public String getBody() {
            return body;
        }
    }

}
