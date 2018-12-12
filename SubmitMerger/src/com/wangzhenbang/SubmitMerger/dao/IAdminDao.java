package com.wangzhenbang.SubmitMerger.dao;

import com.wangzhenbang.SubmitMerger.po.Admin;
import com.wangzhenbang.SubmitMerger.util.PageInfo;

import java.util.List;


public interface IAdminDao {

    /**
     * 根据ID获得Admin
     * @param id
     * @return admin
     */
    public Admin findById(int id);

    // 根据用户名获得Admin
    public Admin findByName(String username);

    public void add(String username,String password,String nickname);

    public void delete(int id);

    public void update(String password,String nickname,int id);

    public void updateStatus(String s,int id);

    public List<Admin> list();

    public PageInfo list(String username, String nickname, int currentPage, int pageSize);

    public Admin login(String username,String password);
}
