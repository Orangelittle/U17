package com.orangelittle.u17.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.TextView;

import com.orangelittle.u17.R;
import com.orangelittle.u17.widget.MyEditText;

/**
 * Created by King on 2017/2/18
 */
public class RegisterNextActivity extends Activity {
    private String msgKey, phoneNum, license = "1";
    private MyEditText edCode, edPass, edConfirmPass;
    private TextView btRegister, tvLicense;
    private CheckBox checkAgree;
//    private UMShareAPI mShareAPI;
//    private SHARE_MEDIA platform;
//    private Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            if (msg.what == CustomArgs.ERROR) {
//                ToastUtil.show(RegisterNextActivity.this, "微信授权失败,注册失败");
//            }
//        }
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_next);
        setTitle("注册");

    }


    protected void initView() {
        edCode = (MyEditText) findViewById(R.id.register_next_code);
        edPass = (MyEditText) findViewById(R.id.register_next_pass);
        edConfirmPass = (MyEditText) findViewById(R.id.register_next_confirmpass);
        btRegister = (TextView) findViewById(R.id.register_next_bt_submit);
        checkAgree = (CheckBox) findViewById(R.id.register_next_checkBox);
        tvLicense = (TextView) findViewById(R.id.register_next_license);
    }

}
