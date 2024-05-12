package com.example.movie.controller;

import com.example.movie.entity.Movie;
import com.example.movie.mapper.MovieMapper;
import com.example.movie.service.MovieService;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MovieController {
    @Autowired
    private MovieMapper movieMapper;
    @Autowired
    private MovieService movieService;

    @GetMapping("/selectAll")
    public List query(){
        List<Movie> movieList = movieMapper.selectAll();
        return movieList;
    }

    @PostMapping("/insert")
    public String insert(Movie movie){
        movie.setGrade(0.0);
        movie.setGradeNum(0);
        int i = movieService.save(movie);
        if (i > 0){
            return "插入成功";
        }
        else {
            return "插入失败";
        }
    }

    @PostMapping("/delete")
    public String delete(int movieId){
        System.out.println(movieId);
        int i = movieMapper.delete(movieId);
        if (i > 0){
            return "删除成功";
        }else {
            return "删除失败";
        }
    }

    @PostMapping("/update")
    public String update(int movieId, int grade){
        int i = movieService.update(movieId, grade);
        if (i > 0){
            return "更新成功";
        }else {
            return "更新失败";
        }
    }

    @PostMapping("/selectByName")
    public List<Movie> selectByName(String movie){
        List<Movie> movieList = movieMapper.selectByName(movie);
        return movieList;
    }

    @PostMapping("/personalizedSelect")
    public List<Movie> personalizedSelect(String type, String country, String language, String grade){
        double[] gradeNum= new double[2];
        if (grade.equals("不限")){
            gradeNum[0]=0;
            gradeNum[1]=10;
        } else if (grade.equals("6.0以下")) {
            gradeNum[0]=0;
            gradeNum[1]=6;
        }else {
            String[] s=grade.split("~");
            gradeNum[0]=Double.parseDouble(s[0]);
            gradeNum[1]=Double.parseDouble(s[1]);
        }
        List<Movie> movieList = movieService.personalizedSelect(type, country, language, gradeNum);
        return movieList;
    }

}
