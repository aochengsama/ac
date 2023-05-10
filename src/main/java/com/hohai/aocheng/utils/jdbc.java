package com.hohai.aocheng.utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class jdbc {
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");//驱动加载
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static Connection con() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/shuidianzhan?characterEncoding=utf-8";
        Connection con = DriverManager.getConnection(url, "root", "123456");//与数据建立连接
        return con;

    }

    public static void main(String[] args) {
        try {
            Connection con = jdbc.con();
            System.out.println(con);//测试链接是否成功
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}