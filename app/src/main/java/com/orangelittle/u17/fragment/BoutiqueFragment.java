package com.orangelittle.u17.fragment;


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.orangelittle.u17.R;
import com.orangelittle.u17.BR;
import com.orangelittle.u17.activity.RankActivity;
import com.orangelittle.u17.activity.SpecialTopicActivity;
import com.orangelittle.u17.adapter.Bou_RecyclerAdapter;
import com.orangelittle.u17.entries.BoutiqueBean;
import com.orangelittle.u17.entry.BouItemSecond;
import com.orangelittle.u17.entry.Bou_ComicList_Item;
import com.orangelittle.u17.entry.Bou_ReturnData;
import com.orangelittle.u17.interfaces.IOnFocusListenable;
import com.orangelittle.u17.other_activity.Animation_Activity;
import com.orangelittle.u17.other_activity.Classify_Activity;
import com.orangelittle.u17.util.ClassType;
import com.orangelittle.u17.util.FastBlur;
import com.orangelittle.u17.util.NetUtils;
import com.orangelittle.u17.util.PullToRefresh;
import com.orangelittle.u17.util.RecyclerViewDisabler;
import com.orangelittle.u17.widget.CircleMenu;
import com.orangelittle.u17.widget.OnMenuSelectedListener;
import com.orangelittle.u17.widget.OnMenuStatusChangeListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.databinding.tool.util.GenerationalClassUtil.ExtensionFilter.BR;

/**
 * A simple {@link Fragment} subclass.
 */
public class BoutiqueFragment extends Fragment implements Callback<BoutiqueBean>,IOnFocusListenable {


    private ViewDataBinding binding;
    private PullToRefresh ptr;
    private RecyclerView recycle;
    private Bou_RecyclerAdapter bou_recyclerAdapter;
    private ProgressBar progressbar;
    private CircleMenu circlefab;
    private  TextView  tv;

    public BoutiqueFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         binding = DataBindingUtil.inflate(inflater, R.layout.fragment_boutique, container, false);
        return binding.getRoot();
    }



    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
    }

    @Override
    public void onViewCreated(View view,  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final HashMap<Class<?>, Pair<Integer, Integer>> map = new HashMap<>();
        map.put(Bou_ComicList_Item.class,new Pair<Integer, Integer>(R.layout.bou_item_comic, com.orangelittle.u17.BR.comicListItem));
        map.put(Bou_ReturnData.class,new Pair<Integer, Integer>(R.layout.bou_galleryitem, com.orangelittle.u17.BR.galleryAdapter));
        map.put(BouItemSecond.class,new Pair<Integer, Integer>(R.layout.bou_item_second, com.orangelittle.u17.BR.second));
         ptr = (PullToRefresh) binding.getRoot().findViewById(R.id.bou_PullToRefresh);
        progressbar = ((ProgressBar) binding.getRoot().findViewById(R.id.bou_progressbar));
        recycle = ptr.getRefreshableView();
        recycle.setLayoutManager(new LinearLayoutManager(getContext()));
          bou_recyclerAdapter = new Bou_RecyclerAdapter(getContext(), new ArrayList<>(), map);
        binding.setVariable( com.orangelittle.u17.BR.bouAdapter, bou_recyclerAdapter);
        recycle.setAdapter(bou_recyclerAdapter);

        NetUtils.getData(ClassType.BoutiqueBean, this);

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
        ptr.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<RecyclerView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<RecyclerView> pullToRefreshBase) {
//                ILoadingLayout loadingLayoutProxy = rec_ptr.getLoadingLayoutProxy(true, false);
//                loadingLayoutProxy.setPullLabel("有妖气！！！");
//                loadingLayoutProxy.setLoadingDrawable(getContext().getResources().getDrawable(R.mipmap.u17_refresh_eye_nictation2));
                ptr.setRefreshing(true);
                bou_recyclerAdapter.removeAll();
                NetUtils.getData(ClassType.BoutiqueBean,BoutiqueFragment.this);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<RecyclerView> pullToRefreshBase) {
//                ILoadingLayout loadingLayoutProxy = rec_ptr.getLoadingLayoutProxy(false, true);
//                loadingLayoutProxy.setRefreshingLabel(null);
//                loadingLayoutProxy.setPullLabel(null);
//                loadingLayoutProxy.setLoadingDrawable(null);
//                if (isHasMore) {
//                    rec_ptr.setRefreshing(true);
//                    NetUtils.getData(ClassType.RecommendBean, RecommendFragment.this, ++pager);

                    ptr.onRefreshComplete();
//                    loadingLayoutProxy.setPullLabel("队长大人！！到底啦！！！");
//                    loadingLayoutProxy.setLoadingDrawable(getContext().getResources().getDrawable(R.mipmap.main_reminder_bottom));
                }
            }
        );

        mRecyclerViewDisabler=new RecyclerViewDisabler();
        recycle.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
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

        /***
         * 第一种动画菜单展开方式
         */
