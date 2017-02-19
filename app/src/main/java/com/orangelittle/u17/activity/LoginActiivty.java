package com.orangelittle.u17.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.orangelittle.u17.R;
import com.orangelittle.u17.util.StringUtil;
import com.orangelittle.u17.util.ToastUtil;
import com.orangelittle.u17.widget.MyEditText;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by King on 2017/2/18
 */
public class LoginActiivty extends Activity {
    private ImageView WeChatLogin;
    private LoginReceiver receiver;
//    private UMShareAPI mShareAPI;
//    private SHARE_MEDIA platform;

    private MyEditText edPhone, edPassword;
    private TextView tvRegister, tvForgetPass, btLogin, tvTest;
//    private Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            if (msg.what == CustomArgs.ERROR) {
//                ToastUtil.show(LoginActiivty.this, "微信授权失败");
//            }
//        }
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        CustomUtil.initTitleBar(LoginActiivty.this);
        setContentView(R.layout.activity_login);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
           getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        Log.e("LoginActiivty", "-------------onCreate");
//        mShareAPI = UMShareAPI.get(LoginActiivty.this);
        receiver = new LoginReceiver();
        IntentFilter filter = new IntentFilter();
//        filter.addAction(CustomArgs.LOGIN_OK);
        registerReceiver(receiver, filter);
//        platform = SHARE_MEDIA.WEIXIN;
        initView();
        initListener();
    }

    protected void initView() {
//        String token = ValueUtil.getValue(BaseApplication.TOKEN);
//        String shopId = ValueUtil.getValue(BaseApplication.SHOPID);
//        if (!StringUtil.isNull(token) && !StringUtil.isNull(shopId)) {
//            Intent intent = new Intent(this, HomeActivity.class);
//            startActivity(intent);
//            finish();
//        } else if (!StringUtil.isNull(token) && StringUtil.isNull(shopId)) {
//            Intent intent = new Intent(this, NearbyShopActivity.class);
//            intent.putExtra("from", "login");
//            startActivity(intent);
//            finish();
//        }

        btLogin = (TextView) findViewById(R.id.login_bt_login);
        WeChatLogin = (ImageView) findViewById(R.id.login_WeChat_login);
        edPhone = (MyEditText) findViewById(R.id.login_phone);
        edPassword = (MyEditText) findViewById(R.id.login_password);
        tvRegister = (TextView) findViewById(R.id.login_tv_register);
        tvForgetPass = (TextView) findViewById(R.id.login_tv_forgetPass);

    }

    protected void initListener() {
        btLogin.setOnClickListener(listener);
        WeChatLogin.setOnClickListener(listener);
        tvRegister.setOnClickListener(listener);
        tvForgetPass.setOnClickListener(listener);
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.login_bt_login:
//                    if (StringUtil.isNull(edPhone.getMyText())) {
//                        ToastUtil.show(LoginActiivty.this, "请输入手机号");
//                    } else if (!StringUtil.isMobileNO(edPhone.getMyText())) {
//                        ToastUtil.show(LoginActiivty.this, "手机号格式错误");
//                    } else if (StringUtil.isNull(edPassword.getMyText())) {
//                        ToastUtil.show(LoginActiivty.this, "请输入密码");
//                    } else {
//                        goLogin();
//                    }
                    startActivity(new Intent(LoginActiivty.this, MainActivity.class));
                    finish();
                    overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);

                    break;
                case R.id.login_WeChat_login:
//                    LoadDialog.show(LoginActiivty.this);
//                    if (mShareAPI.isInstall(LoginActiivty.this, platform)) {
//                        mShareAPI.doOauthVerify(LoginActiivty.this, platform, umAuthListener);
//                    }
                    break;
                case R.id.login_tv_register:
                    startActivity(new Intent(LoginActiivty.this, RegisterActivity.class));
                    overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
                    break;
                case R.id.login_tv_forgetPass:
                    startActivity(new Intent(LoginActiivty.this, ForgetPassActivity.class));
                    overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
                    break;
                default:
                    break;
            }
        }
    };

