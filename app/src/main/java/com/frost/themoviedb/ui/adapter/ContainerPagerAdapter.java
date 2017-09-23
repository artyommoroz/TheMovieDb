package com.frost.themoviedb.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.frost.themoviedb.ui.fragment.BaseFragment;

import java.util.List;

public class ContainerPagerAdapter extends FragmentStatePagerAdapter {

    private List<BaseFragment> fragments;
    private List<String> titles;

    public ContainerPagerAdapter(FragmentManager fragmentManager, List<BaseFragment> fragments,
                              List<String> titles) {
        super(fragmentManager);
        this.fragments = fragments;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
