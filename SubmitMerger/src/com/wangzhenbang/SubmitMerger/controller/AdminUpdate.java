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
@WebServlet("/AdminUpdate")
public class AdminUpdate extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        int id = Integer.parseInt(request.getParameter("id"));
        String password = request.getParameter("password");
        String nickname = request.getParameter("nickname");
        boolean validate = true;
        if(password ==null || "".equals(password)){
            //提示交给前端
            validate = false;
        }
        if(nickname ==null || "".equals(nickname)){
            validate = false;
        }
        if(!validate) {

            request.getRequestDispatcher("WEB-INF/backstage/admin/update.jsp").forward(request, response);

        }
        IAdminDao adminDao = DAOFactory.getAdminDao();
        Admin a = adminDao.findById(id);
        a.setNickname(nickname);
        a.setPassword(password);
        try{
            adminDao.update(a);
            response.sendRedirect("list.jsp");
        } catch(SubmitMergerException ignored) {

        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
