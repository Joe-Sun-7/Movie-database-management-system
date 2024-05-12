package com.example.movie.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

public class Performer {
    @TableId(type = IdType.AUTO)
    private Integer performerId;
    private String performer;

    public Performer() {
    }

    public Performer(String performer) {
        this.performer = performer;
    }

    public Performer(Integer performerId, String performer) {
        this.performerId = performerId;
        this.performer = performer;
    }

    public Integer getPerformerId() {
        return performerId;
    }

    public void setPerformerId(Integer performerId) {
        this.performerId = performerId;
    }

    public String getPerformer() {
        return performer;
    }

    public void setPerformer(String performer) {
        this.performer = performer;
    }

    @Override
    public String toString() {
        return "Performer{" +
                "performerId=" + performerId +
                ", performer='" + performer + '\'' +
                '}';
    }
}
