package com.wangzhenbang.SubmitMerger.controller;

import com.wangzhenbang.SubmitMerger.exception.SubmitMergerException;
import com.wangzhenbang.SubmitMerger.service.AdminService;
import com.wangzhenbang.SubmitMerger.service.impl.AdminServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.synth.SynthEditorPaneUI;
import java.io.IOException;

@WebServlet("/AdminLogin")
public class AdminLogin extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AdminService as = new AdminServiceImpl();
        request.setCharacterEncoding("UTF-8");
        try{
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            boolean result = as.login(request,username,password);


            if(result) {
                request.getSession().setAttribute("isLogin","1");
                request.getRequestDispatcher("list.jsp").forward(request, response);
            }else {
                request.getRequestDispatcher("WEB-INF/backstage/error.jsp").forward(request, response);
            }

        } catch(SubmitMergerException ignored) {

        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
