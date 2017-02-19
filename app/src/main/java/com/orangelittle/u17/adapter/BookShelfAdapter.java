package com.orangelittle.u17.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;


import com.orangelittle.u17.fragment.level1.Rb1Fragment;
import com.orangelittle.u17.fragment.level1.Rb2Fragment;
import com.orangelittle.u17.fragment.level1.Rb3Fragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ice on 2016/10/7.
 */

public class BookShelfAdapter extends FragmentPagerAdapter{

    private List<Fragment> fragmentList;
    public BookShelfAdapter(FragmentManager fm) {
        super(fm);
        fragmentList = new ArrayList<>();
        fragmentList.add(new Rb1Fragment());
        fragmentList.add(new Rb2Fragment());
        fragmentList.add(new Rb3Fragment());
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return 3;
    }
}
