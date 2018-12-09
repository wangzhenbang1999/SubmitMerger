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
@WebServlet("/AdminSetStatus")
public class AdminSetStatus extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        IAdminDao adminDao = DAOFactory.getAdminDao();
        Admin a = adminDao.findById(id);
        if(a.getStatus()==0) {
            a.setStatus(1);
        } else {
            a.setStatus(0);
        }
        adminDao.update(a);
        response.sendRedirect("list.jsp");


    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
