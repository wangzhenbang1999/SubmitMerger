package com.wangzhenbang.SubmitMerger.controller;

import com.wangzhenbang.SubmitMerger.dao.IAdminDao;
import com.wangzhenbang.SubmitMerger.exception.SubmitMergerException;
import com.wangzhenbang.SubmitMerger.util.DAOFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/AdminDelete")
public class AdminDelete extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try{
            int id = Integer.parseInt(request.getParameter("id"));
            IAdminDao adminDao = DAOFactory.getAdminDao();
            adminDao.delete(id);
            response.sendRedirect("list.jsp");
        } catch(SubmitMergerException ignored) {
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
