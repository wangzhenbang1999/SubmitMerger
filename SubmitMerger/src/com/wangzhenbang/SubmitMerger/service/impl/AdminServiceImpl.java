package com.wangzhenbang.SubmitMerger.service.impl;

import com.wangzhenbang.SubmitMerger.dao.IAdminDao;
import com.wangzhenbang.SubmitMerger.po.Admin;
import com.wangzhenbang.SubmitMerger.service.AdminService;
import com.wangzhenbang.SubmitMerger.util.DAOFactory;
import com.wangzhenbang.SubmitMerger.util.PageInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AdminServiceImpl implements AdminService {
    private IAdminDao adminDao = null;

    public AdminServiceImpl() {
        adminDao = DAOFactory.getAdminDao();
    }

    public boolean login(HttpServletRequest request, String username, String password) {
        try {
            Admin admin = adminDao.login(username, password);

            if(admin.getStatus()==1){
                request.setAttribute("msg", "该用户被冻结，请联系管理员");
                return false;
            }else{
                request.getSession().setAttribute("admin", admin);
                return true;
            }
        } catch (Exception e) {

            request.setAttribute("msg", e.getMessage());
            return false;
        }

    }



    public List<Admin> list(String username , String nickname){
        if(username == null) username = "";
        if(nickname == null) nickname = "";
        List<Admin> list = adminDao.list(username, nickname);
        return list;
    }

    @Override
    public PageInfo list(String username, String nickname, String currentPage, int pageSize) {
        if(username == null) username = "";
        if(nickname == null) nickname = "";
        int page = 1;
        if(currentPage != null && !currentPage.equals("")) {
            page = Integer.parseInt(currentPage);
        }
        return adminDao.list(username, nickname, page, pageSize);

    }
}
