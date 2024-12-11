package com.service.impl;

import com.mapper.DormitoryMapper;
import com.mapper.UserMapper;
import com.pojo.*;
import com.service.UserService;
import com.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import java.util.List;

public class UserServiceImpl implements UserService {

    //创建SalSessionFactory对象
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    @Override
    public PageBean<User> selectByPageAndCondition(int currentPage, int pageSize, User user) {
        SqlSession sqlSession = factory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        int begin = (currentPage - 1) * pageSize;
        int size = pageSize;

        String username = user.getUsername();
        if(username != null && username.length() > 0) {
            user.setUsername("%"+username+"%");
        }

        List<User> rows = mapper.selectByPageAndCondition(begin, size, user);
        int totalCount = mapper.selectTotalCountByCondition(user);

        PageBean<User> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);


        sqlSession.close();

        return pageBean;
    }

    @Override
    public void deleteByIds(int[] ids) {
        SqlSession sqlSession = factory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        mapper.deleteByIds(ids);

        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void deleteById(int id) {
        SqlSession sqlSession = factory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        mapper.deleteById(id);

        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void update(User user) {
        SqlSession sqlSession = factory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        mapper.update(user);

        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void add(User user) {
        SqlSession sqlSession = factory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        mapper.add(user);

        sqlSession.commit();
        sqlSession.close();
    }

    public User login(String username, String password) {

        //创建mapper对象
        SqlSession sqlSession =factory.openSession();
        UserMapper mapper =sqlSession.getMapper(UserMapper.class);

        //调用mapper中的login方法，返回数据库中的匹配项，并将其封装成user类
        User user = mapper.login(username, password);

        sqlSession.close();
        return user;
    }

}
