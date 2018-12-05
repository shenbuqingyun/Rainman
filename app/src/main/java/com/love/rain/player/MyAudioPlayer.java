package com.love.rain.player;

import android.content.Context;
import android.media.MediaPlayer;

import com.love.rain.R;
import com.love.rain.bean.Audio;
import com.love.rain.util.LogUtils;

import java.io.IOException;

/**
 * 作者    cpf
 * 时间    2018/12/4
 * 文件    Rainman
 * 描述    封装MediaPlayer，之后管理播放器的状态会更加方便
 */
public class MyAudioPlayer {
    private MediaPlayer mPlayer;

    public void stop(){
        if (mPlayer != null){
            mPlayer.release();
            mPlayer = null;
        }
    }

    public void play(Context c){
        //play()方法的开头调用stop()方法 防止创建多个MediaPlayer实例
        stop();

        mPlayer = MediaPlayer.create(c, R.raw.audio_5);
        //设立监听器 音频播放完之后立即调用stop()方法 尽快释放实例和资源
        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                stop();
            }
        });
/*        try {
            //预加载音频 使用时放置在try catch中，貌似不使用预加载反而可以播放
            //prepare()和prepareAsync() 提供了同步和异步两种方式设置播放器进入prepare状态，需要注意的是，
            //如果MediaPlayer实例是由create方法创建的，那么第一次启动播放前不需要再调用prepare（）了，
            //因为create方法里已经调用过了。
            mPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        //正式加载音频
        mPlayer.start();
    }

    public void play(Context context, int id){
        stop();

        mPlayer = MediaPlayer.create(context, id);
//        mPlayer.setLooping(true); //设置音频循环播放

        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
//                seekTo(1000);
            }
        });

        mPlayer.start();
    }

    /*
    * 获取音频总时长
    * */
    public int getAudioDuration(){
        if (mPlayer != null){
            int j = mPlayer.getDuration();
            return j;
        }
        return 1;
    }

    /*
    * 获取播放进度
    * */
    public int getCurrentPosition(){
        if (mPlayer != null){
            int i = mPlayer.getCurrentPosition();
            LogUtils.d(this,"CurrentPosition ," + i);
            return i;
        }
        return 0;
    }

    /**
     * 指定位置播放
     * @param pos
     */
    public void seekTo(int pos) {
        if (mPlayer == null) {
            return;
        }
        mPlayer.seekTo(pos);
        mPlayer.start();
    }

}
/*
* setOnCompletionListener(MediaPlayer.OnCompletionListener listener)：当流媒体播放完毕的时候回调。
setOnErrorListener(MediaPlayer.OnErrorListener listener)：当播放中发生错误的时候回调。
setOnPreparedListener(MediaPlayer.OnPreparedListener listener)：当装载流媒体完毕的时候回调。
setOnSeekCompleteListener(MediaPlayer.OnSeekCompleteListener listener)：当使用seekTo()设置播放位置的时候回调。
seekTo()是定位方法，可以让播放器从指定的位置开始播放，需要注意的是该方法是个异步方法，也就是说该方法返回时并不意味着定位完成，
尤其是播放的网络文件，真正定位完成时会触发OnSeekComplete.onSeekComplete()，
如果需要是可以调用setOnSeekCompleteListener(OnSeekCompleteListener)设置监听器来处理的
* */
