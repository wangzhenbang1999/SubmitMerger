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
        //提示交给前端去做
        if(username ==null || "".equals(username)){
            validate = false;
        }else{
            request.setAttribute("username", username);
        }
        if(password ==null || "".equals(password)){
            validate = false;
        }else{
            request.setAttribute("password", password);
        }
        if(nickname ==null || "".equals(nickname)){
            validate = false;
        }else{
            request.setAttribute("nickname", nickname);
        }
        if(!validate) {
            request.getRequestDispatcher("WEB-INF/backstage/admin/add.jsp").forward(request, response);
        }
        IAdminDao adminDao = DAOFactory.getAdminDao();
        try{
            adminDao.add(username,password,username);
            response.sendRedirect("list.jsp");
        } catch(SubmitMergerException e) {
            response.getWriter().print(e.getMessage());
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
