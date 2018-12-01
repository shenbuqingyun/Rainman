package com.love.rain.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.love.rain.R;

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
    @BindView(R.id.fragment_front_panel_recycler_view)
    RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_front_panel,container,false);
        unbinder = ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
