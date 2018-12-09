package com.wangzhenbang.SubmitMerger.controller;

import com.wangzhenbang.SubmitMerger.dao.IAdminDao;
import com.wangzhenbang.SubmitMerger.exception.SubmitMergerException;
import com.wangzhenbang.SubmitMerger.po.Admin;

import com.wangzhenbang.SubmitMerger.util.DAOFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/AdminAdd")
public class AdminAdd extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String nickname = request.getParameter("nickname");
        boolean validate = true;
        if(username ==null || "".equals(username)){
            request.setAttribute("usernameError", "用户名不能为空");
            validate = false;
        }else{
            request.setAttribute("username", username);
        }
        if(password ==null || "".equals(password)){
            request.setAttribute("passwordError", "密码不能为空");
            validate = false;
        }else{
            request.setAttribute("password", password);
        }
        if(nickname ==null || "".equals(nickname)){
            request.setAttribute("nicknameError", "昵称不能为空");
            validate = false;
        }else{
            request.setAttribute("nickname", nickname);
        }
        if(!validate) {
            request.getRequestDispatcher("WEB-INF/backstage/admin/add.jsp").forward(request, response);
        }
        Admin admin = new Admin();
        admin.setNickname(nickname);
        admin.setPassword(password);
        admin.setUsername(username);
        admin.setAdminType(0); //0表示普通管理员，1表示超级管理员
        admin.setStatus(0);  //0表示正常，1表示冻结
        IAdminDao adminDao = DAOFactory.getAdminDao();
        try{
            adminDao.add(admin);
            response.sendRedirect("list.jsp");
        } catch(SubmitMergerException e) {
            response.getWriter().print(e.getMessage());
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
