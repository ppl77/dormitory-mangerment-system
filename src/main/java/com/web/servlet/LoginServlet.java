package com.web.servlet;

import com.alibaba.fastjson2.JSON;
import com.pojo.User;
import com.service.UserService;
import com.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {

    //创建UserService对象
    private UserService service = new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /*
        * 因为从request中得到的为json类型，所以我们首先需要用BufferedReader读取，
        * 然后将其转化为字符串类型，为了调用service中的方法，
        * 我们需要将字符串封装成User类
        * */
        BufferedReader br = request.getReader();
        String params = br.readLine();

        System.out.println(params);
        User u = JSON.parseObject(params,User.class);

        String username = u.getUsername();
        String password = u.getPassword();

        //调用service中的login方法，若满足要求则把返回值封装成一个User类
        User user = service.login(username, password);

        //告诉前端数据返回类型
        response.setContentType("application/json;charset=utf-8");

        PrintWriter out = response.getWriter();
        if(user != null) {
            //登录成功
            out.write("{\"success\": true, \"message\": \"登录成功\"}");

            HttpSession session = request.getSession();
            session.setAttribute("user", user);
        } else {
            //登录失败
            out.write("{\"success\": false, \"message\": \"用户名或密码错误\"}");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
