package com.orangelittle.u17.util;

import android.content.SharedPreferences;
import android.os.Environment;

import com.orangelittle.u17.global.U17Application;

import java.io.File;

/**
 * Created by Ice on 2016/10/4.
 */

public class FileUtils {

    private static SharedPreferences preferences = U17Application.context.getSharedPreferences("gameFile",U17Application.context.MODE_PRIVATE);

    public static boolean isExists(String filePath){
        filePath = filePath.replaceAll("/", "_");
        boolean exists = new File(getDir(), filePath).exists();
        return exists;
    }

    public static String getDir(){
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)+"";
    }

    //存储下载进度
    public static void saveProgress(final File des, final float progress){
        new Thread(new Runnable() {
            @Override
            public void run() {
                preferences.edit().putFloat(des.toString(), progress).apply();
            }
        }).start();
    }
    //获取下载进度
    public static float getProgress(File des){
        return preferences.getFloat(des.toString(),0);
    }
}
