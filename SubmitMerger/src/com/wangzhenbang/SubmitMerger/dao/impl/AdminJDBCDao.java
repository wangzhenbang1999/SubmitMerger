package com.wangzhenbang.SubmitMerger.dao.impl;

import com.wangzhenbang.SubmitMerger.dao.IAdminDao;
import com.wangzhenbang.SubmitMerger.exception.SubmitMergerException;
import com.wangzhenbang.SubmitMerger.po.Admin;
import com.wangzhenbang.SubmitMerger.util.JDBCUtil;
import com.wangzhenbang.SubmitMerger.util.PageInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminJDBCDao implements IAdminDao {

    @SuppressWarnings("resource")
    @Override
    public void add(Admin admin) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = JDBCUtil.getConnection();
            //判断用户是否存在
            String sql = "select count(id) from admin where username=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, admin.getUsername());
            rs = ps.executeQuery();
            int count = 0;
            while(rs.next()) {
                count = rs.getInt(1);
            }
            if(count>0) throw new SubmitMergerException("要添加的管理员用户已经存在，不能添加!");
            //添加用户
            sql = "insert into admin (username,password,nickname,adminType,status) value (?,?,?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, admin.getUsername());
            ps.setString(2, admin.getPassword());
            ps.setString(3, admin.getNickname());
            ps.setInt(4, admin.getAdminType());
            ps.setInt(5, admin.getStatus());
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
            String sql = "delete from admin where id=?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(ps);
            JDBCUtil.close(con);
        }
    }

    @Override
    public void update(Admin admin) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = JDBCUtil.getConnection();
            String sql = "update admin set password=?,nickname=?,adminType=?,status=? where id=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, admin.getPassword());
            ps.setString(2, admin.getNickname());
            ps.setInt(3, admin.getAdminType());
            ps.setInt(4, admin.getStatus());
            ps.setInt(5, admin.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Admin> list(String username, String nickname) {
        List<Admin> admins = new ArrayList<Admin>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = JDBCUtil.getConnection();
            String sql = "select id,username,password,nickname,adminType,status from admin where 1=1";
            if(username != null && !username.equals("")) {
                sql += " and username like '%"+username+"%'";
            }
            if(nickname != null && !nickname.equals("")) {
                sql += " and nickname like '%"+nickname+"%'";
            }
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            Admin a = null;
            while(rs.next()) {
                a = new Admin();
                a.setId(rs.getInt("id"));
                a.setUsername(rs.getString("username"));
                a.setPassword(rs.getString("password"));
                a.setNickname(rs.getString("nickname"));
                a.setAdminType(rs.getInt("adminType"));
                a.setStatus(rs.getInt("status"));
                admins.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs);
            JDBCUtil.close(ps);
            JDBCUtil.close(con);
        }
        return admins;
    }

    @SuppressWarnings("resource")
    @Override
    public PageInfo list(String username, String nickname, int currentPage, int pageSize) {
        PageInfo pageInfo = new PageInfo();

        List<Admin> admins = new ArrayList<Admin>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = JDBCUtil.getConnection();
            String sql = "select id,username,password,nickname,adminType,status from admin where 1=1";
            String sqlCount = "select count(*) from admin where 1=1";
            if(username != null && !username.equals("")) {
                sql += " and username like '%"+username+"%'";
                sqlCount += " and username like '%"+username+"%'";
            }
            if(nickname != null && !nickname.equals("")) {
                sql += " and nickname like '%"+nickname+"%'";
                sqlCount += " and nickname like '%"+nickname+"%'";
            }
            sql+=" limit ?,?";
            ps = con.prepareStatement(sql);
            //记录范围
            if ((pageSize > 0) && (currentPage > 0)) {
                ps.setInt(1, (currentPage < 2) ? 0 : (currentPage - 1) * pageSize);
                ps.setInt(2, pageSize);
            }
            rs = ps.executeQuery();
            Admin a = null;
            while(rs.next()) {
                a = new Admin();
                a.setId(rs.getInt("id"));
                a.setUsername(rs.getString("username"));
                a.setPassword(rs.getString("password"));
                a.setNickname(rs.getString("nickname"));
                a.setAdminType(rs.getInt("adminType"));
                a.setStatus(rs.getInt("status"));
                admins.add(a);
            }
//			填充分页数据
            pageInfo.setDatas(admins);

            ps = con.prepareStatement(sqlCount);
            rs = ps.executeQuery();
            int totalRecord = 0;
            while(rs.next()) {
                totalRecord = rs.getInt(1);
            }
            pageInfo.setCurrentPage(currentPage);
            pageInfo.setPageSize(pageSize);
            pageInfo.setRecordsFiltered(totalRecord);
//			很多时候没筛选的总记录数可以不用加，当然需要的话可以再查询一遍加上去，也就会有  搜索到12条记录/总共32条记录
//			很多时候就是 获得搜索到多少条记录即可，
            pageInfo.setRecordsTotal(totalRecord);
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
    public Admin findByName(String username) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Admin a = null;
        try {
            con = JDBCUtil.getConnection();
            String sql = "select id,username,password,nickname,adminType,status from admin where username=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            while(rs.next()) {
                a = new Admin();
                a.setId(rs.getInt("id"));
                a.setNickname(rs.getString("nickname"));
                a.setPassword(rs.getString("password"));
                a.setUsername(rs.getString("username"));
                a.setAdminType(rs.getInt("adminType"));
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
    public Admin login(String username, String password) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Admin a = null;
        try {
            con = JDBCUtil.getConnection();//异常
            String sql = "select id,username,password,nickname,adminType,status from admin where username=? and password=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            while(rs.next()) {
                a = new Admin();
                a.setId(rs.getInt("id"));
                a.setUsername(rs.getString("username"));
                a.setPassword(rs.getString("password"));
                a.setNickname(rs.getString("nickname"));
                a.setAdminType(rs.getInt("adminType"));
                a.setStatus(rs.getInt("status"));
            }
            if(a==null) throw new SubmitMergerException("用户名或者密码不正确");
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
    public Admin findById(int id) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Admin a = null;
        try {
            con = JDBCUtil.getConnection();
            String sql = "select id,username,password,nickname,adminType,status from admin where id=?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while(rs.next()) {
                a = new Admin();
                a.setId(rs.getInt("id"));
                a.setNickname(rs.getString("nickname"));
                a.setPassword(rs.getString("password"));
                a.setUsername(rs.getString("username"));
                a.setAdminType(rs.getInt("adminType"));
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

}
