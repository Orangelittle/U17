package com.orangelittle.u17.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import com.orangelittle.u17.entries.level1.RankBean;
import com.orangelittle.u17.fragment.level1.RankItemFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wmc on 16-9-30.
 */

public class RankAdapter extends FragmentPagerAdapter {
    private List<RankBean.DataBean.ReturnDataBean.RankinglistBean> list;

    public void setList(List<RankBean.DataBean.ReturnDataBean.RankinglistBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public RankAdapter(FragmentManager fm) {
        super(fm);
        list = new ArrayList<>();

    }

    @Override
    public Fragment getItem(int position) {
        RankBean.DataBean.ReturnDataBean.RankinglistBean rankinglistBean = list.get(position);
        String argValue = rankinglistBean.getArgValue();
        String argName = rankinglistBean.getArgName();
        return RankItemFragment.newInstance(argValue, argName);
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return list.get(position).getTitle();
    }
}
