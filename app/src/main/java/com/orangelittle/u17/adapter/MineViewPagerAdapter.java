package com.orangelittle.u17.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2017/1/12 0012.
 */

public class MineViewPagerAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> list;
    private String[] titles;

    public MineViewPagerAdapter(FragmentManager fm, List<Fragment> list, String[] titles) {
        super(fm);
        this.list=list;
        this.titles=titles;

    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list==null?0:list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
