package com.mapper;

import com.pojo.Room;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface RoomMapper {
    @ResultMap("roomResultMap")
    List<Room> selectByPageAndCondition(@Param("begin") int begin, @Param("size") int size, @Param("room") Room room);

    @ResultMap("roomResultMap")
    int selectTotalCountByCondition(Room room);

    @ResultMap("roomResultMap")
    @Insert("insert into tb_rooms values (null,#{dormitoryId},#{roomNumber},#{capacity},#{price},#{dormitoryName})")
    void add(Room room);

    @ResultMap("roomResultMap")
    void deleteByIds(@Param("ids") int[] ids);

    @ResultMap("roomResultMap")
    @Delete("delete from tb_rooms where room_id = #{roomId}")
    void deleteById(int id);

    @ResultMap("roomResultMap")
    void update(Room room);

    @Select("select * from tb_rooms")
    @ResultMap("roomResultMap")
    List<Room> selectAll();

}
