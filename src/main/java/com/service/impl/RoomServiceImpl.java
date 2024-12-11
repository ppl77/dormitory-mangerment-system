package com.service.impl;

import com.mapper.RoomMapper;
import com.pojo.PageBean;
import com.pojo.Room;
import com.service.RoomService;
import com.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class RoomServiceImpl implements RoomService {

    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();
    @Override
    public PageBean<Room> selectByPageAndCondition(int currentPage, int pageSize, Room room) {
        SqlSession sqlSession = factory.openSession();
        RoomMapper mapper = sqlSession.getMapper(RoomMapper.class);

        int begin = (currentPage - 1) * pageSize;
        int size = pageSize;

        String dormitoryName = room.getDormitoryName();
        if(dormitoryName != null && dormitoryName.length() > 0) {
            room.setDormitoryName("%"+dormitoryName+"%");
        }

        String roomNumber = room.getRoomNumber();
        if(roomNumber != null && roomNumber.length() > 0) {
            room.setRoomNumber("%"+roomNumber+"%");
        }

        Integer capacity = room.getCapacity();
        if(capacity != null) {
            room.setCapacity(capacity);
        }
        Integer dormitoryId = room.getDormitoryId();
        if(dormitoryId != null) {
            room.setDormitoryId(dormitoryId);
        }

        List<Room> rows = mapper.selectByPageAndCondition(begin, size, room);
        int totalCount = mapper.selectTotalCountByCondition(room);

        PageBean<Room> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);


        sqlSession.close();

        return pageBean;
    }

    @Override
    public void add(Room room) {
        SqlSession sqlSession = factory.openSession();
        RoomMapper mapper = sqlSession.getMapper(RoomMapper.class);

        mapper.add(room);

        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void deleteByIds(int[] ids) {
        SqlSession sqlSession = factory.openSession();
        RoomMapper mapper = sqlSession.getMapper(RoomMapper.class);

        mapper.deleteByIds(ids);

        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void deleteById(int id) {
        SqlSession sqlSession = factory.openSession();
        RoomMapper mapper = sqlSession.getMapper(RoomMapper.class);

        mapper.deleteById(id);

        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void update(Room room) {
        SqlSession sqlSession = factory.openSession();
        RoomMapper mapper = sqlSession.getMapper(RoomMapper.class);

        mapper.update(room);

        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public List<Room> selectAll() {
        SqlSession sqlSession = factory.openSession();
        RoomMapper mapper = sqlSession.getMapper(RoomMapper.class);

        List<Room> rooms = mapper.selectAll();

        sqlSession.close();
        return rooms;
    }
}
