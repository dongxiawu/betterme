package com.zoe.dongxia.betterme.model;

import android.support.annotation.NonNull;

/**
 * Created by dongxia on 18-1-6.
 */

public class Todo {
    private String name;
    private int duration;
    private String remark;
    private int breakTime;
    private int cycles;
    private int timingType;

    public Todo(@NonNull String name){
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public String getName() {
        return name;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setName(String name) {
        this.name = name;
    }
}
