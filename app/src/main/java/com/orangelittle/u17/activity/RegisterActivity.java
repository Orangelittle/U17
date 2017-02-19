package com.orangelittle.u17.activity;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.orangelittle.u17.R;
import com.orangelittle.u17.util.StringUtil;
import com.orangelittle.u17.util.ToastUtil;
import com.orangelittle.u17.widget.MyEditText;

/**
 * Created by King on 2017/2/18
 */
public class RegisterActivity extends Activity implements View.OnClickListener {
    private MyEditText edPhone, edCode;
    private TextView btNext;
    private ImageView ivCancel, ivCode;
    private LinearLayout layoutGoLogin;
    private String vcodeKey;
    private boolean isUsed = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
           getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        initView();
        initListener();
//        getVcodeKey();
    }

    private void initListener() {
        ivCancel.setOnClickListener(this);
        btNext.setOnClickListener(this);
        layoutGoLogin.setOnClickListener(this);
        ivCode.setOnClickListener(this);

        edPhone.setTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 11) {
//                    checkPhone();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    protected void initView() {
        edPhone = (MyEditText) findViewById(R.id.register_phone);
        edCode = (MyEditText) findViewById(R.id.register_code);
        btNext = (TextView) findViewById(R.id.register_bt_submit);
        ivCancel = (ImageView) findViewById(R.id.register_iv_cancel);
        ivCode = (ImageView) findViewById(R.id.register_iv_code);
        layoutGoLogin = (LinearLayout) findViewById(R.id.register_layout_haveCount);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_iv_cancel:
                finish();
                break;
            case R.id.register_bt_submit:
                if (StringUtil.isNull(edPhone.getMyText())) {
                    ToastUtil.show(RegisterActivity.this, "输入手机号");
                } else if (!isUsed) {
                    ToastUtil.show(RegisterActivity.this, "该用户名已被使用");
                } else if (!StringUtil.isMobileNO(edPhone.getMyText())) {
                    ToastUtil.show(RegisterActivity.this, "手机号格式有误");
                } else if (StringUtil.isNull(edCode.getMyText())) {
                    ToastUtil.show(RegisterActivity.this, "输入图形验证码");
                } else {
//                    getPhoneCode();
                }
                break;
            case R.id.register_layout_haveCount:
                finish();
                break;
            case R.id.register_iv_code:
                edCode.setMText("");
//                getVcodeKey();
                break;
            default:
                break;
        }
    }

//    //获取图形验证码必须的key
//    private void getVcodeKey() {
//        NewHttpUtil.postHttpWithoutToken(RegisterActivity.this, HostUrl.GET_VCODEKEY, null, new NewHttpUtil.NewNetListener() {
//            @Override
//            public void successful(JSONObject object) throws IOException {
//                try {
//                    vcodeKey = object.getString("vcodeKey");
//                    if (vcodeKey != null) {
//                        //通过key拼接图片链接
//                        String url = HostUrl.GET_VCODE_IMAGE + "?width=100&height=50&vcodeKey=" + vcodeKey;
//                        Picasso.with(RegisterActivity.this).load(url).into(ivCode);
//                    } else {
//                        ToastUtil.show(RegisterActivity.this, "图片验证码获取失败");
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }
//
//    //获取短信验证码
//    private void getPhoneCode() {
//        Map<String, String> map = new HashMap<>();
//        map.put("uuid", BaseApplication.uuid);
//        map.put("mobile", edPhone.getMyText());
//        map.put("vcodeKey", vcodeKey);
//        map.put("code", edCode.getMyText());
//
//        NewHttpUtil.postHttpWithToken(RegisterActivity.this, HostUrl.GET_PHONE_CODE, map, new NewHttpUtil.NewNetWrongListener() {
//            @Override
//            public void successful(JSONObject object) throws IOException {
//                try {
//                    ToastUtil.show(RegisterActivity.this, "短信验证码已发送到您的手机");
//                    String msgKey = object.getString("temp_s");
//                    Intent intent = new Intent(RegisterActivity.this, RegisterNextActivity.class);
//                    intent.putExtra("msgKey", msgKey);
//                    intent.putExtra("mobile", edPhone.getMyText());
//                    startActivityForResult(intent, CustomArgs.RECODE_REGISTER);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void failure(String msg) throws IOException {
//                getVcodeKey();
//            }
//        });
//    }
//
//    private void checkPhone() {
//        Map<String, String> map = new HashMap<>();
//        map.put("uuid", BaseApplication.uuid);
//        map.put("mobile", edPhone.getMyText());
//        map.put("pam_user[account]", edPhone.getMyText());
//
//        NewHttpUtil.postHttp(RegisterActivity.this, HostUrl.CHECK_PHONE, map, new NewHttpUtil.NewNetWrongListener() {
//            @Override
//            public void successful(JSONObject object) throws IOException {
//                isUsed = true;
//            }
//
//            @Override
//            public void failure(String msg) throws IOException {
//                isUsed = false;
//            }
//        });
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == CustomArgs.RECODE_REGISTER && resultCode == Activity.RESULT_OK) {
//            finish();
//        }
//    }
}
