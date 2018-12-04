package com.love.rain.bean;

/**
 * 作者    cpf
 * 时间    2018/12/4 10:26
 * 文件    Rainman
 * 描述
 */
public class Audio {
    private String description;
    private int audioId;

    public Audio(String description,int audioId){
        this.description = description;
        this.audioId = audioId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAudioId() {
        return audioId;
    }

    public void setAudioId(int audioId) {
        this.audioId = audioId;
    }
}
