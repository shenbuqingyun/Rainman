package com.love.rain.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.love.rain.R;
import com.love.rain.player.MyAudioPlayer;
import com.love.rain.util.LogUtils;
import com.love.rain.view.PickerScrollView;
import com.love.rain.view.Pickers;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 作者    Chin_style
 * 时间    2018/12/1
 * 文件    Rainman
 * 描述
 * 致谢    Thank you for your advice.
 */
public class FrontPanelFragment extends Fragment {
    private Unbinder unbinder;
    private MyAudioPlayer audioPlayer;
    private boolean is_play;
    @BindView(R.id.fragment_front_panel_playBtn)
    Button mPlayBtn;
    @BindView(R.id.fragment_front_panel_stopBtn)
    Button mStopBtn;
    @BindView(R.id.fragment_front_panel_guide_img)
    ImageView mGuideImage;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_front_panel, container, false);
        unbinder = ButterKnife.bind(this, view);
        audioPlayer = new MyAudioPlayer();

        return view;
    }

    @OnClick(R.id.fragment_front_panel_guide_img)
    public void controlAudioPlay() {
        if (!is_play) {
            audioPlayer.play(getActivity());
            mGuideImage.setImageResource(R.drawable.status_bar_play_img);
            is_play = true;
        } else {
            audioPlayer.stop();
            mGuideImage.setImageResource(R.drawable.status_bar_pause_img);
            is_play = false;
        }
    }

    @OnClick(R.id.fragment_front_panel_playBtn)
    public void playAudio() {
//        audioPlayer.play(getActivity());
        hideBottomUIMenu();
    }

    @OnClick(R.id.fragment_front_panel_stopBtn)
    public void stopAudio() {
//        audioPlayer.stop();
        showNavigationBar();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        audioPlayer.stop();
        unbinder.unbind();
    }

    /*
    * 状态栏 导航栏设为透明
    * */
    private void hideStatusBarNavigationBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getActivity().getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.RED);
            window.setNavigationBarColor(Color.BLUE);
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        }
    }

    /**
     * 隐藏虚拟按键，并且全屏
     */
    protected void hideBottomUIMenu() {
        //隐藏虚拟按键，并且全屏
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            View v = getActivity().getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            View decorView = getActivity().getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }

    private void showNavigationBar() {
        View decorView = getActivity().getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_VISIBLE;
        decorView.setSystemUiVisibility(uiOptions);
    }

}


