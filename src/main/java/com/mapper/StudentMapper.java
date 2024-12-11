package com.mapper;

import com.pojo.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface StudentMapper {
    @ResultMap("studentResultMap")
    List<Student> selectByPageAndCondition(@Param("begin") int begin, @Param("size") int size, @Param("student") Student student);

    @ResultMap("studentResultMap")
    int selectTotalCountByCondition(Student student);

    @ResultMap("studentResultMap")
    @Insert("insert into tb_students values (null,#{name},#{gender},#{ethnicity},#{major},#{group},#{contact},#{dormitoryId},#{roomId},#{dormitoryName},#{roomNumber})")
    void add(Student student);

    @ResultMap("studentResultMap")
    void deleteByIds(@Param("ids") int[] ids);

    @ResultMap("studentResultMap")
    @Delete("delete from tb_students where student_id = #{studentId}")
    void deleteById(int id);

    @ResultMap("studentResultMap")
    void update(Student student);

    @Select("select * from tb_students")
    @ResultMap("studentResultMap")
    List<Student> selectAll();
}
