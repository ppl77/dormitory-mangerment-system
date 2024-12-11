package com.mapper;

import com.pojo.Room;
import com.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {
    //实现登录功能，要检查数据库中是否有匹配项
    @Select("select * from tb_users where username = #{username} and password = #{password}")
    User login(@Param("username") String username, @Param("password") String password);

    @ResultMap("userResultMap")
    List<User> selectByPageAndCondition(@Param("begin") int begin, @Param("size") int size, @Param("user") User user);

    @ResultMap("userResultMap")
    int selectTotalCountByCondition(User user);

    @ResultMap("userResultMap")
    void deleteByIds(@Param("ids") int[] ids);

    @ResultMap("userResultMap")
    @Delete("delete from tb_users where user_id = #{id}")
    void deleteById(int id);

    @ResultMap("userResultMap")
    @Insert("insert into tb_users (user_id, username, password) values (null,#{username},#{password})")
    void add(User user);

    @ResultMap("userResultMap")
    void update(User user);

}
