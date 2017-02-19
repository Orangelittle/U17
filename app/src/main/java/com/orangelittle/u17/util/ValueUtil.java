package com.orangelittle.u17.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.orangelittle.u17.global.U17Application;


/**
 * Created by Administrator on 2016/7/5.
 */
public class ValueUtil {
    public static void putValue(String key, String value) {
        SharedPreferences sp = U17Application.getContext().getSharedPreferences("kuaimai9", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String getValue(String key) {
        SharedPreferences sp = U17Application.getContext().getSharedPreferences("kuaimai9", Context.MODE_PRIVATE);
        return sp.getString(key, null);
    }
}
