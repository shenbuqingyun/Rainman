package com.love.rain.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.love.rain.R;
import com.love.rain.player.MyAudioPlayer;

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
        View view = inflater.inflate(R.layout.fragment_front_panel,container,false);
        unbinder = ButterKnife.bind(this,view);
        audioPlayer = new MyAudioPlayer();
        return view;
    }

    @OnClick(R.id.fragment_front_panel_guide_img)
    public void controlAudioPlay(){
        if (!is_play){
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
    public void playAudio(){
        audioPlayer.play(getActivity());
    }

    @OnClick(R.id.fragment_front_panel_stopBtn)
    public void stopAudio(){
        audioPlayer.stop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        audioPlayer.stop();
        unbinder.unbind();
    }
}
