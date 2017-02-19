package com.orangelittle.u17.activity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.view.Gravity;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.orangelittle.u17.R;
import com.orangelittle.u17.adapter.ReadComicAdapter;
import com.orangelittle.u17.entries.level1.DanmuBean;
import com.orangelittle.u17.entries.level1.ReadComicBean;
import com.orangelittle.u17.util.ClassType;
import com.orangelittle.u17.util.NetUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReadComicActivity extends BaseActivity implements Callback<ReadComicBean>,Handler.Callback, View.OnClickListener {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private int showPosition;
    private List<ReadComicBean.DataBean.ReturnDataBean> returnDataBeanList;
    private List<ReadComicBean.DataBean.ReturnDataBean.ImageListBean> imageListBeen;
    private List<DanmuBean.DataBean.ReturnDataBean> danmuBeanList;
    private List<Integer> positionList;
    private String comicid;
    private int chapterPage;
    private int position;
    private FrameLayout framlayout;

    private Handler handler = new Handler(this);
    private LinearLayout tucao_anim;
    private ImageView tucao_switch;
    private ImageView tucao_publication;
    private ImageView tucao_background;
    private ImageView tucao_tag;

    private boolean isOpen;
    private boolean isExtend;
    private boolean isCanTranslate = true;
    private float heightPixels;
    private TextView tucao_count;
    private int lastLine = -1;
    private int currentLine;
    private Random random = new Random(System.currentTimeMillis());
    private FrameLayout progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_comic);
        Intent intent = getIntent();
        comicid = intent.getStringExtra("comicid");
        chapterPage = intent.getIntExtra("page",-1);
        if(chapterPage == -1) {
            position = intent.getIntExtra("showposition", -1);
        }
        recyclerView = ((RecyclerView) findViewById(R.id.read_recycler));
        framlayout = ((FrameLayout) findViewById(R.id.activity_read_comic));
        tucao_anim = ((LinearLayout) findViewById(R.id.tucao_anim));
        tucao_switch = ((ImageView) findViewById(R.id.tucao_switch));
        tucao_publication = ((ImageView) findViewById(R.id.tucao_publication));
        tucao_background = ((ImageView) findViewById(R.id.tucao_background));
        tucao_tag = ((ImageView) findViewById(R.id.tucao_tag));
        tucao_count = ((TextView) findViewById(R.id.tucao_count));
        progressBar = ((FrameLayout) findViewById(R.id.progressBar));
        tucao_background.setOnClickListener(this);
        tucao_publication.setOnClickListener(this);
        tucao_switch.setOnClickListener(this);
        linearLayoutManager = new LinearLayoutManager(this);
        returnDataBeanList = new ArrayList<>();
        imageListBeen = new ArrayList<>();
        positionList = new ArrayList<>();
        heightPixels = getResources().getDisplayMetrics().heightPixels;
        NetUtils.getData(ClassType.ReadComicBean,this, comicid);
    }

    @Override
    public void onResponse(Call<ReadComicBean> call, Response<ReadComicBean> response) {
        progressBar.setVisibility(View.GONE);
        returnDataBeanList = response.body().getData().getReturnData();
        for (ReadComicBean.DataBean.ReturnDataBean returnDataBean : returnDataBeanList) {
            imageListBeen.addAll(returnDataBean.getImage_list());
            for (int i = 1; i <= returnDataBean.getImage_list().size(); i++) {
                positionList.add(i);
            }
        }
        if(chapterPage != -1) {
            for (int i = 0; i < chapterPage - 1; i++) {
                showPosition += returnDataBeanList.get(i).getImage_list().size();
            }
        }else{
            showPosition = position;
        }
        linearLayoutManager.scrollToPosition(showPosition);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new ReadComicAdapter(imageListBeen,this,positionList,comicid));
        getDanmuData(imageListBeen.get(showPosition).getImage_id());
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    getDanmu();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                super.onDraw(c, parent, state);
                c.drawColor(Color.BLACK);
            }

            @Override
            public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
                super.onDrawOver(c, parent, state);
            }

            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.set(0,10,0,10);
            }
        });
    }

    @Override
    public void onFailure(Call<ReadComicBean> call, Throwable t) {
        Toast.makeText(this, "数据请求失败,正在重新请求", Toast.LENGTH_SHORT).show();
        NetUtils.getData(ClassType.ReadComicBean,this, comicid);
    }

    public void getDanmu() {
        int lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
        View childAt = linearLayoutManager.getChildAt(linearLayoutManager.getChildCount()-1);
        int top = childAt.getTop();
        if(top<heightPixels/2){
            if(showPosition != lastVisibleItemPosition) {
                String image_id = imageListBeen.get(lastVisibleItemPosition).getImage_id();
                handler.removeMessages(1);
                framlayout.removeAllViews();
                getDanmuData(image_id);
                showPosition = lastVisibleItemPosition;
            }
        }else{
            if(showPosition != lastVisibleItemPosition-1) {
                String image_id = imageListBeen.get(lastVisibleItemPosition - 1).getImage_id();
                handler.removeMessages(1);
                framlayout.removeAllViews();
                getDanmuData(image_id);
                showPosition = lastVisibleItemPosition-1;
            }
        }

    }

    private void getDanmuData(String image_id) {
        NetUtils.getData(ClassType.DanmuBean, new Callback<DanmuBean>() {
            @Override
            public void onResponse(Call<DanmuBean> call, Response<DanmuBean> response) {
                danmuBeanList = response.body().getData().getReturnData();
                if(danmuBeanList.size()>99) {
                    tucao_count.setText("99+");
                }else{
                    tucao_count.setText(danmuBeanList.size() + "");
                }
                if (isOpen) {
                    handler.sendEmptyMessage(1);
                }
            }
            @Override
            public void onFailure(Call<DanmuBean> call, Throwable t) {
                Toast.makeText(ReadComicActivity.this, "数据请求失败", Toast.LENGTH_SHORT).show();
            }
        },image_id,comicid);
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case 1:
                generateItem();
                int duration = (int) ((2000 - 1000) * Math.random());
                handler.sendEmptyMessageDelayed(1, duration);
                break;
        }
        return true;
    }

    @NonNull
    private void generateItem() {
        final TextView textView = new TextView(this);
        String text = danmuBeanList.get((int) (Math.random() * danmuBeanList.size())).getContent();
        textView.setText(text);
        textView.setTextSize(25);
        textView.setTextColor(Color.WHITE);
        textView.setMaxLines(1);
        textView.setBackgroundResource(R.drawable.danmu_shape);
        textView.setPadding(5,5,5,5);
        float lineHeight = 80 ;
        float totalLine = (heightPixels / getLineHeight(textView, text, 20)) * 2;
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        if (danmuBeanList.size() < 10) {
            currentLine = random.nextInt(5)+1;
            while(currentLine==lastLine) {
                currentLine = random.nextInt(5)+1;
            }
        }else{
            currentLine = random.nextInt(10)+1;
            while(currentLine==lastLine) {
                currentLine = random.nextInt(10)+1;
            }
        }

        layoutParams.gravity = Gravity.RIGHT;
        layoutParams.topMargin = (int) (currentLine *lineHeight);
        textView.setLayoutParams(layoutParams);
        framlayout.addView(textView);
        ObjectAnimator translateAnimator = ObjectAnimator.ofFloat(textView, "translationX", getTextWidth(textView, text, 20), -framlayout.getWidth());
        translateAnimator.setDuration((long) ((getTextWidth(textView, text, 20)/framlayout.getWidth()+1)*4000));
        translateAnimator.start();
        translateAnimator.setInterpolator(new LinearInterpolator());
        translateAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                textView.clearAnimation();
                framlayout.removeView(textView);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        lastLine = currentLine;
    }

    public float getTextWidth(TextView textView, String text, int textSize){
        TextPaint paint = textView.getPaint();
        float scaledDensity = this.getResources().getDisplayMetrics().scaledDensity;
        paint.setTextSize(scaledDensity * textSize);
        return paint.measureText(text);
    }
    private int getLineHeight(TextView textView, String text, int textSize) {
        Rect bounds = new Rect();
        TextPaint paint;
        paint = textView.getPaint();
        paint.getTextBounds(text, 0, text.length(), bounds);
        return bounds.height();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeMessages(1);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tucao_background:
                if(isExtend && isCanTranslate){
                    isCanTranslate = false;
                    tucao_publication.setClickable(false);
                    tucao_switch.setClickable(false);
                    ObjectAnimator animation = ObjectAnimator.ofFloat(tucao_anim,"translationY",-tucao_anim.getHeight()*2/3,tucao_publication.getHeight()/2);
                    translateAnim(animation,isExtend);
                }else if(!isExtend && isCanTranslate){
                    isCanTranslate = false;
                    tucao_anim.setVisibility(View.VISIBLE);
                    tucao_publication.setClickable(true);
                    tucao_switch.setClickable(true);
                    ObjectAnimator animation = ObjectAnimator.ofFloat(tucao_anim,"translationY",tucao_publication.getHeight()/2,-tucao_anim.getHeight()*2/3);
                    translateAnim(animation,isExtend);
                }
                isExtend = !isExtend;
                break;
            case R.id.tucao_switch:
                isCanTranslate = false;
                if (isOpen) {
                    tucao_switch.setBackgroundResource(R.mipmap.icon_turn_on);
                    tucao_tag.setVisibility(View.VISIBLE);
                    framlayout.removeAllViews();
                    handler.removeMessages(1);
                }else{
                    tucao_switch.setBackgroundResource(R.mipmap.icon_turn_off);
                    tucao_tag.setVisibility(View.INVISIBLE);
                    if(danmuBeanList!=null) {
                        handler.sendEmptyMessage(1);
                    }
                }
                ObjectAnimator animation = ObjectAnimator.ofFloat(tucao_anim,"translationY",-tucao_anim.getHeight()*2/3,tucao_publication.getHeight()/2);
                translateAnim(animation,true);
                isOpen=!isOpen;
                isExtend = false;
                break;
            case R.id.tucao_publication:

                break;
        }
    }

    private void translateAnim(ObjectAnimator animation, final boolean isExtend) {
        animation.setDuration(300);
        animation.start();
        animation.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if(isExtend){
                    tucao_anim.setVisibility(View.INVISIBLE);
                }
                isCanTranslate = true;
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

}