//        fab = ((FloatingActionButton) binding.getRoot().findViewById(R.id.fab));
//        tv = ((TextView) binding.getRoot().findViewById(R.id.cloud));
//        fab.setOnClickListener(this);
//        tv1 = (TextView) binding.getRoot().findViewById(R.id.tv1);
//        tv2 = (TextView) binding.getRoot().findViewById(tv2);
//        tv3 = (TextView) binding.getRoot().findViewById(tv3);
//        tv4 = (TextView) binding.getRoot().findViewById(tv4);
//        tv1.setOnClickListener(this);
//        tv2.setOnClickListener(this);
//        tv3.setOnClickListener(this);
//        tv4.setOnClickListener(this);
//        mlist.add(tv1);mlist.add(tv2);
//        mlist.add(tv3);mlist.add(tv4);
//        closeMenu();
        /**
         * 第二种动画菜单展开方式
         */

        final Handler handler =new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg != null) {

                    switch (msg.arg1) {
                        case 0:
                            jumpOtherActivity(RankActivity.class,getContext());
                            break;
                         case 1:
                             jumpOtherActivity(SpecialTopicActivity.class, getContext());
                            break;
                        case 2:
                            jumpOtherActivity(Classify_Activity.class, getContext());
                            break;
                        case 3:
                            jumpOtherActivity(Animation_Activity.class,getContext());
                            break;
                    }
                }
            }
        } ;
        circlefab = ((CircleMenu) binding.getRoot().findViewById(R.id.fab));
        tv = ((TextView) binding.getRoot().findViewById(R.id.cloud));
        circlefab.setMainMenu(Color.parseColor("#D34E1D"), R.mipmap.icon_menu, R.mipmap.icon_cancel)
                .addSubMenu(Color.parseColor("#FBBF36"), R.mipmap.rankings)
                .addSubMenu(Color.parseColor("#848BF7"), R.mipmap.specials)
                .addSubMenu(Color.parseColor("#64B1E2"), R.mipmap.classifys)
                .addSubMenu(Color.parseColor("#87D335"), R.mipmap.animations)
//                .addSubMenu(Color.parseColor("#FF6A00"), R.mipmap.icon_gps)
                .setOnMenuSelectedListener(new OnMenuSelectedListener() {

                    @Override
                    public void onMenuSelected(int index) {
                        Message message=new Message();
                        switch (index) {
                            case 0:
                                message.arg1=0;
                                handler.sendMessageDelayed(message,800);
//                                jumpOtherActivity(RankActivity.class, getContext());
                                break;
                            case 1:
                                message.arg1=1;
                                handler.sendMessageDelayed(message,800);

                                break;
                            case 2:
                                message.arg1=2;
                                handler.sendMessageDelayed(message,800);

                                break;
                            case 3:
                                message.arg1=3;
                                handler.sendMessageDelayed(message,800);
                                break;
                        }

                    }

                }).setOnMenuStatusChangeListener(new OnMenuStatusChangeListener() {

            @Override
            public void onMenuOpened() {
                tv.setVisibility(View.VISIBLE);
                recycle.addOnItemTouchListener(mRecyclerViewDisabler);
            }

            @Override
            public void onMenuClosed() {
                tv.setVisibility(View.GONE);
                recycle.removeOnItemTouchListener(mRecyclerViewDisabler);
            }

        });
    }

    private boolean isExpand;
    /***
     * 第一种菜单展开方式
     */
//    private boolean fabOpened=true;
//    private FloatingActionButton fab;
//    private TextView tv;
//    private TextView tv1;
//    private TextView tv2;
//    private TextView tv3;
//    private TextView tv4;
//    private List<TextView> mlist=new ArrayList<>();
    private static final int HIDE_THRESHOLD = 20;
    private int scrolledDistance = 0;
        //拦截事件
    private RecyclerViewDisabler mRecyclerViewDisabler=null;



    @Override
    public void onResponse(Call<BoutiqueBean> call, Response<BoutiqueBean> response) {
        Bou_ReturnData returnData = response.body().getData().getReturnData();
        bou_recyclerAdapter.add(0,returnData);
        bou_recyclerAdapter.add(1,new BouItemSecond());
        List<Bou_ComicList_Item> comicLists = returnData.getComicLists();
        for (int i = 0; i < comicLists.size(); i++) {
            if (comicLists.get(i).getComics().size()==0) {
                comicLists.remove(i);
            }
        }
        bou_recyclerAdapter.addAll(comicLists);
        ptr.onRefreshComplete();
        progressbar.setVisibility(View.GONE);
    }

    @Override
    public void onFailure(Call<BoutiqueBean> call, Throwable t) {
    }


    private void updateShow(boolean isExpand) {
        if (isExpand) {
            openFloating();
        } else {
            closeFloating();
        }
    }

    private void openFloating(){
        ObjectAnimator anim1 = ObjectAnimator.ofFloat(circlefab, "scaleX", 0f, 1f);
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(circlefab, "scaleY", 0f, 1f);
        AnimatorSet set=new AnimatorSet();
        set.play(anim1).with(anim2);
        set.setDuration(500);
        set.start();

    }
    private void closeFloating(){
        ObjectAnimator anim1 = ObjectAnimator.ofFloat(circlefab, "scaleX", 1f, 0f);
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(circlefab, "scaleY", 1f, 0f);
        AnimatorSet set=new AnimatorSet();
        set.play(anim1).with(anim2);
        set.setDuration(500);
        set.start();
    }
    /***
     * 第一种方式动画 FloatingActionButton展开菜单 缩放等
     * @param
     */

    private Intent intent = null;
    public void jumpOtherActivity(Class clazz , Context context){
        intent = new Intent(context, clazz);
        context.startActivity(intent);
    }
