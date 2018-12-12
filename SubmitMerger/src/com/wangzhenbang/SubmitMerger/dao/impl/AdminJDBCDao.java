package com.wangzhenbang.SubmitMerger.dao.impl;

import com.wangzhenbang.SubmitMerger.dao.IAdminDao;
import com.wangzhenbang.SubmitMerger.exception.SubmitMergerException;
import com.wangzhenbang.SubmitMerger.po.Admin;
import com.wangzhenbang.SubmitMerger.util.C3P0Utils;
import com.wangzhenbang.SubmitMerger.util.JDBCUtil;
import com.wangzhenbang.SubmitMerger.util.PageInfo;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminJDBCDao implements IAdminDao {

    @SuppressWarnings("resource")
    @Override
    public void add(String username,String password,String nickname)  {
        try {
            QueryRunner runner = new QueryRunner(C3P0Utils.getDataSource());
            runner.update("insert into admin (username,password,nickname,adminType,status) value (?,?,?,?,?)",username,password,nickname,0,0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void delete(int id) {
        try {
            QueryRunner runner = new QueryRunner(C3P0Utils.getDataSource());
            runner.update("delete from admin where id=?",id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(String password,String nickname,int id) {
        try {
            QueryRunner runner = new QueryRunner(C3P0Utils.getDataSource());
            System.out.println(3+password+nickname);
            runner.update("update admin set password=?,nickname=? where id=?",nickname,password,id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateStatus(String s, int id) {
        try {
            QueryRunner runner = new QueryRunner(C3P0Utils.getDataSource());
            runner.update("update admin set status=? where id=?",s,id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Admin> list() {
        QueryRunner runner = new QueryRunner(C3P0Utils.getDataSource());
        // 2.执行添加
        List<Admin> admins = null;
        try {
            admins = runner.query("select * from admin", new BeanListHandler<Admin>(Admin.class));
        } catch (SQLException e) {
            e.printStackTrace();
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
        QueryRunner runner = new QueryRunner(C3P0Utils.getDataSource());
        Admin admin = null;
        try {
            admin = runner.query("select id,username,password,nickname,adminType,status from admin where username=?", new BeanHandler<Admin>(Admin.class), username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admin;
    }

    @Override
    public Admin login(String username, String password) {
        QueryRunner runner = new QueryRunner(C3P0Utils.getDataSource());
        Admin admin = null;
        try {
            admin = runner.query("select id,username,password,nickname,adminType,status from admin where username=? and password=?", new BeanHandler<Admin>(Admin.class), username,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admin;
    }

    @Override
    public Admin findById(int id) {
        QueryRunner runner = new QueryRunner(C3P0Utils.getDataSource());
        Admin admin = null;
        try {
            admin = runner.query("select id,username,password,nickname,adminType,status from admin where id=?", new BeanHandler<Admin>(Admin.class), id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admin;
    }

}
