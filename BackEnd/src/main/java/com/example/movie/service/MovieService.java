package com.example.movie.service;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.movie.entity.*;
import com.example.movie.mapper.DirectorMapper;
import com.example.movie.mapper.MovieMapper;
import com.example.movie.mapper.PerformerMapper;
import com.example.movie.mapper.ScriptwriterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    @Autowired
    private MovieMapper movieMapper;
    @Autowired
    private DirectorMapper directorMapper;
    @Autowired
    private PerformerMapper performerMapper;
    @Autowired
    private ScriptwriterMapper scriptwriterMapper;
    public int save(Movie movie){
        int i = movieMapper.insert(movie);
        int movieId = movie.getMovieId();
        List<Director> directorList = movie.getDirectorList();
        List<Performer> performerList = movie.getPerformerList();
        List<Scriptwriter> scriptwriterList = movie.getScriptwriterList();
        for (Director director: directorList){
            List<Director> director1 = directorMapper.selectByName(director.getDirector());
            if (director1.size() != 0){
                directorMapper.insertdir(movieId, director1.get(0).getDirectorId());
            }else {
                directorMapper.insert(director);
                directorMapper.insertdir(movieId, director.getDirectorId());
            }
        }
        for (Performer performer: performerList){
            List<Performer> performer1 = performerMapper.selectByName(performer.getPerformer());
            if (performer1.size() != 0){
                performerMapper.insertper(movieId, performer1.get(0).getPerformerId());
            }else {
                performerMapper.insert(performer);
                System.out.println(movieId);
                performerMapper.insertper(movieId, performer.getPerformerId());
            }
        }
        for (Scriptwriter scriptwriter : scriptwriterList){
            List<Scriptwriter> scriptwriter1 = scriptwriterMapper.selectByName(scriptwriter.getScriptwriter());
            if(scriptwriter1.size() != 0){
                scriptwriterMapper.insertscr(movieId, scriptwriter1.get(0).getScriptwriterId());
            }else {
                scriptwriterMapper.insert(scriptwriter);
                scriptwriterMapper.insertscr(movieId, scriptwriter.getScriptwriterId());
            }
        }
        return i;
    }

    public int update(int movieId, int grade){
        Movie movie = movieMapper.selectById(movieId);
        double gradeOld = movie.getGrade();
        int gradeNumOld = movie.getGradeNum();
        int gradeNumNew = gradeNumOld + 1;
        double gradeNew = (gradeOld * gradeNumOld + grade) / gradeNumNew;
        return movieMapper.updateGrade(gradeNew,gradeNumNew,movieId);
    }


    public List<Movie> personalizedSelect(String type, String country, String language, double[] grade){
        String typeSql = "";
        String countrySql = "";
        String languageSql = "";
        if (!type.equals("不限")){
            typeSql = " and type = " + "\"" + type + "\"";
        }
        if (!country.equals("不限")){
            countrySql =" and country = " + "\"" + country + "\"";
        }
        if (!language.equals("不限")){
            languageSql =" and language = " + "\"" + language + "\"";
        }
        String Sql=typeSql + countrySql + languageSql;
        System.out.println(Sql);
        List<Movie> movieList = movieMapper.personalizedSelect(grade[0],grade[1],Sql);
        return movieList;
    }
}
