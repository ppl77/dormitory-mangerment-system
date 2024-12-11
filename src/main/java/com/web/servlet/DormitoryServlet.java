package com.web.servlet;

import com.alibaba.fastjson.JSON;
import com.pojo.Dormitory;
import com.pojo.PageBean;
import com.pojo.Student;
import com.service.DormitoryService;
import com.service.impl.DormitoryServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/dormitory/*")
public class DormitoryServlet extends BaseServlet {

    private DormitoryService dormitoryService = new DormitoryServiceImpl();
    public void selectByPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");

        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串


        Dormitory dormitory = JSON.parseObject(params, Dormitory.class);


        PageBean<Dormitory> pageBean = dormitoryService.selectByPageAndCondition(currentPage, pageSize, dormitory);
        //转为json
        String jsonString = JSON.toJSONString(pageBean);

        //写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader br = request.getReader();
        String params = br.readLine();

        Dormitory dormitory = JSON.parseObject(params,Dormitory.class);


        dormitoryService.add(dormitory);

        response.getWriter().write("success");
    }

    public void deleteByIds(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader br = request.getReader();
        String params = br.readLine();

        int[] ids = JSON.parseObject(params,int[].class);

        //System.out.println(params);
        dormitoryService.deleteByIds(ids);

        response.getWriter().write("success");
    }

    public void deleteById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        BufferedReader br = request.getReader();
        String params = br.readLine();

        int id = JSON.parseObject(params,int.class);

        dormitoryService.deleteById(id);

        response.getWriter().write("success");
    }

    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader br = request.getReader();
        String params = br.readLine();

        Dormitory dormitory = JSON.parseObject(params,Dormitory.class);

        dormitoryService.update(dormitory);

        response.getWriter().write("success");
    }
}
