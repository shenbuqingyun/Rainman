package com.love.rain.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.love.rain.R;
import com.love.rain.view.PickerScrollView;
import com.love.rain.view.Pickers;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 作者    cpf
 * 时间    2018/12/4 18:40
 * 文件    Rainman
 * 描述
 */
public class TimingFragment extends Fragment {
    private Unbinder unbinder;
    @BindView(R.id.pickerscrlllview)
    FrameLayout mPickerscrlllview;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_audio_timing_fragment,container,false);
        unbinder = ButterKnife.bind(this,view);

        PickerScrollView pickerScrollView = new PickerScrollView(getActivity());
        mPickerscrlllview.addView(pickerScrollView);
        pickerScrollView.setOnSelectListener(pickerListener);

        return view;
    }

    PickerScrollView.onSelectListener pickerListener = new PickerScrollView.onSelectListener() {
        @Override
        public void onSelect(Pickers pickers) {
//            tvTime.setText(pickers.getShowConetnt());

        }
    };


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
