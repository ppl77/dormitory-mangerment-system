package com.service;

import com.pojo.Dormitory;
import com.pojo.PageBean;
import com.pojo.Room;

import java.util.List;

public interface RoomService {
    PageBean<Room> selectByPageAndCondition(int currentPage, int pageSize, Room room);

    void add(Room room);

    void deleteByIds(int[] ids);

    void deleteById(int id);

    void update(Room room);

    List<Room> selectAll();
}
