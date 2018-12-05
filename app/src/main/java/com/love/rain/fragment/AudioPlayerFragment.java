package com.love.rain.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.love.rain.R;
import com.love.rain.adapter.AudioAdapter;
import com.love.rain.bean.Audio;
import com.love.rain.player.MyAudioPlayer;
import com.love.rain.util.LogUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 作者    Chin_style
 * 时间    2018/12/1 0001
 * 文件    Rainman
 * 描述
 * 致谢    Thank you for your advice.
 */
public class AudioPlayerFragment extends Fragment {
    private Unbinder unbinder;
    @Nullable
    @BindView(R.id.fragment_front_panel_recycler_view)
    RecyclerView mRecyclerView;

    private List<Audio> mAudioList = new ArrayList<>();
    private MyAudioPlayer mAudioPlayer;
    private Timer mTimer;
    private TimerTask mTimerTask;
    private int currentPosition;
    private int audioDuration;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_audio_player_fragment,container,false);
        unbinder = ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAudioPlayer = new MyAudioPlayer();
        initAudio();
        initRecyclerView();
    }

    private void initRecyclerView(){
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);

        AudioAdapter audioAdapter = new AudioAdapter(mAudioList);
        audioAdapter.setOnItemClickListener(new AudioAdapter.OnItemClickLsitener() {
            @Override
            public void onItemClick(View view, int postion) {
/*                int childCount = mRecyclerView.getChildCount();
                for (int i = 0; i < postion; i++) {
                    final View child = mRecyclerView.getChildAt(i);
                    child.setBackgroundColor(Color.parseColor("#272C32"));
                }
                for (int i = postion; postion < i && i < childCount; i++) {
                    final View child = mRecyclerView.getChildAt(i);
                    child.setBackgroundColor(Color.parseColor("#272C32"));
                }*/
//                View positionView = mRecyclerView.getChildAt(postion); //获取列表子项的第一种方法
//                View positionView = layoutManager.findViewByPosition(postion); // 获取列表子项的第二种方法
//                view.setBackgroundColor(getActivity().getResources().getColor(R.color.cyan));
                //颜色的控制逻辑写在Adapter外部时，控制不够精准 所以放入到Adapter的onBindViewHolder()中执行
                mAudioPlayer.play(getActivity(), mAudioList.get(postion).getAudioId());
                Toast.makeText(getActivity(), "点击了" + (postion + 1) + "项", Toast.LENGTH_SHORT).show();
                audioDuration = mAudioPlayer.getAudioDuration();
                loopingHandler.removeCallbacks(looping_thread);
                //用定时器实现记录播放进度 能实现无缝循环但是会出现再次点击报错的问题
                mTimer = new Timer();
                mTimerTask = new TimerTask() {
                    @Override
                    public void run() {
                        loopingHandler.postDelayed(looping_thread,100);
                        if (audioDuration - currentPosition < 500) {
                            mAudioPlayer.seekTo(500);
                        }
                    }
                };
                mTimer.schedule(mTimerTask, 0, 10);

            }
        });
        mRecyclerView.setAdapter(audioAdapter);
    }

    Handler loopingHandler = new Handler();
    Runnable looping_thread = new Runnable() {
        public void run() {
            currentPosition = mAudioPlayer.getCurrentPosition();
        }
    };

    private void initAudio(){
        Audio audio0 = new Audio("audio0",R.raw.audio_0);
        mAudioList.add(audio0);
        Audio audio1 = new Audio("audio1",R.raw.audio_1);
        mAudioList.add(audio1);
        Audio audio2 = new Audio("audio2",R.raw.audio_2);
        mAudioList.add(audio2);
        Audio audio3 = new Audio("audio3",R.raw.audio_3);
        mAudioList.add(audio3);
        Audio audio4 = new Audio("audio4",R.raw.audio_4);
        mAudioList.add(audio4);
        Audio audio5 = new Audio("audio5",R.raw.audio_5);
        mAudioList.add(audio5);
        Audio audio6 = new Audio("audio6",R.raw.audio_6);
        mAudioList.add(audio6);
        Audio audio7 = new Audio("audio7",R.raw.audio_7);
        mAudioList.add(audio7);
        Audio audio8 = new Audio("audio8",R.raw.audio_8);
        mAudioList.add(audio8);
        LogUtils.d("AudioPlayerFragment","mAudioList," + mAudioList.size()); //改变三个Fragment的位置 躲开数据自增bug
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
