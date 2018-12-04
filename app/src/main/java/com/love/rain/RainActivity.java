package com.love.rain;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.astuetz.PagerSlidingTabStrip;
import com.love.rain.adapter.ViewPagerFragmentAdapter;
import com.love.rain.fragment.AudioPlayerFragment;
import com.love.rain.fragment.FrontPanelFragment;
import com.love.rain.util.LogUtils;
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
    @BindView(R.id.activity_main_bottom_layout)
    LinearLayout mLinearLayout;
    private FrontPanelFragment mFrontPanelFragment;
    private int width, height;
    private float ratio = (float) 0.382;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rain);
        ButterKnife.bind(this);
        width = getResources().getDisplayMetrics().widthPixels ;
        height = getResources().getDisplayMetrics().heightPixels ;
        mFrontPanelFragment = new FrontPanelFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_main_panel_layout, mFrontPanelFragment)
                .commit();

        String[] titles = {"雨声阵阵雨声阵阵", "延时睡眠延时睡眠", "应用相关应用相关",};
        Fragment[] fragments = { new AudioPlayerFragment(),new AudioPlayerFragment(),new AudioPlayerFragment()};
        ViewPagerFragmentAdapter adapter = new ViewPagerFragmentAdapter(getSupportFragmentManager(), fragments, titles);
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) mLinearLayout.getLayoutParams();
        params.height = (int) (height*ratio);
        LogUtils.d("height","RainActivity" + params.height);
        mLinearLayout.setLayoutParams(params);
        mViewPager.setAdapter(adapter);
        mPagerSlidingTabStrip.setViewPager(mViewPager);
    }
}
