package com.orangelittle.u17.widget;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by King on 2017/1/11.
 */

public class MyTabViewGroup extends LinearLayout implements ViewPager.OnPageChangeListener{

    private ViewPager mViewPager;

    public MyTabViewGroup(Context context) {
        super(context);
    }

    public MyTabViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTabViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

   public void setUpwithViewPager(ViewPager viewPager){
       this.mViewPager=viewPager;
       mViewPager.addOnPageChangeListener(this);

   }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        for (int i = 0; i < getChildCount(); i++) {

            final int position = i;
            getChildAt(i).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    setClickedView(position);
                    if (mViewPager != null) {
                        mViewPager.setCurrentItem(position,false);
                    }
                }
            });
        }
		getChildAt(0).setAlpha(1f);//默认设置第一个选中状态
    }


    //    http://blog.csdn.net/yilip/article/details/44345655
    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        try {
            super.onRestoreInstanceState(state);
        }catch (Exception e) {}
        state=null;
    }
    private void setClickedView(int position){
        for (int i = 0; i < getChildCount(); i++) {
            ((MyTabView) getChildAt(i)).setAlpha(0f);
        }
        ((MyTabView) getChildAt(position)).setAlpha(1f);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (positionOffset>0) {
            ((MyTabView) getChildAt(position)).setAlpha(1-positionOffset);
            ((MyTabView) getChildAt(position + 1)).setAlpha(positionOffset);
        }
    }

    @Override
    public void onPageSelected(int position) {

    }
    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
