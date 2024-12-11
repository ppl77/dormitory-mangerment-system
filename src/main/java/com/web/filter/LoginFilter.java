//package com.web.filter;
//
//import javax.servlet.*;
//import javax.servlet.annotation.*;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//import java.io.PrintWriter;
//
//@WebFilter("/*")
//public class LoginFilter implements Filter {
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
//
//        HttpServletRequest req = (HttpServletRequest) request;
//        HttpServletResponse resp = (HttpServletResponse) response;
//        HttpSession session = req.getSession();
//        Object user = session.getAttribute("user");
//
//        String[] urls = {"/index.html", "/images/", "/js/", "/element-ui/", "/loginServlet"};
//        String requestURI = req.getRequestURI();
//
//        // 判断是否需要拦截
//        for (String url : urls) {
//            if (requestURI.contains(url)) {
//                chain.doFilter(request, response); // 放行
//                return;
//            }
//        }
//        // 用户已登录，放行
//        if (user != null) {
//            System.out.println("用户已登录，放行: " + requestURI);
//            chain.doFilter(request, response);
//        } else {
//            System.out.println("用户未登录，重定向到登录页: /dams-war/index.html");
//            if (!resp.isCommitted()) {
//                resp.sendRedirect("/dams-war/index.html");
//            }
//        }
//
//
//    }
//
//    public void init(FilterConfig config) throws ServletException {
//    }
//
//    public void destroy() {
//    }
//}
