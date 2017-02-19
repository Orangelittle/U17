package com.orangelittle.u17.util;

import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;

/**
 * Created by Administrator on 2017/1/16 0016.
 * 拦截菜单弹出时 recyclerview的触摸事件
 */

public class RecyclerViewDisabler implements RecyclerView.OnItemTouchListener {
    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

        return  true;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