//    private void goLogin() {
//        Map<String, String> map = new HashMap<>();
//        map.put("uuid", ValueUtil.getValue(BaseApplication.UUID));
//        map.put("account", edPhone.getMyText());
//        map.put("password", edPassword.getMyText());
//
//        NewHttpUtil.postHttp(LoginActiivty.this, HostUrl.LOGIN_IN, map, new NewHttpUtil.NewNetListener() {
//            @Override
//            public void successful(org.json.JSONObject object) throws IOException {
//                try {
//                    String token;
//                    try {
//                        token = object.getString("securitytoken");
//                    } catch (JSONException e) {
//                        token = null;
//                    }
//                    Log.e("Login", "uuid -------" + ValueUtil.getValue(BaseApplication.UUID));
//                    if (!StringUtil.isNull(token)) {
//                        ValueUtil.putValue(BaseApplication.TOKEN, token);
//                        Log.e("Login", "securitytoken -------" + token);
//                        String shopId;
//                        try {
//                            shopId = object.getString("shop_id");
//                        } catch (JSONException e) {
//                            shopId = null;
//                        }
//
//                        int isBind;
//                        try {
//                            isBind = object.getInt("is_band");
//                        } catch (JSONException e) {
//                            isBind = 0;
//                        }
//                        if (isBind == 0) {
//                            ValueUtil.putValue(BaseApplication.ISBIND, "0");
//                            BaseApplication.isBind = false;
//                        } else if (isBind == 1) {
//                            ValueUtil.putValue(BaseApplication.ISBIND, "1");
//                            BaseApplication.isBind = true;
//                        }
//
//                        String userId = object.getString("user_id");
//                        if (!StringUtil.isNull(userId)) {
//                            ValueUtil.putValue(BaseApplication.USERID, userId);
////                            ToastUtil.show(LoginActiivty.this, "userId-----" + ValueUtil.getValue(BaseApplication.USERID));
//                        } else {
//                            ToastUtil.show(LoginActiivty.this, "userId获取失败");
//                        }
//
//                        String loginType;
//                        try {
//                            loginType = object.getString("loginType");
//                        } catch (JSONException e) {
//                            loginType = null;
//                        }
//                        if (!StringUtil.isNull(loginType)) {
//                            ValueUtil.putValue(BaseApplication.LOGIN_TYPE, loginType);
//                        } else {
//                            ValueUtil.putValue(BaseApplication.LOGIN_TYPE, "loginType");
//                        }
//                        if (!StringUtil.isNull(shopId)) {
//                            ValueUtil.putValue(BaseApplication.SHOPID, shopId);
//                            Intent intent = new Intent(LoginActiivty.this, HomeActivity.class);
//                            startActivity(intent);
//                        } else {
//                            Intent intent = new Intent(LoginActiivty.this, NearbyShopActivity.class);
//                            intent.putExtra("from", "login");
//                            startActivity(intent);
//                        }
//                        finish();
//                    } else {
//                        ToastUtil.show(LoginActiivty.this, "登录异常");
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        mShareAPI.onActivityResult(requestCode, resultCode, data);
        Log.e("LoginActivity", "LoginActivity-----------onActivityResult");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.e("LoginActivity", "LoginActivity-----------onKeyDown");
        if (KeyEvent.KEYCODE_BACK == keyCode) {
            exitBy2Click();
        }
        return false;
    }

    private class LoginReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.e("LoginActivity", "LoginActivity-----------onReceive");
            LoginActiivty.this.finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
        Log.e("LoginActivity", "LoginActivity-----------onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
//        LoadDialog.close();
    }

    //授权回调
