package com.orangelittle.u17.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.TextView;

import com.orangelittle.u17.R;
import com.orangelittle.u17.global.U17Application;
import com.orangelittle.u17.util.CustomArgs;
import com.orangelittle.u17.util.StringUtil;
import com.orangelittle.u17.util.ToastUtil;
import com.orangelittle.u17.util.ValueUtil;
import com.orangelittle.u17.widget.NoteDialog;

/**
 * Created by King on 2017/2/18
 */
public class SplashActivity extends Activity {
    private boolean canUse = false;
    private TextView tvShopName;
    private ImageView ivLogo;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0x233:
                    if (checkIsFirstRun()) {
                        startActivity(new Intent(SplashActivity.this, GuideActivity.class));
                        finish();
                        overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
                    } else {
                        startActivity(new Intent(SplashActivity.this, LoginActiivty.class));
                        finish();
                        overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
                    }
                    break;
//                case CustomArgs.EXIT:
//                    finish();
//                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        CustomUtil.initTitleBar(this);
//        //设置无标题
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        //设置全屏
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        initView();
        if (Integer.parseInt(Build.VERSION.SDK) >= 23) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                    != PackageManager.PERMISSION_GRANTED) {
                //请求设备信息权限
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE},
                        CustomArgs.MY_PERMISSIONS_REQUEST_READ_PHONE_STATE);
                canUse = false;
            } else {
                TelephonyManager tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
                U17Application.uuid = tm.getDeviceId();
                ValueUtil.putValue(U17Application.UUID, tm.getDeviceId());
                if (!StringUtil.isNull(ValueUtil.getValue(U17Application.UUID))) {
                    canUse = true;
                    handler.sendEmptyMessageDelayed(0x233, 2000);
                } else {
                    canUse = false;
                    NoteDialog noteDialog = new NoteDialog(SplashActivity.this);
                    noteDialog.setClickOutSide(false);
                    noteDialog.setContent("没有权限程序将无法使用，是否去设置相关权限？");
                    noteDialog.setNoteDialogListener(new NoteDialog.NoteDialogListener() {
                        @Override
                        public void handleConfrm() {
                            goSetting();
                        }

                        @Override
                        public void handleCancel() {
                            ToastUtil.show(SplashActivity.this, "程序即将退出");
                            handler.sendEmptyMessageDelayed(CustomArgs.EXIT, 3000);
                        }
                    });
                    noteDialog.show();
                }
            }
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_SETTINGS)
                    != PackageManager.PERMISSION_GRANTED) {
                //请求设备信息权限
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_SETTINGS},1);
            }
        } else {
            TelephonyManager tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
            U17Application.uuid = tm.getDeviceId();
            ValueUtil.putValue(U17Application.UUID, tm.getDeviceId());
            handler.sendEmptyMessageDelayed(0x233, 2000);
        }
    }

    private void initView(){
        tvShopName = (TextView) findViewById(R.id.splash_name);
        ivLogo = (ImageView) findViewById(R.id.splash_iv);
        ivLogo.setImageAlpha(53);
        ivLogo.setImageResource(R.mipmap.bg_login_top);
//        if(StringUtil.isNull(ValueUtil.getValue(BaseApplication.SHOP_NAME))){
//            tvShopName.setText("快买酒");
//        }else {
//            tvShopName.setText(ValueUtil.getValue(BaseApplication.SHOP_NAME));
//        }
//
//        if(StringUtil.isNull(ValueUtil.getValue(BaseApplication.SHOP_LOGO))){
//            ivLogo.setImageResource(R.drawable.logo);
//        }else {
//            Picasso.with(SplashActivity.this).load(ValueUtil.getValue(BaseApplication.SHOP_LOGO)).into(ivLogo);
//        }
    }

    /**
     * 方法说明： checkIsFirstRun()应用第一次启动判断
     */
    public boolean checkIsFirstRun() {
        SharedPreferences sp = getSharedPreferences("CXTIMES", Context.MODE_PRIVATE);
        int currentAppVersionCode = 1;
        try {
            currentAppVersionCode = getPackageManager().getPackageInfo(
                    getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        int savecode = sp.getInt("vercode", 0);
        if (currentAppVersionCode > savecode) {
            SharedPreferences.Editor editor = sp.edit();
            editor.putInt("vercode", currentAppVersionCode);
            editor.commit();
            return true;
        }
        return false;
    }


    private void goSetting() {
        Intent intentSetting = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intentSetting.setData(Uri.parse("package:" + this.getPackageName()));
        startActivityForResult(intentSetting, CustomArgs.SETTING);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case CustomArgs.MY_PERMISSIONS_REQUEST_READ_PHONE_STATE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                            != PackageManager.PERMISSION_GRANTED) {
                        //请求设备信息权限
                        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE},
                                CustomArgs.MY_PERMISSIONS_REQUEST_READ_PHONE_STATE);
                        canUse = false;
                    } else {
                        TelephonyManager tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
                        U17Application.uuid = tm.getDeviceId();
                        ValueUtil.putValue(U17Application.UUID, tm.getDeviceId());
                        if (!StringUtil.isNull(ValueUtil.getValue(U17Application.UUID))) {
                            canUse = true;
                            handler.sendEmptyMessageDelayed(0x233, 2000);
                        } else {
                            canUse = false;
                            NoteDialog noteDialog = new NoteDialog(SplashActivity.this);
                            noteDialog.setContent("没有权限程序将无法使用，是否去设置相关权限？");
                            noteDialog.setNoteDialogListener(new NoteDialog.NoteDialogListener() {
                                @Override
                                public void handleConfrm() {
                                    goSetting();
                                }

                                @Override
                                public void handleCancel() {
                                    ToastUtil.show(SplashActivity.this, "程序即将退出");
                                    handler.sendEmptyMessageDelayed(CustomArgs.EXIT, 3000);
                                }
                            });
                            noteDialog.show();
                        }
                    }
                } else {
                    NoteDialog noteDialog = new NoteDialog(SplashActivity.this);
                    noteDialog.setContent("没有获取手机信息权限程序将无法使用，是否设置？");
                    noteDialog.setNoteDialogListener(new NoteDialog.NoteDialogListener() {
                        @Override
                        public void handleConfrm() {
                            goSetting();
                        }

                        @Override
                        public void handleCancel() {
                            ToastUtil.show(SplashActivity.this, "程序即将退出");
                            handler.sendEmptyMessageDelayed(CustomArgs.EXIT, 3000);
                        }
                    });
                    noteDialog.show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}
