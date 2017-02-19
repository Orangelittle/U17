package com.orangelittle.u17.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import com.orangelittle.u17.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/6/1.
 */
public class StringUtil {
    public static boolean isNull(String s) {
        if (s == null || s.equals("") || s.equals("null")||s.isEmpty()) {
            return true;
        }
        return false;
    }

    public static String tvText(TextView tv) {
        return tv.getText().toString().trim();
    }

    //时间戳转化为Sting或Date
    public static String changeDate(long atime) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(atime * 1000);
    }

    //毫秒转化为Sting或Date
    public static String changeDateNow(long atime) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd ");
        return format.format(atime);
    }

    //检验是否是电话号码
    public static boolean isMobileNO(String mobiles) {
        Pattern p = Pattern.compile("^1[34578]{1}\\d{9}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    /**
     * 保留两位小数
     */
    public static String formatData(double f) {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(f);
    }

    //检测是否包含特殊表情符号
    public static boolean containsEmoji(String source) {
        int len = source.length();
        boolean isEmoji = false;
        for (int i = 0; i < len; i++) {
            char hs = source.charAt(i);
            if (0xd800 <= hs && hs <= 0xdbff) {
                if (source.length() > 1) {
                    char ls = source.charAt(i+1);
                    int uc = ((hs - 0xd800) * 0x400) + (ls - 0xdc00) + 0x10000;
                    if (0x1d000 <= uc && uc <= 0x1f77f) {
                        return true;
                    }
                }
            } else {
                // non surrogate
                if (0x2100 <= hs && hs <= 0x27ff && hs != 0x263b) {
                    return true;
                } else if (0x2B05 <= hs && hs <= 0x2b07) {
                    return true;
                } else if (0x2934 <= hs && hs <= 0x2935) {
                    return true;
                } else if (0x3297 <= hs && hs <= 0x3299) {
                    return true;
                } else if (hs == 0xa9 || hs == 0xae || hs == 0x303d || hs == 0x3030 || hs == 0x2b55 || hs == 0x2b1c || hs == 0x2b1b || hs == 0x2b50|| hs == 0x231a ) {
                    return true;
                }
                if (!isEmoji && source.length() > 1 && i < source.length() -1) {
                    char ls = source.charAt(i+1);
                    if (ls == 0x20e3) {
                        return true;
                    }
                }
            }
        }
        return  isEmoji;
    }

    //从文件读取Json数据
    public static String getJson(Context context, String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            AssetManager assetManager = context.getAssets();
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public static void setColorText(Context context, TextView tv, int start, int end){
        if(!StringUtil.isNull(tv.getText().toString())){
            SpannableString spanText = new SpannableString(tv.getText().toString());
            spanText.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.pink)), start, end, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
            tv.setText(spanText);
        }
    }
}
