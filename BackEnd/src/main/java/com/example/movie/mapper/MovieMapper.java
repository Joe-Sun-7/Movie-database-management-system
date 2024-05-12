package com.example.movie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.movie.entity.Movie;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface MovieMapper extends BaseMapper<Movie> {
    @Select("select * from movie")
    @Results(
            {
                    @Result(column = "movie_id", property = "movieId"),
                    @Result(column = "movie", property = "movie"),
                    @Result(column = "grade", property = "grade"),
                    @Result(column = "type", property = "type"),
                    @Result(column = "country", property = "country"),
                    @Result(column = "language", property = "language"),
                    @Result(column = "date", property = "date"),
                    @Result(column = "duration", property = "duration"),
                    @Result(column = "alias", property = "alias"),
                    @Result(column = "grade_num", property = "gradeNum"),
                    @Result(column = "movie_id", property = "directorList", javaType = List.class,
                        many = @Many(select = "com.example.movie.mapper.DirectorMapper.selectDirector")),
                    @Result(column = "movie_id", property = "performerList", javaType = List.class,
                            many = @Many(select = "com.example.movie.mapper.PerformerMapper.selectPerformer")),
                    @Result(column = "movie_id", property = "scriptwriterList", javaType = List.class,
                            many = @Many(select = "com.example.movie.mapper.ScriptwriterMapper.selectScriptwriter"))
            }
    )
    List<Movie> selectAll();

    @Update("update movie set grade=#{gradeNew},grade_num=#{gradeNumNew} where movie_id = #{movieId}")
    int updateGrade(@Param("gradeNew") double gradeNew, @Param("gradeNumNew") int gradeNumNew, @Param("movieId") int movieId);

    @Delete("delete from movie where movie_id = #{movieId}")
    int delete(@Param("movieId") int movieId);

    @Select("select * from movie where movie like CONCAT('%', #{movie}, '%')")
    @Results(
            {
                    @Result(column = "movie_id", property = "movieId"),
                    @Result(column = "movie", property = "movie"),
                    @Result(column = "grade", property = "grade"),
                    @Result(column = "type", property = "type"),
                    @Result(column = "country", property = "country"),
                    @Result(column = "language", property = "language"),
                    @Result(column = "date", property = "date"),
                    @Result(column = "duration", property = "duration"),
                    @Result(column = "alias", property = "alias"),
                    @Result(column = "grade_num", property = "gradeNum"),
                    @Result(column = "movie_id", property = "directorList", javaType = List.class,
                            many = @Many(select = "com.example.movie.mapper.DirectorMapper.selectDirector")),
                    @Result(column = "movie_id", property = "performerList", javaType = List.class,
                            many = @Many(select = "com.example.movie.mapper.PerformerMapper.selectPerformer")),
                    @Result(column = "movie_id", property = "scriptwriterList", javaType = List.class,
                            many = @Many(select = "com.example.movie.mapper.ScriptwriterMapper.selectScriptwriter"))
            }
    )
    List<Movie> selectByName(@Param("movie") String movie);

    @Select("select * from movie where grade >= #{gradeLow} and grade <= #{gradeHigh}${Sql}")
    @Results(
            {
                    @Result(column = "movie_id", property = "movieId"),
                    @Result(column = "movie", property = "movie"),
                    @Result(column = "grade", property = "grade"),
                    @Result(column = "type", property = "type"),
                    @Result(column = "country", property = "country"),
                    @Result(column = "language", property = "language"),
                    @Result(column = "date", property = "date"),
                    @Result(column = "duration", property = "duration"),
                    @Result(column = "alias", property = "alias"),
                    @Result(column = "grade_num", property = "gradeNum"),
                    @Result(column = "movie_id", property = "directorList", javaType = List.class,
                            many = @Many(select = "com.example.movie.mapper.DirectorMapper.selectDirector")),
                    @Result(column = "movie_id", property = "performerList", javaType = List.class,
                            many = @Many(select = "com.example.movie.mapper.PerformerMapper.selectPerformer")),
                    @Result(column = "movie_id", property = "scriptwriterList", javaType = List.class,
                            many = @Many(select = "com.example.movie.mapper.ScriptwriterMapper.selectScriptwriter"))
            }
    )
    List<Movie> personalizedSelect(@Param("gradeLow") double gradeLow, @Param("gradeHigh") double gradeHigh, @Param("Sql") String Sql);
}
