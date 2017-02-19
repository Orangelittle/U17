package com.orangelittle.u17.util;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.handmark.pulltorefresh.library.PullToRefreshBase;

/**
 * Created by Administrator on 2016/9/29.
 */

public class PullToRefresh extends PullToRefreshBase<RecyclerView> {

    public PullToRefresh(Context context) {
        super(context);
    }

    public PullToRefresh(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PullToRefresh(Context context, Mode mode) {
        super(context, mode);
    }

    public PullToRefresh(Context context, Mode mode, AnimationStyle animStyle) {
        super(context, mode, animStyle);
    }

    @Override
    public Orientation getPullToRefreshScrollDirection() {
        return Orientation.VERTICAL;
    }

    @Override
    protected RecyclerView createRefreshableView(Context context, AttributeSet attributeSet) {
        RecyclerView view = new RecyclerView(context, attributeSet);
        return view;
    }

    @Override
    protected boolean isReadyForPullEnd() {
        View view = getRefreshableView().getChildAt(getRefreshableView().getChildCount() - 1);
        if (null != view) {
            return getRefreshableView().getBottom() >= view.getBottom();
        }
        return false;
    }

    @Override
    protected boolean isReadyForPullStart() {
        View view = getRefreshableView().getChildAt(0);
        if (view != null) {
            return view.getTop() >= getRefreshableView().getTop();
        }
        return false;
    }
}
