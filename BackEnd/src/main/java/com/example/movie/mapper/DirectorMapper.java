package com.example.movie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.movie.entity.Director;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface DirectorMapper extends BaseMapper<Director> {
    @Select("select director_id,director from dir_movie natural join director where movie_id = #{movieId}")
    List<Director> selectDirector(int movieId);

    @Select("select * from director where director=#{director}")
    List<Director> selectByName(String director);

    @Insert("insert into dir_movie(movie_id, director_id) values (#{movieId},#{directorId})")
    int insertdir(@Param("movieId") int movieId, @Param("directorId") int directorId);
}
