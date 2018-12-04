package com.love.rain.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.love.rain.R;
import com.love.rain.adapter.AudioAdapter;
import com.love.rain.bean.Audio;

import java.util.ArrayList;
import java.util.List;

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
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_audio_player_fragment,container,false);
        unbinder = ButterKnife.bind(this,view);
        initAudio();
        initRecyclerView();
        return view;
    }

    private void initRecyclerView(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);

        AudioAdapter audioAdapter = new AudioAdapter(mAudioList);
        mRecyclerView.setAdapter(audioAdapter);
    }

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
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
