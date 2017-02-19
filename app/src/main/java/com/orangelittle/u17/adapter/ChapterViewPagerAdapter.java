package com.orangelittle.u17.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.util.Pools;
import android.support.v4.view.PagerAdapter;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.orangelittle.u17.R;
import com.orangelittle.u17.activity.ReadComicActivity;
import com.orangelittle.u17.entries.level1.DatailBean;
import com.orangelittle.u17.widget.FlowLayout;

import java.util.List;

/**
 * Created by Administrator on 2016/9/29.
 */

public class ChapterViewPagerAdapter extends PagerAdapter implements View.OnClickListener {
    private List<String> titleList;
    private Context context;
    private List<DatailBean.DataBean.ReturnDataBean.ChapterListBean> chapterListBean;
    private Pools.Pool<FlowLayout> pool = new Pools.SimplePool(4);
    private boolean isDesc;
    private String comicid;
    private int size;
    public ChapterViewPagerAdapter(List<String> titleList, Context context,List<DatailBean.DataBean.ReturnDataBean.ChapterListBean> chapterListBean,boolean isDesc,String comicid) {
        this.titleList = titleList;
        this.context = context;
        this.chapterListBean = chapterListBean;
        this.isDesc = isDesc;
        this.comicid = comicid;
        size = chapterListBean.size();
    }

    @Override
    public int getCount() {
        return titleList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        FlowLayout flowLayout = pool.acquire();
        if(flowLayout==null){
            flowLayout = (FlowLayout) LayoutInflater.from(context).inflate(R.layout.chapter_list_sub,container,false);
        }
        flowLayout.removeAllViews();
        int widthPixels = context.getResources().getDisplayMetrics().widthPixels;
        int heightPixels = (int) context.getResources().getDimension(R.dimen.guess_vpheight);
        int marginPixels = (int) context.getResources().getDimension(R.dimen.guess_margin);
        ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams((widthPixels-10*marginPixels)/5, (heightPixels-10*marginPixels)/3);
        lp.leftMargin = marginPixels;
        lp.rightMargin = marginPixels;
        lp.topMargin = marginPixels;
        lp.bottomMargin = marginPixels;
        if(isDesc) {
            setDescText(position, flowLayout, lp);
        }else{
            setAscText(position,flowLayout,lp);
        }
        container.addView(flowLayout);
        return flowLayout;
    }



    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        pool.release(((FlowLayout) object));
        container.removeView(((FlowLayout) object));
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }

    @Override
    public void onClick(View v) {
        int page = Integer.parseInt(((TextView) v).getText().toString());
        if(chapterListBean.get(page-1).getType().equals("3")){
            Toast.makeText(context, "小伙充个VIP吧", Toast.LENGTH_SHORT).show();
        }else {
            Intent intent = new Intent(context, ReadComicActivity.class);
            intent.putExtra("page", page);
            intent.putExtra("comicid", comicid);
            SharedPreferences read_history = context.getSharedPreferences(comicid, Context.MODE_PRIVATE);
            SharedPreferences.Editor edit = read_history.edit();
            edit.putInt("page", page);
            edit.putBoolean(comicid, true);
            edit.apply();
            context.startActivity(intent);
        }
    }

    private void setDescText(int position, FlowLayout flowLayout, ViewGroup.MarginLayoutParams lp) {
        if(position!=getCount()-1) {
            for(int i = 15*position+1; i <= 15 * (position + 1); i++) {
                addTextView(i,flowLayout,lp);
            }
        }else{
            for(int i = 15*position+1; i <= size; i++) {
                addTextView(i,flowLayout,lp);
            }
        }
    }

    private void setAscText(int position, FlowLayout flowLayout, ViewGroup.MarginLayoutParams lp) {
        if(position!=getCount()-1) {
            for(int i = size-position*15; i >= size-15*(position + 1)+1; i--) {
                addTextView(i,flowLayout,lp);
            }
        }else{
            for(int i = size-position*15; i >= 1; i--) {
                addTextView(i,flowLayout,lp);
            }
        }
    }
    private void addTextView(int i,FlowLayout flowLayout, ViewGroup.MarginLayoutParams lp){
        FrameLayout frameLayout = new FrameLayout(context);
        TextView textView = new TextView(context);
        textView.setText(i + "");
        textView.setGravity(Gravity.CENTER);

        textView.setBackgroundResource(R.drawable.chapter_list_shape);
        textView.setOnClickListener(this);
        frameLayout.addView(textView);
        frameLayout.setLayoutParams(lp);
        isNewOrVip(i-1, frameLayout);
        flowLayout.addView(frameLayout);
    }
    private void isNewOrVip(int i, FrameLayout frameLayout) {
        if (chapterListBean.get(i).getType().equals("3") && chapterListBean.get(i).getIs_new() == 0) {
            setVip(frameLayout);
        } else if (chapterListBean.get(i).getIs_new() == 1 && chapterListBean.get(i).getType().equals("0")) {
            setNew(frameLayout);
        } else if (chapterListBean.get(i).getType().equals("3") && chapterListBean.get(i).getIs_new() == 1) {
            setVip(frameLayout);
            setNew(frameLayout);
        }
    }

    private void setVip(FrameLayout frameLayout) {

        TextView left  = new TextView(context);
        left.setBackgroundResource(R.mipmap.detail_chapter_vip);
        left.setGravity(Gravity.LEFT);
        ViewGroup.LayoutParams layoutParams = new FrameLayout.LayoutParams(100, 100);
        layoutParams.width = 40;
        layoutParams.height = 40;
        left.setLayoutParams(layoutParams);
        frameLayout.addView(left);
    }

    private void setNew(FrameLayout frameLayout) {
        TextView right  = new TextView(context);
        right.setBackgroundResource(R.mipmap.comic_detail_chapter_new);
        right.setGravity(Gravity.RIGHT);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(100, 100);
        layoutParams.width = 40;
        layoutParams.height = 40;
        layoutParams.gravity=Gravity.RIGHT;
        right.setLayoutParams(layoutParams);
        frameLayout.addView(right);
    }
}
