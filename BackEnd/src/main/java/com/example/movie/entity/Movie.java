package com.example.movie.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.sql.Timestamp;
import java.util.List;

public class Movie {
    @TableId(type = IdType.AUTO)
    private Integer movieId;
    private String movie;
    private Double grade;
    private String type;
    private String country;
    private String language;
    private Timestamp date;
    private Integer duration;
    private String alias;
    private Integer gradeNum;
    @TableField(exist = false)
    private List<Director> directorList;
    @TableField(exist = false)
    private List<Performer> performerList;
    @TableField(exist = false)
    private List<Scriptwriter> scriptwriterList;

    public Movie() {
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Integer getGradeNum() {
        return gradeNum;
    }

    public void setGradeNum(Integer gradeNum) {
        this.gradeNum = gradeNum;
    }

    public List<Director> getDirectorList() {
        return directorList;
    }

    public void setDirectorList(List<Director> directorList) {
        this.directorList = directorList;
    }

    public List<Performer> getPerformerList() {
        return performerList;
    }

    public void setPerformerList(List<Performer> performerList) {
        this.performerList = performerList;
    }

    public List<Scriptwriter> getScriptwriterList() {
        return scriptwriterList;
    }

    public void setScriptwriterList(List<Scriptwriter> scriptwriterList) {
        this.scriptwriterList = scriptwriterList;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieId=" + movieId +
                ", movie='" + movie + '\'' +
                ", grade=" + grade +
                ", type='" + type + '\'' +
                ", country='" + country + '\'' +
                ", language='" + language + '\'' +
                ", date=" + date +
                ", duration=" + duration +
                ", alias='" + alias + '\'' +
                ", gradeNum=" + gradeNum +
                ", directorList=" + directorList +
                ", performerList=" + performerList +
                ", scriptwriterList=" + scriptwriterList +
                '}';
    }
}
