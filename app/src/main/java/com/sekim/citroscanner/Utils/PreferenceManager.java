package com.sekim.citroscanner.Utils;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * 데이터 저장 및 로드 클래스
 */

public class PreferenceManager {

    public static final String PREFERENCES_NAME = "CitroScanner";
    private static final String DEFAULT_VALUE_STRING = "";
    private static final boolean DEFAULT_VALUE_BOOLEAN = false;
    private static final int DEFAULT_VALUE_INT = -1;
    private static final long DEFAULT_VALUE_LONG = -1L;
    private static final float DEFAULT_VALUE_FLOAT = -1F;

    public static final String USER_TOKEN = "USER_TOKEN";
    public static final String IS_FIRST = "IS_FIRST";


    private static SharedPreferences getPreferences(Context context) {

        return context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);

    }


    /**
     * String 값 저장
     *
     * @param context
     * @param key
     * @param value
     */



    public static void setString(Context context, String key, String value) {

        SharedPreferences prefs = getPreferences(context);

        SharedPreferences.Editor editor = prefs.edit();

        editor.putString(key, value);

        editor.apply();
        //editor.commit();

    }


    /**
     * boolean 값 저장
     *
     * @param context
     * @param key
     * @param value
     */

    public static void setBoolean(Context context, String key, boolean value) {

        SharedPreferences prefs = getPreferences(context);

        SharedPreferences.Editor editor = prefs.edit();

        editor.putBoolean(key, value);

        editor.apply();

    }


    /**
     * int 값 저장
     *
     * @param context
     * @param key
     * @param value
     */

    public static void setInt(Context context, String key, int value) {

        SharedPreferences prefs = getPreferences(context);

        SharedPreferences.Editor editor = prefs.edit();

        editor.putInt(key, value);

        editor.apply();

    }


    /**
     * long 값 저장
     *
     * @param context
     * @param key
     * @param value
     */

    public static void setLong(Context context, String key, long value) {

        SharedPreferences prefs = getPreferences(context);

        SharedPreferences.Editor editor = prefs.edit();

        editor.putLong(key, value);

        editor.apply();

    }


    /**
     * float 값 저장
     *
     * @param context
     * @param key
     * @param value
     */

    public static void setFloat(Context context, String key, float value) {

        SharedPreferences prefs = getPreferences(context);

        SharedPreferences.Editor editor = prefs.edit();

        editor.putFloat(key, value);

        editor.apply();

    }


    /**
     * String 값 로드
     *
     * @param context
     * @param key
     * @return
     */

    public static String getString(Context context, String key) {

        SharedPreferences prefs = getPreferences(context);
        String value = prefs.getString(key, DEFAULT_VALUE_STRING);

        return value;

    }


    /**
     * boolean 값 로드
     *
     * @param context
     * @param key
     * @return
     */

    public static boolean getBoolean(Context context, String key) {

        SharedPreferences prefs = getPreferences(context);

        boolean value = prefs.getBoolean(key, DEFAULT_VALUE_BOOLEAN);

        return value;

    }


    /**
     * int 값 로드
     *
     * @param context
     * @param key
     * @return
     */

    public static int getInt(Context context, String key) {

        SharedPreferences prefs = getPreferences(context);

        int value = prefs.getInt(key, DEFAULT_VALUE_INT);

        return value;

    }


    /**
     * long 값 로드
     *
     * @param context
     * @param key
     * @return
     */

    public static long getLong(Context context, String key) {

        SharedPreferences prefs = getPreferences(context);

        long value = prefs.getLong(key, DEFAULT_VALUE_LONG);

        return value;

    }


    /**
     * float 값 로드
     *
     * @param context
     * @param key
     * @return
     */

    public static float getFloat(Context context, String key) {

        SharedPreferences prefs = getPreferences(context);

        float value = prefs.getFloat(key, DEFAULT_VALUE_FLOAT);

        return value;

    }




    /**
     * 키 값 삭제
     *
     * @param context
     * @param key
     */

    public static void removeKey(Context context, String key) {

        SharedPreferences prefs = getPreferences(context);

        SharedPreferences.Editor edit = prefs.edit();

        edit.remove(key);

        edit.apply();

    }


    /**
     * 모든 저장 데이터 삭제
     *
     * @param context
     */

    public static void clear(Context context) {

        SharedPreferences prefs = getPreferences(context);

        SharedPreferences.Editor edit = prefs.edit();

        edit.clear();

        edit.apply();

    }

}