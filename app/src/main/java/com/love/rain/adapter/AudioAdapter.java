package com.love.rain.adapter;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.love.rain.R;
import com.love.rain.bean.Audio;

import java.util.List;

/**
 * 作者    cpf
 * 时间    2018/12/4 10:30
 * 文件    Rainman
 * 描述    音频的Adapter 主要难点有三项
 *        ①点击不同项时控制子项的颜色变化；②利用SharePreference进行持久化保存;③如何控制每个子项中音频的播放与停止
 */
public class AudioAdapter extends RecyclerView.Adapter<AudioAdapter.ViewHolder> implements View.OnClickListener {

    private List<Audio> mAudioList;
    private View itemView;
    public int selected = -1;
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        LinearLayout linearLayout;
        public ViewHolder(View itemView){
            super(itemView);
            linearLayout = itemView.findViewById(R.id.audio_adapter_root);
            textView = itemView.findViewById(R.id.audio_adapter_txt);
        }
    }

    public AudioAdapter(List<Audio> audioList){
        mAudioList = audioList;
    }

    @NonNull
    @Override
    public AudioAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.audio_adapter_layout,parent,false);
        ViewHolder holder = new ViewHolder(itemView);
        itemView.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AudioAdapter.ViewHolder holder, int position) {
        Audio audio = mAudioList.get(position);
        holder.itemView.setTag(position);
        holder.textView.setText(audio.getDescription());
        if (position == selected) {
            holder.itemView.setBackgroundColor(Color.parseColor("#22A1A2"));
        } else {
            holder.itemView.setBackgroundColor(Color.parseColor("#272C32"));
        }
    }

    @Override
    public void onClick(View view){
        selected = (Integer) view.getTag();
        notifyDataSetChanged();
        if (onItemClickListener != null){
            onItemClickListener.onItemClick(view, (Integer) view.getTag());
        }
    }

    @Override
    public int getItemCount() {
        return mAudioList.size();
    }

    public interface OnItemClickLsitener{
        void onItemClick(View view, int postion);
    }

    private OnItemClickLsitener onItemClickListener;

    public void setOnItemClickListener(OnItemClickLsitener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

}
