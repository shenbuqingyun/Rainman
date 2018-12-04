package com.love.rain.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.love.rain.R;
import com.love.rain.bean.Audio;

import java.util.List;

/**
 * 作者    cpf
 * 时间    2018/12/4 10:30
 * 文件    Rainman
 * 描述
 */
public class AudioAdapter extends RecyclerView.Adapter<AudioAdapter.ViewHolder> {

    private List<Audio> mAudioList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;

        public ViewHolder(View itemView){
            super(itemView);
            textView = itemView.findViewById(R.id.audio_adapter_txt);
        }
    }

    public AudioAdapter(List<Audio> audioList){
        mAudioList = audioList;
    }

    @NonNull
    @Override
    public AudioAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.audio_adapter_layout,parent,false);
        ViewHolder holder = new ViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AudioAdapter.ViewHolder holder, int position) {
        Audio audio = mAudioList.get(position);
        holder.textView.setText(audio.getDescription());
    }

    @Override
    public int getItemCount() {
        return mAudioList.size();
    }
}
