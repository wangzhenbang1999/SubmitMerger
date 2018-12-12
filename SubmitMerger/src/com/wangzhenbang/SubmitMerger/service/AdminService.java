package com.wangzhenbang.SubmitMerger.service;

import com.wangzhenbang.SubmitMerger.po.Admin;
import com.wangzhenbang.SubmitMerger.util.PageInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface AdminService {
    public boolean login(HttpServletRequest request, String username,String password);
    public List<Admin> list();
    public PageInfo list(String username, String nickname, String currentPage, int pageSize);
}