//    private UMAuthListener umAuthListener = new UMAuthListener() {
//        @Override
//        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
//            LoadDialog.close();
//            Log.e("LoginActivity", "LoginActivity-----------授权回调成功");
//            Log.e("LoginActivity", "LoginActivity-----------data：" + data);
//            Log.e("LoginActivity", "LoginActivity-----------access_token：" + data.get("access_token"));
//            Log.e("LoginActivity", "LoginActivity-----------openid：" + data.get("openid"));
//            Log.e("LoginActivity", "LoginActivity-----------unionid：" + data.get("unionid"));
//
//            if (data != null) {
//                String access_token = data.get("access_token");
//                String openid = data.get("openid");
//                String unionid = data.get("unionid");
//                if (!StringUtil.isNull(access_token) && !StringUtil.isNull(access_token) && !StringUtil.isNull(access_token)) {
//                    weChatSignIn(access_token, openid, unionid);
//                } else {
//                    handler.sendEmptyMessage(CustomArgs.ERROR);
//                }
//            }
////            mShareAPI.getPlatformInfo(LoginActiivty.this, platform, getINFoListener);
//        }
//
//        @Override
//        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
//            LoadDialog.close();
//            Log.e("LoginActivity", "LoginActivity-----------授权回调失败");
//        }
//
//        @Override
//        public void onCancel(SHARE_MEDIA platform, int action) {
//            LoadDialog.close();
//            Log.e("LoginActivity", "LoginActivity-----------授权回调取消");
//        }
//    };

    //获取用户信息回调
//    private UMAuthListener getINFoListener = new UMAuthListener() {
//        @Override
//        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
//            Log.e("LoginActivity", "LoginActivity-----------获取用户信息回调成功");
//            Log.e("LoginActivity", "LoginActivity-----------data" + data);
//        }
//
//        @Override
//        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
//            Log.e("LoginActivity", "LoginActivity-----------获取用户信息回调失败");
//        }
//
//        @Override
//        public void onCancel(SHARE_MEDIA platform, int action) {
//            Log.e("LoginActivity", "LoginActivity-----------获取用户信息回调取消");
//        }
//    };

//    private void weChatSignIn(final String token, final String openid, String unionid) {
//        Map<String, String> map = new HashMap<>();
//        map.put("uuid", ValueUtil.getValue(BaseApplication.UUID));
//        map.put("access_token", token);
//        map.put("openid", openid);
//        map.put("unionid", unionid);
//
//        NewHttpUtil.postHttp(LoginActiivty.this, HostUrl.WECHART_SIGN_IN, map, new NewHttpUtil.NewNetListener() {
//            @Override
//            public void successful(org.json.JSONObject object) throws IOException {
//                String token;
//                try {
//                    token = object.getString("securitytoken");
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                    token = null;
//                }
//                if (token != null) {
//                    ValueUtil.putValue(BaseApplication.TOKEN, token);
//                    Log.e("WeChat", "securitytoken -------" + token);
//                    String shopId;
//                    try {
//                        shopId = object.getString("shop_id");
//                    } catch (JSONException e) {
//                        shopId = null;
//                    }
//                    ValueUtil.putValue(BaseApplication.SHOPID, shopId);
//                    Log.e("WeChat", "shopid -------" + shopId);
//
//                    String userId = null;
//                    try {
//                        userId = object.getString("user_id");
//                    } catch (JSONException e) {
//                        userId = null;
//                    }
//                    if (!StringUtil.isNull(userId)) {
//                        ValueUtil.putValue(BaseApplication.USERID, userId);
////                        ToastUtil.show(LoginActiivty.this, "userId-----" + userId);
//                    } else {
//                        ToastUtil.show(LoginActiivty.this, "userId获取失败");
//                    }
//
//                    int isBind;
//                    try {
//                        isBind = object.getInt("is_band");
//                    } catch (JSONException e) {
//                        isBind = 0;
//                    }
//                    if (isBind == 0) {
//                        ValueUtil.putValue(BaseApplication.ISBIND, "0");
//                        BaseApplication.isBind = false;
//                    } else if (isBind == 1) {
//                        ValueUtil.putValue(BaseApplication.ISBIND, "1");
//                        BaseApplication.isBind = true;
//                    }
//
//                    if (StringUtil.isNull(shopId)) {
//                        Intent intent = new Intent(LoginActiivty.this, NearbyShopActivity.class);
//                        intent.putExtra("from", "login");
//                        startActivity(intent);
//                    } else {
//                        Intent intent = new Intent(LoginActiivty.this, HomeActivity.class);
//                        startActivity(intent);
//                    }
//                    finish();
//                }
//            }
//        });
//    }

    /**
     * 双击退出函数
     */
    private static Boolean isExit = false;

    private void exitBy2Click() {
        Timer tExit = null;
        if (isExit == false) {
            isExit = true; // 准备退出
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务
        } else {
            finish();
            System.exit(0);
        }
    }
}
