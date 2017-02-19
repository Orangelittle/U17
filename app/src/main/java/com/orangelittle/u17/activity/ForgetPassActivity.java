package com.orangelittle.u17.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.orangelittle.u17.R;
import com.orangelittle.u17.widget.MyEditText;

/**
 * Created by King on 2017/2/18
 */
public class ForgetPassActivity extends Activity {
    private MyEditText edPhone, edCode;
    private TextView tvNext;
    private ImageView ivCode;
    private String vcodeKey;
    private boolean isUsed = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pass);
    }


//    protected void initView() {
//        edPhone = (MyEditText) findViewById(R.id.forget_phone);
//        edCode = (MyEditText) findViewById(R.id.forget_code);
//        ivCode = (ImageView) findViewById(R.id.forget_iv_code);
//        tvNext = (TextView) findViewById(R.id.forget_bt_submit);
//    }
//
//    @Override
//    protected void initData() {
//        getVcodeKey();
//    }
//
//
//    protected void initListener() {
//        ivCode.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                edCode.setMText("");
//                getVcodeKey();
//            }
//        });
//        tvNext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (StringUtil.isNull(edPhone.getMyText())) {
//                    ToastUtil.show(mContext, "请输入手机号");
//                } else if (!isUsed) {
//                    ToastUtil.show(mContext, "该手机号未注册");
//                } else if (StringUtil.isNull(edCode.getMyText())) {
//                    ToastUtil.show(mContext, "请输入图形验证码");
//                } else {
//                    getPhoneCode();
//                }
//            }
//        });
//
//        edPhone.setTextChangeListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if (s.length() == 11) {
//                    checkPhone();
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
//    }
//
//    private void checkPhone() {
//        Map<String, String> map = new HashMap<>();
//        map.put("uuid", BaseApplication.uuid);
//        map.put("mobile", edPhone.getMyText());
//        map.put("pam_user[account]", edPhone.getMyText());
//        map.put("is_forget", "1");
//
//        NewHttpUtil.postHttp(mContext, HostUrl.CHECK_PHONE, map, new NewHttpUtil.NewNetWrongListener() {
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
//    //获取短信验证码
//    private void getPhoneCode() {
//        Map<String, String> map = new HashMap<>();
//        map.put("uuid", BaseApplication.uuid);
//        map.put("mobile", edPhone.getMyText());
//        map.put("vcodeKey", vcodeKey);
//        map.put("code", edCode.getMyText());
//
//        NewHttpUtil.postHttpWithToken(mContext, HostUrl.GET_PHONE_CODE, map, new NewHttpUtil.NewNetWrongListener() {
//            @Override
//            public void successful(JSONObject object) throws IOException {
//                try {
//                    ToastUtil.show(mContext, "短信验证码已发送到您的手机");
//                    String msgKey = object.getString("temp_s");
//                    Intent intent = new Intent(mContext, ForgetPassNextActivity.class);
//                    intent.putExtra("msgKey", msgKey);
//                    intent.putExtra("mobile", edPhone.getMyText());
//                    startActivityForResult(intent, CustomArgs.RECODE_FORGETPASS);
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
//    private void getVcodeKey() {
//        NewHttpUtil.postHttp(ForgetPassActivity.this, HostUrl.GET_VCODEKEY, null, new NewHttpUtil.NewNetListener() {
//            @Override
//            public void successful(JSONObject object) throws IOException {
//                try {
//                    vcodeKey = object.getString("vcodeKey");
//                    if (vcodeKey != null) {
//                        String url = HostUrl.GET_VCODE_IMAGE + "?width=100&height=50&vcodeKey=" + vcodeKey;
//                        Picasso.with(ForgetPassActivity.this).load(url).into(ivCode);
//                    } else {
//                        ToastUtil.show(ForgetPassActivity.this, "图片验证码获取失败");
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == CustomArgs.RECODE_FORGETPASS && resultCode == Activity.RESULT_OK) {
//            finish();
//        }
//    }
}
