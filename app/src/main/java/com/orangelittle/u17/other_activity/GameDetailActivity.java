package com.orangelittle.u17.other_activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.orangelittle.u17.activity.BaseActivity;
import com.orangelittle.u17.R;
import com.orangelittle.u17.adapter.GameDetailAdapter;
import com.orangelittle.u17.entries.level1.GameDetailBean;
import com.orangelittle.u17.fragment.GameFragment;
import com.orangelittle.u17.util.ClassType;
import com.orangelittle.u17.util.NetUtils;
import com.orangelittle.u17.widget.FlickerProgressBar;
import com.facebook.drawee.view.SimpleDraweeView;
import com.kogitune.activity_transition.ActivityTransition;

import java.util.Locale;

import retrofit2.Callback;
import retrofit2.Response;

import static com.orangelittle.u17.R.id.coverUrl;


public class GameDetailActivity extends BaseActivity implements View.OnClickListener, Callback<GameDetailBean> {

    private SimpleDraweeView mCoverUrl;
    private TextView mTitle;
    private TextView mDownloadTimes;
    private TextView mGameType;
    private TextView mSize;
    private TextView mDesc;
    private int appId;
    private ImageView back;
    private RecyclerView mRecyclerView;
    private GameDetailAdapter mAdapter;
    private FlickerProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_detail_item);
        initView();
        appId = getIntent().getExtras().getInt("appId");
//        getGameDetailBean(appId);
        NetUtils.getData(ClassType.GameDetailBean,this,appId);
        ActivityTransition.with(getIntent()).to(findViewById(R.id.coverUrl)).start(savedInstanceState);
    }

    private void fillView(GameDetailBean gameDetailBean) {
        GameDetailBean.DataBean.ReturnDataBean.AppBean appBean = gameDetailBean.getData().getReturnData().getApp();
        mCoverUrl.setImageURI(appBean.getCoverUrl());
//        ViewCompat.setTransitionName(mCoverUrl,getString(R.string.share_element_game));
        mTitle.setText(appBean.getTitle());
        mSize.setText(String.format(Locale.CHINA, "%.2fMB", ((float) appBean.getSize()) / 1024 / 1024));
        mGameType.setText(appBean.getGameType());
        mProgressBar.setOnClickListener(appBean);
        mProgressBar.initState(appBean.getDownUrl());
        float downLoadTimes = appBean.getDownloadTimes();
        if (downLoadTimes > 9999) {
            mDownloadTimes.setText(String.format("%.2f万次下载", downLoadTimes / 10000));
        } else {
            mDownloadTimes.setText(String.format("%.0f次下载", downLoadTimes));
        }
        mDesc.setText(appBean.getDesc());
        mAdapter.addAll(appBean.getSmallPictureUrls());
    }

    private void initView() {
        mCoverUrl = (SimpleDraweeView) findViewById(coverUrl);
        mTitle = (TextView) findViewById(R.id.title);
        mDownloadTimes = (TextView) findViewById(R.id.downloadTimes);
        mGameType = (TextView) findViewById(R.id.gameType);
        mSize = (TextView) findViewById(R.id.size);
        mDesc = (TextView) findViewById(R.id.desc);
        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.game_rv);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
        mAdapter = new GameDetailAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mProgressBar = (FlickerProgressBar) findViewById(R.id.downUrl);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                GameFragment.mGameAdapter.notifyDataSetChanged();
                break;
        }
    }

    @Override
    public void onResponse(retrofit2.Call<GameDetailBean> call, Response<GameDetailBean> response) {
        fillView(response.body());
    }

    @Override
    public void onFailure(retrofit2.Call<GameDetailBean> call, Throwable t) {

    }
}
