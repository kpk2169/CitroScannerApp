package com.sekim.citroscanner.Retrofit.Barcode;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;
import org.json.JSONObject;

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

        @NonNull
        @Override
        public String toString() {
            String returnValue = "";

            try{
                JSONObject resultObject = new JSONObject();

                resultObject.put("id", id );
                resultObject.put("store_id", storeId );
                resultObject.put("product_group_id", productGroupId );
                resultObject.put("type", type );
                resultObject.put("name", name );
                resultObject.put("price", price );
                resultObject.put("body", body );
                resultObject.put("excerpt", excerpt );
                resultObject.put("barcode", barcode );

                JSONArray infoArr = new JSONArray();
                for( int i = 0; i < productInfoList.size(); i++ ){
                    ProductInfo info = productInfoList.get(i);
                    JSONObject infoObj = new JSONObject();
                    infoObj.put("title", info.getTitle());
                    infoObj.put("body", info.getBody());
                    infoArr.put( infoObj );
                }

                resultObject.put("infos", infoArr );

                returnValue = resultObject.toString();

            }catch (Exception e){
                e.printStackTrace();
            }

            return returnValue;
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
