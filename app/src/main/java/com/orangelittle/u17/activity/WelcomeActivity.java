package com.orangelittle.u17.activity;

import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.orangelittle.u17.R;
import com.orangelittle.u17.entry.WelcomeBean;
import com.orangelittle.u17.util.ClassType;
import com.orangelittle.u17.util.NetUtils;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WelcomeActivity extends AppCompatActivity implements Callback<WelcomeBean> {

    private SimpleDraweeView simpleDraweeView;
    private ControllerListener controllerListener;
    private TextView djs;
    public static final int NEXT =101;
    private int current = 3;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==NEXT) {
                current = current-1;
                if (current>=0){
                    djs.setText(current+"");
                    handler.sendEmptyMessageDelayed(NEXT,1000);
                }else {
                    finish();
                    startActivity(new Intent(WelcomeActivity.this,MainActivity.class));
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        djs = ((TextView) findViewById(R.id.djs));
        simpleDraweeView = (SimpleDraweeView) findViewById(R.id.welcome_iv);
        NetUtils.getData(ClassType.WelcomeBean, this);
    }

    @Override
    public void onResponse(Call<WelcomeBean> call, Response<WelcomeBean> response) {
        WelcomeBean.DataBean data = response.body().getData();
        if (data.getStateCode()==-1){
            //加载失败
            djs.setVisibility(View.VISIBLE);
            handler.sendEmptyMessageDelayed(NEXT,1000);
        }else {
            String image_url = data.getReturnData().getStartAd().getImage_url();
            Uri uri = Uri.parse(image_url);
            simpleDraweeView.setImageURI(image_url);
            ControllerListener controllerListener = new BaseControllerListener<ImageInfo>() {
                @Override
                public void onFinalImageSet(
                        String id,
                        @Nullable ImageInfo imageInfo,
                        @Nullable Animatable anim) {
                    djs.setVisibility(View.VISIBLE);
                    handler.sendEmptyMessageDelayed(NEXT,1000);
                }

                @Override
                public void onIntermediateImageSet(String id, @Nullable ImageInfo imageInfo) {
                }

                @Override
                public void onFailure(String id, Throwable throwable) {
                    djs.setVisibility(View.VISIBLE);
                    handler.sendEmptyMessageDelayed(NEXT,1000);
                }
            };
            DraweeController controller = Fresco.newDraweeControllerBuilder()
                    .setControllerListener(controllerListener)
                    .setUri(uri)
                    .build();
            simpleDraweeView.setController(controller);
        }
    }

    @Override
    public void onFailure(Call<WelcomeBean> call, Throwable t) {

    }
}
