package com.orangelittle.u17.fragment.level1;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.orangelittle.u17.R;
import com.orangelittle.u17.adapter.RankAdapter;
import com.orangelittle.u17.entries.level1.RankBean;
import com.orangelittle.u17.util.ClassType;
import com.orangelittle.u17.util.NetUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class RankFragment extends Fragment implements Callback<RankBean>, View.OnClickListener {


    private Toolbar rank_toolbar;
    private TabLayout rank_tab;
    private ViewPager rank_vp;
    private RankAdapter adapter;
    private AppCompatActivity activity;

    public RankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rank, container, false);
    }

    @Override
    public void onViewCreated(View view,  Bundle savedInstanceState) {
        rank_toolbar = ((Toolbar) view.findViewById(R.id.rank_toolbar));
        rank_tab = ((TabLayout) view.findViewById(R.id.rank_tab));
        rank_vp = ((ViewPager) view.findViewById(R.id.rank_vp));

        activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(rank_toolbar);
        ActionBar actionBar = activity.getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        rank_toolbar.setNavigationIcon(R.mipmap.toolbar_back_normal);
        actionBar.setDisplayHomeAsUpEnabled(true);
        rank_toolbar.setNavigationOnClickListener(this);
        adapter = new RankAdapter(getChildFragmentManager());
        rank_vp.setAdapter(adapter);
        NetUtils.getData(ClassType.RankBean,this);
        rank_tab.setupWithViewPager(rank_vp);


    }


    @Override
    public void onResponse(Call<RankBean> call, Response<RankBean> response) {
        List<RankBean.DataBean.ReturnDataBean.RankinglistBean> rankinglist = response.body().getData().getReturnData().getRankinglist();
        adapter.setList(rankinglist);
    }

    @Override
    public void onFailure(Call<RankBean> call, Throwable t) {
        Toast.makeText(getContext(), "数据出错", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        activity.finish();
    }
}
