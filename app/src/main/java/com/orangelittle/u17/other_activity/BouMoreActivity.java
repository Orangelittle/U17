package com.orangelittle.u17.other_activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.util.Pair;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.orangelittle.u17.activity.BaseActivity;
import com.orangelittle.u17.R;
import com.orangelittle.u17.BR;

import com.orangelittle.u17.adapter.Comic_RecycleAdapter;
import com.orangelittle.u17.entries.level1.RankListBean;
import com.orangelittle.u17.util.ClassType;
import com.orangelittle.u17.util.NetUtils;
import com.orangelittle.u17.util.PullToRefresh;
import com.handmark.pulltorefresh.library.PullToRefreshBase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


@RequiresApi(api = Build.VERSION_CODES.M)
public class BouMoreActivity extends BaseActivity implements Callback<RankListBean> {
    private Toolbar toolBar;
    private TextView title;
    private LinearLayout lin;
    private TextView update;
    private int height;
    private int left;
    private PopupWindow popupWindow;
    private int pager = 1;
    private ViewDataBinding binding;
    private Comic_RecycleAdapter<RankListBean.DataBean.ReturnDataBean.ComicsBean> adapter;
    private RecyclerView recycle;
    private boolean isOpen = false;
    private int argValue;
    private String argName;
    private boolean hasMore;
    private boolean isHasMore;
    private boolean isLoading = false;
    private PullToRefresh pullToRefresh;
    private LinearLayoutManager linearLayoutManager;
    private ProgressBar progressbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         binding = DataBindingUtil.setContentView(this, R.layout.activity_bou_more);

        Intent intent = getIntent();
        String titles = intent.getStringExtra("title");
        title = (TextView) findViewById(R.id.more_title);
        progressbar = (ProgressBar) findViewById(R.id.more_progressbar);
        title.setText(titles);
        lin = (LinearLayout) findViewById(R.id.more_lin);
        update = (TextView) findViewById(R.id.more_update);
          pullToRefresh = (PullToRefresh) findViewById(R.id.boumore_ptr);
        recycle = pullToRefresh.getRefreshableView();

         argValue =Integer.parseInt(intent.getStringExtra("argValue").trim());

         argName = intent.getStringExtra("argName");

        NetUtils.getData(ClassType.RankListBean, this, argValue, argName, pager);
        Map<Class<?>, Pair<Integer, Integer>> map = new HashMap<>();
        map.put(RankListBean.DataBean.ReturnDataBean.ComicsBean.class,new Pair(R.layout.more_recycler_item, BR.moreComicsBean));
        ArrayList<RankListBean.DataBean.ReturnDataBean.ComicsBean> comics = new ArrayList<>();
        adapter = new Comic_RecycleAdapter<RankListBean.DataBean.ReturnDataBean.ComicsBean>(this,comics,map);
        binding.setVariable(BR.moreAdapter, adapter);
         linearLayoutManager = new LinearLayoutManager(this);
        recycle.setLayoutManager(linearLayoutManager);
        recycle.setAdapter(adapter);
        recycle.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int position = linearLayoutManager.findLastVisibleItemPosition();
                if (position==adapter.getSize()-5 && !isLoading && isHasMore) {
                    isLoading =true;
                    NetUtils.getData(ClassType.RankListBean, BouMoreActivity.this, argValue, argName, ++pager);

                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }
        });


        recycle.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                super.onDraw(c, parent, state);
                c.drawColor(Color.GRAY);
            }

            @Override
            public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
                super.onDrawOver(c, parent, state);
            }

            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.set(0,0,0,1);
            }
        });

        pullToRefresh.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<RecyclerView>() {
            @Override
            public void onRefresh(PullToRefreshBase<RecyclerView> pullToRefreshBase) {
                pullToRefresh.setRefreshing(true);
                adapter.removeAll();
                NetUtils.getData(ClassType.RankListBean,BouMoreActivity.this,argValue, argName, pager);
            }

//            @Override
//            public void onPullDownToRefresh(PullToRefreshBase<RecyclerView> pullToRefreshBase) {
//
//            }

//            @Override
//            public void onPullUpToRefresh(PullToRefreshBase<RecyclerView> pullToRefreshBase) {
//
// //               ILoadingLayout loadingLayoutProxy = rec_ptr.getLoadingLayoutProxy(false, true);
////                loadingLayoutProxy.setRefreshingLabel(null);
////                loadingLayoutProxy.setPullLabel(null);
////                loadingLayoutProxy.setLoadingDrawable(null);
////                if (isHasMore) {
////                    pullToRefresh.setRefreshing(true);
////                    NetUtils.getData(ClassType.RecommendBean, BouMoreActivity.this,argValue, argName, ++pager);
////                } else {
////                    pullToRefresh.onRefreshComplete();
//
////                    loadingLayoutProxy.setPullLabel("队长大人！！到底啦！！！");
////                    loadingLayoutProxy.setLoadingDrawable(getContext().getResources().getDrawable(R.mipmap.main_reminder_bottom));
//
//            }
        });
    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.more_back:
                finish();
                break;
            case R.id.more_update:
                height = lin.getMeasuredHeight();
                left = lin.getRight();
//                PopupMenu popupMenu = new PopupMenu(this, update );
//                popupMenu.getMenuInflater().inflate(R.menu.popumenu,popupMenu.getMenu());


                if (popupWindow == null) {
                    View inflate = LayoutInflater.from(this).inflate(R.layout.dialog_menu, null);
                    popupWindow = new PopupWindow(inflate, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    popupWindow.setOutsideTouchable(true);
                    popupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.chapter_list_shape));
                }
                if (!isOpen) {
                    popupWindow.showAtLocation(lin, Gravity.TOP,left , height+50);
                    isOpen = !isOpen;
                } else {
                    popupWindow.dismiss();
                    isOpen = !isOpen;
                }
                    break;

        }
    }

    @Override
    public void onResponse(Call<RankListBean> call, Response<RankListBean> response) {
         isHasMore = response.body().getData().getReturnData().isHasMore();
        List<RankListBean.DataBean.ReturnDataBean.ComicsBean> comics = response.body().getData().getReturnData().getComics();
        adapter.addAll(comics);
        pullToRefresh.onRefreshComplete();
        isLoading = false;
        progressbar.setVisibility(View.GONE);
    }

    @Override
    public void onFailure(Call<RankListBean> call, Throwable t) {

    }
}
