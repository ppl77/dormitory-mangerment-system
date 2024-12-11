package com.web.servlet;

import com.alibaba.fastjson.JSON;
import com.pojo.PageBean;
import com.pojo.Room;
import com.pojo.Student;
import com.service.StudentService;
import com.service.impl.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/student/*")
public class StudentServlet extends BaseServlet {

    private StudentService StudentService = new StudentServiceImpl();
    public void selectByPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");

        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串


        Student student = JSON.parseObject(params, Student.class);


        PageBean<Student> pageBean = StudentService.selectByPageAndCondition(currentPage, pageSize, student);
        //转为json
        String jsonString = JSON.toJSONString(pageBean);
        
        //写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader br = request.getReader();
        String params = br.readLine();

        Student student = JSON.parseObject(params,Student.class);


        StudentService.add(student);

        response.getWriter().write("success");
    }

    public void deleteByIds(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader br = request.getReader();
        String params = br.readLine();

        int[] ids = JSON.parseObject(params,int[].class);
        StudentService.deleteByIds(ids);

        response.getWriter().write("success");
    }

    public void deleteById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        BufferedReader br = request.getReader();
        String params = br.readLine();

        int id = JSON.parseObject(params,int.class);

        StudentService.deleteById(id);

        response.getWriter().write("success");
    }

    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader br = request.getReader();
        String params = br.readLine();

        Student student = JSON.parseObject(params,Student.class);

        StudentService.update(student);

        response.getWriter().write("success");
    }

}
