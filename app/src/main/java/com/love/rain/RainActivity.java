package com.love.rain;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.astuetz.PagerSlidingTabStrip;
import com.love.rain.adapter.ViewPagerFragmentAdapter;
import com.love.rain.fragment.AudioPlayerFragment;
import com.love.rain.fragment.FrontPanelFragment;
import com.love.rain.view.SimplePanel;

import butterknife.BindView;
import butterknife.ButterKnife;
/**
 * 作者    Chin_style
 * 时间    2018/12/1
 * 文件    Rainman
 * 描述
 * 致谢    Thank you for your advice.
 */
public class RainActivity extends AppCompatActivity {

    @BindView(R.id.activity_main_panel_layout)
    SimplePanel mSimplePanel;
    @BindView(R.id.activity_main_view_pager)
    ViewPager mViewPager;
    @BindView(R.id.activity_main_tab)
    PagerSlidingTabStrip mPagerSlidingTabStrip;

    private FrontPanelFragment mFrontPanelFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rain);
        ButterKnife.bind(this);
        mFrontPanelFragment = new FrontPanelFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_main_panel_layout, mFrontPanelFragment)
                .commit();
        String[] titles = {"主页", "娱乐", "科技",};
        Fragment[] fragments = { new AudioPlayerFragment()};
        ViewPagerFragmentAdapter adapter = new ViewPagerFragmentAdapter(getSupportFragmentManager(), fragments, titles);
        mViewPager.setAdapter(adapter);
        mPagerSlidingTabStrip.setViewPager(mViewPager);
    }
}
