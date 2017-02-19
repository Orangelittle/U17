package com.orangelittle.u17.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.orangelittle.u17.R;
import com.facebook.drawee.view.SimpleDraweeView;
import com.orangelittle.u17.entries.GameBean;

import java.util.List;

/**
 * Created by Ice on 2016/9/30.
 */

public class GamePagerAdapter extends PagerAdapter{

    private final Context mContext;
    private List<GameBean.DataBean.ReturnDataBean.GameheaderBean.BannerBean> mList;

    public GamePagerAdapter(Context context,List<GameBean.DataBean.ReturnDataBean.GameheaderBean.BannerBean> beanList) {
        mList = beanList;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mList==null?0:10000;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        SimpleDraweeView imageView = new SimpleDraweeView(mContext);
        GameBean.DataBean.ReturnDataBean.GameheaderBean.BannerBean bannerBean = mList.get(getRealPos(position));
        imageView.setImageURI(bannerBean.getCoverUrl());
        imageView.getHierarchy().setPlaceholderImage(R.mipmap.main_recycler_image_default);
        container.addView(imageView);
        imageView.setOnClickListener(bannerBean);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    public int getRealPos(int position){
        return position % mList.size();
    }
}
