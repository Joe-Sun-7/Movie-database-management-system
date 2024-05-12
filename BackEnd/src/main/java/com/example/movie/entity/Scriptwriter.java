package com.example.movie.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

public class Scriptwriter {
    @TableId(type = IdType.AUTO)
    private Integer scriptwriterId;
    private String scriptwriter;

    public Scriptwriter() {
    }

    public Scriptwriter(String scriptwriter) {
        this.scriptwriter = scriptwriter;
    }

    public Scriptwriter(Integer scriptwriterId, String scriptwriter) {
        this.scriptwriterId = scriptwriterId;
        this.scriptwriter = scriptwriter;
    }

    public Integer getScriptwriterId() {
        return scriptwriterId;
    }

    public void setScriptwriterId(Integer scriptwriterId) {
        this.scriptwriterId = scriptwriterId;
    }

    public String getScriptwriter() {
        return scriptwriter;
    }

    public void setScriptwriter(String scriptwriter) {
        this.scriptwriter = scriptwriter;
    }

    @Override
    public String toString() {
        return "Scriptwriter{" +
                "scriptwriterId=" + scriptwriterId +
                ", scriptwriter='" + scriptwriter + '\'' +
                '}';
    }
}
