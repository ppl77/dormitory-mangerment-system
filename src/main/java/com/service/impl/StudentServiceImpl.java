package com.service.impl;

import com.mapper.RoomMapper;
import com.mapper.StudentMapper;
import com.pojo.PageBean;
import com.pojo.Room;
import com.pojo.Student;
import com.service.StudentService;
import com.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class StudentServiceImpl implements StudentService {
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    @Override
    public PageBean<Student> selectByPageAndCondition(int currentPage, int pageSize, Student student) {
        SqlSession sqlSession = factory.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);

        int begin = (currentPage - 1) * pageSize;
        int size = pageSize;

        String name = student.getName();
        if(name != null && name.length() > 0) {
            student.setName("%"+name+"%");
        }

        String ethnicity = student.getEthnicity();
        if(ethnicity != null && ethnicity.length() > 0) {
            student.setEthnicity("%"+ethnicity+"%");
        }

        String major = student.getMajor();
        if(major != null && major.length() > 0) {
            student.setMajor("%"+major+"%");
        }

        String group = student.getGroup();
        if(group != null && group.length() > 0) {
            student.setGroup("%"+group+"%");
        }

        String gender = student.getGender();
        if(gender != null &&  gender.length() > 0) {
            student.setGender(gender);
        }
        String dormitoryName = student.getDormitoryName();
        if(dormitoryName != null && dormitoryName.length() > 0) {
            student.setDormitoryName("%"+dormitoryName+"%");
        }
        String roomNumber = student.getRoomNumber();
        if(roomNumber != null && roomNumber.length() > 0) {
            student.setRoomNumber("%"+roomNumber+"%");
        }
        List<Student> rows = mapper.selectByPageAndCondition(begin, size, student);
        int totalCount = mapper.selectTotalCountByCondition(student);

        PageBean<Student> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);


        sqlSession.close();

        return pageBean;
    }

    @Override
    public void add(Student student) {
        SqlSession sqlSession = factory.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);

        mapper.add(student);

        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void deleteByIds(int[] ids) {
        SqlSession sqlSession = factory.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);

        mapper.deleteByIds(ids);

        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void deleteById(int id) {
        SqlSession sqlSession = factory.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);

        mapper.deleteById(id);

        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void update(Student student) {
        SqlSession sqlSession = factory.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);

        mapper.update(student);

        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public List<Student> selectAll() {
        SqlSession sqlSession = factory.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);

        List<Student> students = mapper.selectAll();

        sqlSession.close();

        return students;


    }
}
