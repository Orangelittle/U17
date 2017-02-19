package com.orangelittle.u17.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.orangelittle.u17.R;
import com.orangelittle.u17.widget.MyEditText;

/**
 *Created by King on 2017/2/18
 */
public class ForgetPassNextActivity extends Activity{
    private String msgKey,phoneNum;
    private TextView tvPhoneNum,btConfirm;
    private MyEditText edCode,edNewPass,edConfirmPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_next);
        setTitle("找回密码");
    }


    protected void initView() {
        tvPhoneNum = (TextView) findViewById(R.id.forget_confirm_phone);
        edCode = (MyEditText) findViewById(R.id.forget_confirm_code);
        edNewPass = (MyEditText) findViewById(R.id.forget_confirm_new_pass);
        edConfirmPass = (MyEditText) findViewById(R.id.forget_confirm_re_newpass);
        btConfirm = (TextView) findViewById(R.id.forget_confirm_bt_submit);
    }


//    protected void initData() {
//        msgKey = getIntent().getStringExtra("msgKey");
//        phoneNum = getIntent().getStringExtra("mobile");
//        tvPhoneNum.setText("手机号："+phoneNum);
//    }
//
//
//    protected void initListener() {
//        btConfirm.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(StringUtil.isNull(edCode.getMyText())){
//                    ToastUtil.show(mContext,"请输入短信验证码");
//                }else if(StringUtil.isNull(edNewPass.getMyText())){
//                    ToastUtil.show(mContext,"请输入新密码");
//                }else if(StringUtil.isNull(edConfirmPass.getMyText())){
//                    ToastUtil.show(mContext,"请再次确认新密码");
//                }else if(!edConfirmPass.getMyText().equals(edNewPass.getMyText())){
//                    ToastUtil.show(mContext,"两次密码不一致");
//                }else {
//                    upDatePass();
//                }
//            }
//        });
//    }
//
//    private void upDatePass(){
//        Map<String,String> map = new HashMap<>();
//        map.put("uuid", BaseApplication.uuid);
//        map.put("mobile",phoneNum);
//        map.put("vcode",edCode.getMyText());
//        map.put("temp_s",msgKey);
//        map.put("new_pwd",edNewPass.getMyText());
//        map.put("confirm_pwd",edConfirmPass.getMyText());
//
//        NewHttpUtil.postHttpWithoutToken(mContext, HostUrl.FIND_PASS, map, new NewHttpUtil.NewNetListener() {
//            @Override
//            public void successful(JSONObject object) throws IOException {
//                ToastUtil.show(mContext,"密码重置成功");
//                setResult(Activity.RESULT_OK);
//                finish();
//            }
//        });
//    }

}
