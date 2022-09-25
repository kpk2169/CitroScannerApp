package com.sekim.citroscanner.Retrofit.User;

public class StringFormatter {

    public static String getMoneyFormat( int price ){
        String value = "";

        try {
            value = String.format( "%,d", price);
        }catch (Exception e){
            e.printStackTrace();
        }

        return value;
    }
}
