package com.orangelittle.u17.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.orangelittle.u17.R;
import com.orangelittle.u17.widget.DepthPageTransformer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by King on 2017/2/18
 */
public class GuideActivity extends Activity {
    private ViewPager viewPager;
    private LinearLayout layoutIcon;
    private Button buttonGo;
    private List<ImageView> icons = new ArrayList<ImageView>();
    private int[] images = {R.mipmap.guide_one,
            R.mipmap.guide_two, R.mipmap.guide_three,R.mipmap.guide_four};
    private int current;
    private int x, y, moveX, moveY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initView();
    }

    private void initView() {
        layoutIcon = (LinearLayout) findViewById(R.id.guide_layout_icon);
        buttonGo = (Button) findViewById(R.id.guide_button);
        buttonGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GuideActivity.this, LoginActiivty.class));
                finish();
                overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
            }
        });

        for (int i = 0; i < images.length; i++) {
            ImageView icon = new ImageView(GuideActivity.this);
            icon.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
            icon.setPadding(30, 0, 0, 0);
            icon.setImageResource(R.drawable.ic_gray_circle);
            icon.setScaleType(ImageView.ScaleType.CENTER);
            icons.add(icon);
            layoutIcon.addView(icon);
        }
        viewPager = (ViewPager) findViewById(R.id.guide_viewpager);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setPageTransformer(true,new DepthPageTransformer());
        GuidePagerAdapter adapter = new GuidePagerAdapter();
        viewPager.setAdapter(adapter);
        icons.get(0).setImageResource(R.drawable.ic_pink_circle);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                current = position;
                if (icons.size() > 0) {
                    for (int i = 0; i < icons.size(); i++) {
                        icons.get(i).setImageResource(R.drawable.ic_gray_circle);
                    }
                    icons.get(position).setImageResource(R.drawable.ic_pink_circle);
                }
                if(position == icons.size()-1){
                    buttonGo.setVisibility(View.VISIBLE);
                }else {
                    buttonGo.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    class GuidePagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return images.length;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            if (images.length > 0) {
                View view = LayoutInflater.from(GuideActivity.this).inflate(R.layout.guid_imageview, null);
                ImageView imageView = (ImageView) view.findViewById(R.id.guid_image);
                imageView.setImageResource(images[position]);
                container.addView(view);
                return view;
            } else {
                return null;
            }
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
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
