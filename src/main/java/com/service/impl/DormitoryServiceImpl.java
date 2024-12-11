package com.service.impl;

import com.mapper.DormitoryMapper;
import com.pojo.Dormitory;
import com.pojo.PageBean;
import com.service.DormitoryService;
import com.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.time.LocalDate;
import java.util.List;

public class DormitoryServiceImpl implements DormitoryService {

    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    public PageBean<Dormitory> selectByPageAndCondition(int currentPage, int pageSize, Dormitory dormitory) {
        SqlSession sqlSession = factory.openSession();
        DormitoryMapper mapper = sqlSession.getMapper(DormitoryMapper.class);

        int begin = (currentPage - 1) * pageSize;
        int size = pageSize;


        String dormitoryName = dormitory.getDormitoryName();
        if(dormitoryName != null && dormitoryName.length() > 0) {
            dormitory.setDormitoryName("%"+dormitoryName+"%");
        }

        String startDate = dormitory.getStartDate();
        if(startDate != null && startDate.length() > 0) {
            dormitory.setStartDate("%"+startDate+"%");
        }
        Integer floors = dormitory.getFloors();
        if(floors != null) {
            dormitory.setFloors(floors);
        }

        Integer rooms = dormitory.getRooms();
        if(rooms != null) {
            dormitory.setRooms(rooms);
        }

        List<Dormitory> rows = mapper.selectByPageAndCondition(begin, size, dormitory);
        int totalCount = mapper.selectTotalCountByCondition(dormitory);

        PageBean<Dormitory> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);

        sqlSession.close();

        return pageBean;
    }

    @Override
    public void add(Dormitory dormitory) {
        SqlSession sqlSession = factory.openSession();
        DormitoryMapper mapper = sqlSession.getMapper(DormitoryMapper.class);

        mapper.add(dormitory);

        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void deleteByIds(int[] ids) {
        SqlSession sqlSession = factory.openSession();
        DormitoryMapper mapper = sqlSession.getMapper(DormitoryMapper.class);

        mapper.deleteByIds(ids);

        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void deleteById(int id) {
        SqlSession sqlSession = factory.openSession();
        DormitoryMapper mapper = sqlSession.getMapper(DormitoryMapper.class);

        mapper.deleteById(id);

        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void update(Dormitory dormitory) {
        SqlSession sqlSession = factory.openSession();
        DormitoryMapper mapper = sqlSession.getMapper(DormitoryMapper.class);

        mapper.update(dormitory);

        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public List<Dormitory> selectAll() {
        SqlSession sqlSession = factory.openSession();
        DormitoryMapper mapper = sqlSession.getMapper(DormitoryMapper.class);

        List<Dormitory> dormitories = mapper.selectAll();
        sqlSession.close();
        return dormitories;
    }
}
