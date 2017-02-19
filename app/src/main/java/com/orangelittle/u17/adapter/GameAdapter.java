package com.orangelittle.u17.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.orangelittle.u17.R;
import com.facebook.drawee.view.SimpleDraweeView;
import com.orangelittle.u17.entries.GameBean;
import com.orangelittle.u17.fragment.GameFragment;
import com.orangelittle.u17.widget.FlickerProgressBar;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.orangelittle.u17.R.id.downUrl;

/**
 * Created by Ice on 2016/9/29.
 */

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.ViewHolder> implements View.OnTouchListener, ViewPager.OnPageChangeListener {

    private final GameFragment gameFragment;
    private Context mContext;
    private LayoutInflater inflater;
    private List<Object> mList;
    private ViewPager mViewPager;
    private RecyclerView recyclerView;
    private List<View> dots = new ArrayList<>();

    public GameAdapter(Context Context, GameFragment gameFragment) {
        mContext = Context;
        inflater = LayoutInflater.from(Context);
        this.gameFragment = gameFragment;
    }

    public void setList(List<Object> list) {
        mList = list;
        notifyDataSetChanged();
    }

    public void addAll(List<Object> list) {
        mList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(viewType, parent, false));
    }

    public static final int NEXT = 100;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case NEXT:
                    mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
                    mHandler.sendEmptyMessageDelayed(NEXT, 1000);
                    break;
            }
        }
    };

    public void stopHandler() {
        mHandler.removeMessages(NEXT);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        switch (position) {
            case 0:
                List<GameBean.DataBean.ReturnDataBean.GameheaderBean.BannerBean> beanList = (List<GameBean.DataBean.ReturnDataBean.GameheaderBean.BannerBean>) mList.get(position);
                GamePagerAdapter adapter = new GamePagerAdapter(mContext, beanList);
                holder.mViewPager.setAdapter(adapter);
                holder.mViewPager.setCurrentItem(5000);
                holder.mViewPager.setOnPageChangeListener(this);
                setViewPager(holder.mViewPager);
                mHandler.removeMessages(NEXT);
                mHandler.sendEmptyMessageDelayed(NEXT, 1000);
                holder.mViewPager.setOnTouchListener(this);
                if (holder.dots.getChildCount() == 0) {
                    for (int i = 0; i < beanList.size(); i++) {
                        View view = new View(mContext);
                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(10, 10);
                        params.rightMargin = 10;
                        view.setLayoutParams(params);
                        view.setBackground(mContext.getResources().getDrawable(R.drawable.dot));
                        holder.dots.addView(view);
                        dots.add(view);
                    }
                }
                break;
            case 1:
                holder.mRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 3));
                holder.mRecyclerView.setAdapter(new GameHeaderAdapter(mContext, (List<GameBean.DataBean.ReturnDataBean.GameheaderBean.RecommandsBean>) mList.get(position)));
                break;
            default:
                GameBean.DataBean.ReturnDataBean.ItemListBean itemListBean = (GameBean.DataBean.ReturnDataBean.ItemListBean) mList.get(position);
                holder.mCoverUrlImg.setImageURI(itemListBean.getCoverUrl());
                holder.mTitleTv.setText(itemListBean.getTitle());
                float downLoadTimes = itemListBean.getDownloadTimes();
                if (downLoadTimes > 9999) {
                    holder.mDownloadTimesTv.setText(String.format("%.2f万次下载", downLoadTimes / 10000));
                } else {
                    holder.mDownloadTimesTv.setText(String.format("%.0f次下载", downLoadTimes));
                }
                holder.mSize.setText(String.format(Locale.CHINA, "%.2fMB", ((float) itemListBean.getSize()) / 1024 / 1024));
                holder.mGameTypeTv.setText(itemListBean.getGameType());
                holder.mDownUrl.setOnClickListener(itemListBean);
                holder.mDownUrl.initState(itemListBean.getDownUrl());
                holder.mCoverUrlImg.setOnClickListener(itemListBean);
                if (position == mList.size() - 1) {
                    gameFragment.getData();
                }
        }
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return R.layout.game_item_viewpager;
            case 1:
                return R.layout.game_item_sixpic;
            default:
                return R.layout.game_item_h;
        }
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public void setViewPager(ViewPager viewPager) {
        this.mViewPager = viewPager;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                mHandler.removeMessages(NEXT);
                break;
            case MotionEvent.ACTION_UP:
                mHandler.sendEmptyMessageDelayed(NEXT, 0);
        }
        return false;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    int lastDot;
    @Override
    public void onPageSelected(int position) {
        if (dots.size()!=0){
            dots.get(lastDot).setBackground(mContext.getResources().getDrawable(R.drawable.dot));
            dots.get(position%4).setBackground(mContext.getResources().getDrawable(R.drawable.dot_nor));
        }
        lastDot = position%4;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final FlickerProgressBar mDownUrl;
        private final LinearLayout dots;
        private TextView mSize;
        private ViewPager mViewPager;
        private RecyclerView mRecyclerView;
        private SimpleDraweeView mCoverUrlImg;
        private TextView mTitleTv;
        private TextView mDownloadTimesTv;
        private TextView mGameTypeTv;
        private RelativeLayout mRelativeLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            if (itemView instanceof RelativeLayout) {
                //如果是横向的下载布局
                mRelativeLayout = (RelativeLayout) itemView;
            }
            mCoverUrlImg = ((SimpleDraweeView) itemView.findViewById(R.id.coverUrl));
            mTitleTv = (TextView) itemView.findViewById(R.id.title);
            mDownloadTimesTv = (TextView) itemView.findViewById(R.id.downloadTimes);
            mSize = ((TextView) itemView.findViewById(R.id.size));
            mGameTypeTv = (TextView) itemView.findViewById(R.id.gameType);
            mRecyclerView = ((RecyclerView) itemView.findViewById(R.id.sixpic_rv));
            mViewPager = ((ViewPager) itemView.findViewById(R.id.game_vp));
            mDownUrl = (FlickerProgressBar) itemView.findViewById(R.id.downUrl);
            dots = ((LinearLayout) itemView.findViewById(R.id.dots));
        }
    }
}
