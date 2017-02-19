package com.orangelittle.u17.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.util.Pools;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;


import com.orangelittle.u17.activity.ChapterActivity;
import com.orangelittle.u17.R;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.orangelittle.u17.entry.Bout_GalleryItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/30.
 */

public class GalleryPagerAdapter extends PagerAdapter {
    private List<Bout_GalleryItem> items = new ArrayList<>();
    private Context mContext ;
    private Pools.Pool<SimpleDraweeView> pool = new Pools.SimplePool<>(4);

    public GalleryPagerAdapter(List<Bout_GalleryItem> items, Context mContext) {
        this.items.addAll(items);
        this.mContext = mContext;
        Bout_GalleryItem first = items.get(0);
        Bout_GalleryItem last = items.get(items.size() - 1);
        this.items.add(0,last);
        this.items.add(first);

    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(((View) object));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Bout_GalleryItem item = items.get(position);
        SimpleDraweeView draweeView = pool.acquire();
        if (draweeView == null) {
            draweeView = new SimpleDraweeView(mContext);
        }
        GenericDraweeHierarchyBuilder builder = new GenericDraweeHierarchyBuilder(mContext.getResources());
        GenericDraweeHierarchy hierarchy = builder.setPlaceholderImage(R.mipmap.bg_login_top)
                .setActualImageScaleType(ScalingUtils.ScaleType.FOCUS_CROP)
                .build();
        draweeView.setImageURI(item.getCover());
        draweeView.setHierarchy(hierarchy);
        container.addView(draweeView);
        return draweeView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(((View) object));
        pool.release((SimpleDraweeView) object);
    }

    public void click(View v ){
        int currentItem = ((ViewPager) v).getCurrentItem();
        Bout_GalleryItem item = items.get(currentItem);
        switch (item.getLinkType()) {
            case 1:

                break;
            case 3:
                Intent intent = new Intent(mContext, ChapterActivity.class);
                intent.putExtra("comicId",item.getExt().get(0).getVal());
                mContext.startActivity(intent);
                break;
        }

    }
}
