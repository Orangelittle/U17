package com.orangelittle.u17.fragment;


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.orangelittle.u17.R;
import com.orangelittle.u17.adapter.RecAdapter;
import com.orangelittle.u17.entries.RecommendBean;
import com.orangelittle.u17.other_activity.Classify_Activity;
import com.orangelittle.u17.util.ClassType;
import com.orangelittle.u17.util.NetUtils;
import com.orangelittle.u17.util.PullToRefresh;
import com.handmark.pulltorefresh.library.PullToRefreshBase;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecommendFragment extends Fragment implements Callback<RecommendBean>, View.OnClickListener {

    private PullToRefresh rec_ptr;
    private RecAdapter recAdapter;
    private int pager = 1;
    private ImageView rec_search;
    private boolean isHasMore;
    private boolean isLoadingMore=false;
    private LinearLayoutManager mLayoutManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recommend, container, false);
    }


    private boolean isExpand;
    private ImageView search_btn;
    private ImageView searchchange_btn;
    private EditText search_editext;
    //设置缩放X轴锚点 search_editext宽度位置
    private double serchWid;
    private static final int HIDE_THRESHOLD = 20;
    private int scrolledDistance = 0;

    @Override
    public void onViewCreated(View view, @Nullable final Bundle savedInstanceState) {
        rec_ptr = ((PullToRefresh) view.findViewById(R.id.rec_PullToRefresh));
        final RecyclerView refreshableView = rec_ptr.getRefreshableView();
        recAdapter = new RecAdapter(getContext(),refreshableView);
        mLayoutManager = new LinearLayoutManager(getContext());
        refreshableView.setLayoutManager(mLayoutManager);
        refreshableView.setAdapter(recAdapter);
        NetUtils.getData(ClassType.RecommendBean, this, pager);


        search_btn = (ImageView) view.findViewById(R.id.search_btn);
        searchchange_btn = (ImageView) view.findViewById(R.id.searchchanged_btn);
        search_editext = (EditText) view.findViewById(R.id.search_Editext);
        isExpand = true;
        search_editext.setOnClickListener(this);
        search_btn.setOnClickListener(this);
        searchchange_btn.setOnClickListener(this);


//        rec_ptr.setScrollingWhileRefreshingEnabled(true);
        refreshableView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(final RecyclerView recyclerView, int dx, int dy) {
                Log.d("FFF",dy+"");
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItem = ((LinearLayoutManager) mLayoutManager).findLastVisibleItemPosition();
                int totalItemCount = mLayoutManager.getItemCount();
                //lastVisibleItem >= totalItemCount - 4 表示剩下4个item自动加载，各位自由选择
                // dy>0 表示向下滑动
                if (lastVisibleItem >= totalItemCount - 5 && dy > 0) {
                    if(isLoadingMore&&isHasMore){

                    } else{
                        NetUtils.getData(ClassType.RecommendBean, new Callback<RecommendBean>() {
                            @Override
                            public void onResponse(Call<RecommendBean> call, Response<RecommendBean> response) {
                                RecommendBean.DataBean.ReturnDataBean returnData = response.body().getData().getReturnData();
                                isHasMore = returnData.isHasMore();
                                recAdapter.addAll(returnData.getDataList());
                                isLoadingMore = false;
                            }

                            @Override
                            public void onFailure(Call<RecommendBean> call, Throwable t) {
                                Snackbar.make(recyclerView, "数据加载失败", Snackbar.LENGTH_SHORT);
                            }
                        }, ++pager);
                        isLoadingMore = true;
                    }
                }

//                int firstPosition = mLayoutManager.findFirstCompletelyVisibleItemPosition();
//                private void hideViews() {
//                    mToolbar.animate().translationY(-mToolbar.getHeight()).setInterpolator(new AccelerateInterpolator(2));
//
//                    FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) mFabButton.getLayoutParams();
//                    int fabBottomMargin = lp.bottomMargin;
//                    mFabButton.animate().translationY(mFabButton.getHeight()+fabBottomMargin).setInterpolator(new AccelerateInterpolator(2)).start();
//                }
//
//                private void showViews() {
//                    mToolbar.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2));
//                    mFabButton.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2)).start();
//                }


                if (scrolledDistance>HIDE_THRESHOLD && isExpand) {
                    updateShow(false);
                    isExpand = false;
                    scrolledDistance=0;
                }
                if (scrolledDistance <-HIDE_THRESHOLD && !isExpand) {
                    updateShow(true);
                    isExpand = true;
                    scrolledDistance=0;
                }
                if((isExpand && dy>0) || (!isExpand && dy<0)) {
                    scrolledDistance += dy;
                }

            }
        });
        rec_ptr.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<RecyclerView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<RecyclerView> pullToRefreshBase) {
//                ILoadingLayout loadingLayoutProxy = rec_ptr.getLoadingLayoutProxy(true, false);
//                loadingLayoutProxy.setPullLabel("有妖气！！！");
//                loadingLayoutProxy.setLoadingDrawable(getContext().getResources().getDrawable(R.mipmap.u17_refresh_eye_nictation2));

                rec_ptr.setRefreshing(true);
                recAdapter.removeAll();
                NetUtils.getData(ClassType.RecommendBean, RecommendFragment.this, 1);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<RecyclerView> pullToRefreshBase) {
                rec_ptr.onRefreshComplete();
//                ILoadingLayout loadingLayoutProxy = rec_ptr.getLoadingLayoutProxy(false, true);
//                loadingLayoutProxy.setRefreshingLabel(null);
//                loadingLayoutProxy.setPullLabel(null);
//                loadingLayoutProxy.setLoadingDrawable(null);
//                if (isHasMore) {
//                    rec_ptr.setRefreshing(true);
//                    NetUtils.getData(ClassType.RecommendBean, RecommendFragment.this, ++pager);
//                } else {
//                    rec_ptr.onRefreshComplete();
//                    loadingLayoutProxy.setPullLabel("队长大人！！到底啦！！！");
//                    loadingLayoutProxy.setLoadingDrawable(getContext().getResources().getDrawable(R.mipmap.main_reminder_bottom));
//                }
            }
        });
    }

    @Override
    public void onResponse(Call<RecommendBean> call, Response<RecommendBean> response) {
        RecommendBean.DataBean.ReturnDataBean returnData = response.body().getData().getReturnData();
        isHasMore = returnData.isHasMore();
        recAdapter.addAll(returnData.getDataList());
        rec_ptr.onRefreshComplete();
    }

    @Override
    public void onFailure(Call<RecommendBean> call, Throwable t) {
        Snackbar.make(rec_ptr, "获取数据失败", Snackbar.LENGTH_SHORT).show();
        rec_ptr.onRefreshComplete();
    }

    @Override
    public void onClick(View v) {
        // TODO: 2016/9/30  跳转页面
        Intent intent=new Intent(getContext(), Classify_Activity.class);
        startActivity(intent);
    }


    private float scale;
    public void updateShow(boolean isExpand) {
        serchWid = search_editext.getWidth()+searchchange_btn.getWidth();

        double wid = searchchange_btn.getWidth() ;
        double fenshu = wid / serchWid;
        scale = (float) fenshu;
        if (isExpand) {
            expandSearch();
        } else {
            closeSearch();
        }
    }

    private void expandSearch() {

        searchchange_btn.setVisibility(View.INVISIBLE);
        ObjectAnimator anim1 = ObjectAnimator.ofFloat(searchchange_btn, "alpha", 1f, 0f);
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(search_editext, "alpha", 0f, 1f);
        ObjectAnimator anim3 = ObjectAnimator.ofFloat(search_editext, "scaleX", scale, 1f);
        ObjectAnimator anim4 = ObjectAnimator.ofFloat(search_btn, "alpha", 0f, 1f);

        ObjectAnimator anim7 = ObjectAnimator.ofFloat(searchchange_btn, "scaleX", 1f, 0f);
        ObjectAnimator anim8 = ObjectAnimator.ofFloat(searchchange_btn, "scaleY", 1f, 0f);
        //设置缩放X轴锚点
        search_editext.setPivotX((float) serchWid);
        AnimatorSet animSet2= new AnimatorSet();
        animSet2.play(anim1).with(anim2).with(anim3).with(anim4).with(anim7).with(anim8);
        animSet2.setDuration(800);
        animSet2.start();

    }

    private void closeSearch() {
        searchchange_btn.setVisibility(View.VISIBLE);
        ObjectAnimator anim1 = ObjectAnimator.ofFloat(search_btn, "alpha", 1f, 0f);
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(search_editext, "scaleX", 1f, scale);
        ObjectAnimator anim3 = ObjectAnimator.ofFloat(searchchange_btn, "alpha", 0f, 1f);
        ObjectAnimator anim4 = ObjectAnimator.ofFloat(search_editext, "alpha", 1f, 0f);

        ObjectAnimator anim7 = ObjectAnimator.ofFloat(searchchange_btn, "scaleX", 0f, 1f);
        ObjectAnimator anim8 = ObjectAnimator.ofFloat(searchchange_btn, "scaleY", 0f, 1f);
        search_editext.setPivotX((float) serchWid);
        search_editext.setPivotY(search_editext.getHeight() / 2);

        AnimatorSet animSet1 = new AnimatorSet();
        animSet1.play(anim1).with(anim2).with(anim3).with(anim4).with(anim7).with(anim8);
        animSet1.setDuration(800);
        animSet1.start();
    }
}
