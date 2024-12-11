package com.service;

import com.pojo.*;

public interface UserService {

    PageBean<User> selectByPageAndCondition(int currentPage, int pageSize, User user);

    void deleteByIds(int[] ids);

    void deleteById(int id);

    void update(User user);

    void add(User user);

    //定义登录方法
    User login(String username, String password);

}
