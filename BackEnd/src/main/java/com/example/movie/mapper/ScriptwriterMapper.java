package com.example.movie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.movie.entity.Performer;
import com.example.movie.entity.Scriptwriter;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface ScriptwriterMapper extends BaseMapper<Scriptwriter> {
    @Select("select scriptwriter_id,scriptwriter from scr_movie natural join scriptwriter where movie_id=#{movieId}")
    List<Scriptwriter> selectScriptwriter(int movieId);

    @Select("select * from scriptwriter where scriptwriter=#{scriptwriter}")
    List<Scriptwriter> selectByName(String scriptwriter);

    @Insert("insert into scr_movie(movie_id, scriptwriter_id) values (#{movieId},#{scriptwriterId})")
    int insertscr(@Param("movieId") int movieId, @Param("scriptwriterId") int scriptwriterId);

}
