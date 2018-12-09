package com.wangzhenbang.SubmitMerger.util;

import java.sql.*;
import java.util.Properties;

public class JDBCUtil {

//	public static void main(String[] args) {
//		try {
////			读取配置文件
//			Properties prop = new Properties();
//			prop.load(JDBCUtil.class.getClassLoader().getResourceAsStream("config/jdbc.properties"));
//			String url = prop.getProperty("password");
//			System.out.println(url);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
////		在getConnection中获取的时候，肯定不能每次都读取配置文件，可以把prop设置为单例模式工具类
//	}

    public static Connection getConnection() {
        Properties prop = PropertiesUtil.getJDBCProp();//bug
        Connection con = null;
        try {
            Class.forName(prop.getProperty("driverClassName"));
            con = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("username"), prop.getProperty("password"));
        } catch (SQLException | ClassNotFoundException e) {

            e.printStackTrace();
        }
        return con;

    }

    public static void close(Connection con) {
        try {
            if(con!=null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void close(PreparedStatement ps) {
        try {
            if(ps!=null) ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void close(ResultSet rs) {
        try {
            if(rs!=null) rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