//    private void openMenu(){
//        ObjectAnimator animator=ObjectAnimator.ofFloat(fab,"rotation",0,-155,-135);
//        animator.setDuration(500);
//        animator.start();
//        tv.setVisibility(View.VISIBLE);
//        setTxtsVis(View.VISIBLE);
//        for (int i = 0; i <mlist.size(); i++) {
//            ObjectAnimator anim1 = ObjectAnimator.ofFloat(mlist.get(i), "scaleX", 0, 1).setDuration(300);
//            ObjectAnimator anim2 = ObjectAnimator.ofFloat(mlist.get(i), "scaleY", 0, 1).setDuration(300);
//            anim1.start();
//            anim2.start();
//        }
//        fabOpened=true;
//        recycle.addOnItemTouchListener(mRecyclerViewDisabler);
//    }
//
//    private void closeMenu(){
//        ObjectAnimator animator=ObjectAnimator.ofFloat(fab,"rotation",-135,20,0);
//        animator.setDuration(500);
//        animator.start();
//        tv.setVisibility(View.GONE);
//
//        for (int i = 0; i <mlist.size(); i++) {
//            ObjectAnimator anim1 = ObjectAnimator.ofFloat(mlist.get(i), "scaleX", 1, 0).setDuration(300);
//            ObjectAnimator anim2 = ObjectAnimator.ofFloat(mlist.get(i), "scaleY", 1, 0).setDuration(300);
//            anim1.start();
//            anim2.start();
//        }
//        fabOpened=false;
//        recycle.removeOnItemTouchListener(mRecyclerViewDisabler);
//    }
//
//    private void setTxtsVis(int visible) {
//        tv1.setVisibility(visible);
//        tv2.setVisibility(visible);
//        tv3.setVisibility(visible);
//        tv4.setVisibility(visible);
//    }
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.fab:
//                if (!fabOpened) {
//                    openMenu();
//                }else{
//                    closeMenu();
//                }
//                break;
//            case R.id.tv1:
//                jumpOtherActivity(RankActivity.class,getContext());
//                closeMenu();
//
//                break;
//            case tv2:
//jumpOtherActivity(SpecialTopicActivity.class, getContext());
//                closeMenu();
//
//                break;
//            case tv3:
//                jumpOtherActivity(Classify_Activity.class,getContext());
//                closeMenu();
//                break;
//            case tv4:
//
//                break;
//        }
//
//    }

    private void applyBlur() {
        View view = getActivity().getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache(true);
        /**
         * 获取当前窗口快照，相当于截屏
         */
        Bitmap bmp1 = view.getDrawingCache();
        int height = getOtherHeight();
        /**
         * 除去状态栏和标题栏
         */
        Bitmap bmp2 = Bitmap.createBitmap(bmp1, 0, height,bmp1.getWidth(), bmp1.getHeight() - height);
        blur(bmp2, tv);
    }

    @SuppressLint("NewApi")
    private void blur(Bitmap bkg, View view) {
        long startMs = System.currentTimeMillis();
        float scaleFactor = 8;//图片缩放比例；
        float radius = 15;//模糊程度

        Bitmap overlay = Bitmap.createBitmap(
                (int) (view.getMeasuredWidth() / scaleFactor),
                (int) (view.getMeasuredHeight() / scaleFactor),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(overlay);
        canvas.translate(-view.getLeft() / scaleFactor, -view.getTop()/ scaleFactor);
        canvas.scale(1 / scaleFactor, 1 / scaleFactor);
        Paint paint = new Paint();
        paint.setFlags(Paint.FILTER_BITMAP_FLAG);
        canvas.drawBitmap(bkg, 0, 0, paint);


        overlay = FastBlur.doBlur(overlay, (int) radius, true);
        view.setBackground(new BitmapDrawable(getResources(), overlay));
        /**
         * 打印高斯模糊处理时间，如果时间大约16ms，用户就能感到到卡顿，时间越长卡顿越明显，如果对模糊完图片要求不高，可是将scaleFactor设置大一些。
         */
        Log.i("jerome", "blur time:" + (System.currentTimeMillis() - startMs));
    }

    /**
     * 获取系统状态栏和软件标题栏，部分软件没有标题栏，看自己软件的配置；
     * @return
     */
    private int getOtherHeight() {
        Rect frame = new Rect();
        getActivity().getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;
        int contentTop = getActivity().getWindow().findViewById(Window.ID_ANDROID_CONTENT).getTop();
        int titleBarHeight = contentTop - statusBarHeight;
        return statusBarHeight + titleBarHeight;
    }



}
