package com.example.movie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.movie.entity.Director;
import com.example.movie.entity.Performer;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface PerformerMapper extends BaseMapper<Performer> {
    @Select("select performer_id, performer from per_movie natural join performer where movie_id=#{movieId}")
    List<Performer> selectPerformer(int movieId);

    @Select("select * from performer where performer=#{performer}")
    List<Performer> selectByName(String performer);

    @Insert("insert into per_movie(movie_id, performer_id) values (#{movieId},#{performerId})")
    int insertper(@Param("movieId") int movieId, @Param("performerId") int performerId);
}
