package com.wangzhenbang.SubmitMerger.dao;

import com.wangzhenbang.SubmitMerger.po.Customer;
import com.wangzhenbang.SubmitMerger.util.PageInfo;

import java.util.List;

public interface ICustomerDao {
    /**
     * 添加客户用户
     * @param admin 需要添加的用户对象
     */
    public void add(Customer customer);

    // 删除用户
    public void delete(int id);

    public void update(Customer customer);

    public Customer findById(int id);

    public Customer findByTel(String tel);

    public List<Customer> list();

    public PageInfo list(String tel, String nickname, int currentPage, int pageSize);

    public Customer login(String tel, String password);

}
