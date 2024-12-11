package com.web.servlet;

import com.alibaba.fastjson.JSON;
import com.pojo.Dormitory;
import com.pojo.PageBean;
import com.pojo.Room;
import com.service.RoomService;
import com.service.RoomService;
import com.service.impl.RoomServiceImpl;
import com.service.impl.RoomServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/room/*")
public class RoomServlet extends BaseServlet {

    private RoomService roomService = new RoomServiceImpl();
    public void selectByPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");

        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串


        Room room = JSON.parseObject(params, Room.class);


        PageBean<Room> pageBean = roomService.selectByPageAndCondition(currentPage, pageSize, room);
        //转为json
        String jsonString = JSON.toJSONString(pageBean);


        //写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader br = request.getReader();
        String params = br.readLine();

        Room room = JSON.parseObject(params,Room.class);


        roomService.add(room);

        response.getWriter().write("success");
    }

    public void deleteByIds(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader br = request.getReader();
        String params = br.readLine();

        int[] ids = JSON.parseObject(params,int[].class);
        roomService.deleteByIds(ids);

        response.getWriter().write("success");
    }

    public void deleteById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        BufferedReader br = request.getReader();
        String params = br.readLine();

        int id = JSON.parseObject(params,int.class);

        roomService.deleteById(id);

        response.getWriter().write("success");
    }

    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader br = request.getReader();
        String params = br.readLine();

        Room room = JSON.parseObject(params,Room.class);

        roomService.update(room);

        response.getWriter().write("success");
    }
}
