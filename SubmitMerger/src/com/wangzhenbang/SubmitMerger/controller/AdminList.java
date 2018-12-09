package com.wangzhenbang.SubmitMerger.controller;

import com.wangzhenbang.SubmitMerger.service.AdminService;
import com.wangzhenbang.SubmitMerger.service.impl.AdminServiceImpl;
import com.wangzhenbang.SubmitMerger.util.PageInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/list.jsp")
public class AdminList extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private AdminService as;

    @Override
    public void init() throws ServletException {
        as = new AdminServiceImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //模糊查找的条件
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String nickname = request.getParameter("nickname");
        String page = request.getParameter("page");


        //从数据库中拿首页列表信息
        PageInfo pageInfo = as.list(username, nickname, page, 10);
        request.setAttribute("pageInfo", pageInfo);

        request.getRequestDispatcher("WEB-INF/backstage/admin/list.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
