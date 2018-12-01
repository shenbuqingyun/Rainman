package com.love.rain.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * 作者    Chin_style
 * 时间    2018/12/1 0001
 * 文件    Rainman
 * 描述
 * 致谢    Thank you for your advice.
 */
public class ViewPagerFragmentAdapter extends FragmentPagerAdapter {

    private Fragment[] mFragments;
    private String[] mTitles;

    public ViewPagerFragmentAdapter(FragmentManager fm, Fragment[] fragments, String[] titles) {
        super(fm);
        this.mFragments = fragments;
        this.mTitles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments[position];
    }

    @Override
    public int getCount() {
        return mFragments.length;
    }
    //使用ViewPager+PagerSildingTapStrip时必须复写
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
