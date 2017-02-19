package com.orangelittle.u17.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/5/30.
 */
public class ToastUtil {
    public static void show(Context context, String content){
        Toast.makeText(context,content, Toast.LENGTH_SHORT).show();
    }
}
