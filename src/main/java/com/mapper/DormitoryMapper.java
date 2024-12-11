package com.mapper;

import com.pojo.Dormitory;
import com.pojo.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface DormitoryMapper {
    @ResultMap("dormitoryResultMap")
    List<Dormitory> selectByPageAndCondition(@Param("begin") int begin, @Param("size") int size, @Param("dormitory") Dormitory dormitory);

    @ResultMap("dormitoryResultMap")
    int selectTotalCountByCondition(Dormitory dormitory);

    @ResultMap("dormitoryResultMap")
    @Insert("insert into tb_dormitories values (null,#{dormitoryName},#{floors},#{rooms},#{startDate})")
    void add(Dormitory dormitory);

    @ResultMap("dormitoryResultMap")
    void deleteByIds(@Param("ids") int[] ids);

    @Delete("delete from tb_dormitories where dormitory_id = #{dormitoryId}")
    @ResultMap("dormitoryResultMap")
    void deleteById(int id);

    @ResultMap("dormitoryResultMap")
    void update(Dormitory dormitory);

    @Select("select * from tb_dormitories")
    @ResultMap("dormitoryResultMap")
    List<Dormitory> selectAll();
}
