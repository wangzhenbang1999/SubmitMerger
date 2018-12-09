package com.wangzhenbang.SubmitMerger.dao.impl;

import com.wangzhenbang.SubmitMerger.dao.ICustomerDao;
import com.wangzhenbang.SubmitMerger.exception.SubmitMergerException;
import com.wangzhenbang.SubmitMerger.po.Customer;
import com.wangzhenbang.SubmitMerger.util.JDBCUtil;
import com.wangzhenbang.SubmitMerger.util.PageInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerJDBCDao implements ICustomerDao {

    @SuppressWarnings("resource")
    @Override
    public void add(Customer customer) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = JDBCUtil.getConnection();
            //		判断用户是否存在
            String sql = "select count(id) from customer where tel=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, customer.getTel());
            rs = ps.executeQuery();
            int count = 0;
            while(rs.next()){
                count = rs.getInt(1);
            }
            if(count > 0) throw new SubmitMergerException("要添加的用户已经存在，不能再添加");

//			添加用户
            sql = "insert into customer (tel, password,nickname,status) values (?,?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, customer.getTel());
            ps.setString(2, customer.getPassword());
            ps.setString(3, customer.getNickname());
            ps.setInt(4, customer.getStatus());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs);
            JDBCUtil.close(ps);
            JDBCUtil.close(con);
        }
    }

    @Override
    public void delete(int id) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = JDBCUtil.getConnection();

            String sql = "delete from customer where id=?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            JDBCUtil.close(ps);
            JDBCUtil.close(con);
        }
    }

    @Override
    public void update(Customer customer) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = JDBCUtil.getConnection();
            String sql = "update customer set password=?,nickname=?,status=? where id=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, customer.getPassword());
            ps.setString(2, customer.getNickname());
            ps.setInt(3, customer.getStatus());
            ps.setInt(4, customer.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(ps);
            JDBCUtil.close(con);
        }
    }

    @Override
    public Customer findById(int id) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Customer a = null;
        try {
            con = JDBCUtil.getConnection();
            String sql = "select id,tel, password,nickname,status from customer where id=?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while(rs.next()){
                a = new Customer();
                a.setId(rs.getInt("id"));
                a.setTel(rs.getString("tel"));
                a.setPassword(rs.getString("password"));
                a.setNickname(rs.getString("nickname"));
                a.setStatus(rs.getInt("status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs);
            JDBCUtil.close(ps);
            JDBCUtil.close(con);
        }
        return a;
    }

    @Override
    public Customer findByTel(String tel) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Customer a = null;
        try {
            con = JDBCUtil.getConnection();
            String sql = "select id,tel, password,nickname,status from customer where tel=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, tel);
            rs = ps.executeQuery();
            while(rs.next()){
                a = new Customer();
                a.setId(rs.getInt("id"));
                a.setTel(rs.getString("tel"));
                a.setPassword(rs.getString("password"));
                a.setNickname(rs.getString("nickname"));
                a.setStatus(rs.getInt("status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs);
            JDBCUtil.close(ps);
            JDBCUtil.close(con);
        }
        return a;
    }

    @Override
    public List<Customer> list() {
        List<Customer> customers = new ArrayList<Customer>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = JDBCUtil.getConnection();
            String sql = "select id, tel, password,nickname,status from customer";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            Customer a = null;
            while(rs.next()){
                a = new Customer();
                a.setId(rs.getInt("id"));
                a.setTel(rs.getString("tel"));
                a.setPassword(rs.getString("password"));
                a.setNickname(rs.getString("nickname"));
                a.setStatus(rs.getInt("status"));
                customers.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs);
            JDBCUtil.close(ps);
            JDBCUtil.close(con);
        }
        return customers;
    }

    @SuppressWarnings("resource")
    @Override
    public PageInfo list(String tel, String nickname, int currentPage, int pageSize) {
        PageInfo pageInfo = new PageInfo();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Customer> customers = new ArrayList<Customer>();
        try {
            con = JDBCUtil.getConnection();
            String sql = "select id,tel,customer.password,nickname,status from customer where 1=1";
            String sqlCount = "select count(id) from customer where 1=1";
            if(tel != null && !"".equals(tel)){
                sql += " and tel like '%"+tel + "%'";
                sqlCount += " and tel like '%"+tel + "%'";
            }
            if(nickname != null && !"".equals(nickname)){
                sql += " and nickname like '%"+nickname + "%'";
                sqlCount += " and nickname like '%"+nickname + "%'";
            }
            sql += " limit ?,?";
            ps = con.prepareStatement(sql);
//			页面记录范围
            if(currentPage > 0 && pageSize > 0){
                ps.setInt(1, (currentPage < 2) ? 0 : (currentPage-1)*pageSize);
                ps.setInt(2, pageSize);
            }
            rs = ps.executeQuery();
            Customer a = null;
            while(rs.next()){
                a = new Customer();
                a.setId(rs.getInt("id"));
                a.setTel(rs.getString("tel"));
                a.setPassword(rs.getString("password"));
                a.setNickname(rs.getString("nickname"));
                a.setStatus(rs.getInt("status"));
                customers.add(a);
            }
//			填充分页信息
            pageInfo.setDatas(customers);

            pageInfo.setCurrentPage(currentPage);
            pageInfo.setPageSize(pageSize);

            ps = con.prepareStatement(sqlCount);
            rs = ps.executeQuery();
            int recordsFiltered = 0;
            while(rs.next()){
                recordsFiltered = rs.getInt(1);
            }
            pageInfo.setRecordsFiltered(recordsFiltered);

            pageInfo.setRecordsTotal(recordsFiltered);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs);
            JDBCUtil.close(ps);
            JDBCUtil.close(con);
        }
        return pageInfo;
    }

    @Override
    public Customer login(String tel, String password) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Customer a = null;
        try {
            con = JDBCUtil.getConnection();
//		判断用户是否存在
            String sql = "select id,tel,password,nickname,status from customer where tel=? and password=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, tel);
            ps.setString(2, password);
            rs = ps.executeQuery();
            while(rs.next()){
                a = new Customer();
                a.setId(rs.getInt("id"));
                a.setTel(rs.getString("tel"));
                a.setPassword(rs.getString("password"));
                a.setNickname(rs.getString("nickname"));
                a.setStatus(rs.getInt("status"));
            }
            if(a == null) throw new SubmitMergerException("用户名或者密码错误");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs);
            JDBCUtil.close(ps);
            JDBCUtil.close(con);
        }
        return a;
    }

}
