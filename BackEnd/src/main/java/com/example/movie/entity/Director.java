package com.example.movie.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

public class Director {
    @TableId(type = IdType.AUTO)
    private Integer directorId;
    private String director;

    public Director(String director) {
        this.director = director;
    }

    public Director() {
    }

    public Director(Integer directorId, String director) {
        this.directorId = directorId;
        this.director = director;
    }

    public Integer getDirectorId() {
        return directorId;
    }

    public void setDirectorId(Integer directorId) {
        this.directorId = directorId;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    @Override
    public String toString() {
        return "Director{" +
                "directorId=" + directorId +
                ", director='" + director + '\'' +
                '}';
    }
}
