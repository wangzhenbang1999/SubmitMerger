package com.wangzhenbang.SubmitMerger.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class C3P0Utils {
    // 加载名字为"test"的配置文件
    private static ComboPooledDataSource dataSource = new ComboPooledDataSource("test");

    // 得到数据源get方法，给dbUtils的QueryRunner使用的
    public static ComboPooledDataSource getDataSource() {
        return dataSource;
    }

    // 从ConnectionPool中得到Connection
    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    // 释放资源的方法
    public static void release(ResultSet rs, Statement stmt,Connection conn) {
        if(rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            rs = null;
        }
        if(stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            stmt = null;
        }
        if(conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            conn = null;
        }
    }
}