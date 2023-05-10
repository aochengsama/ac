package com.hohai.aocheng.dao;

import com.hohai.aocheng.pojo.Data2;
import com.hohai.aocheng.pojo.Fig;
import com.hohai.aocheng.utils.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class FigDao implements FigDaoimpl{
    @Override
    public String getfigbyid(String id) throws SQLException {
        // TODO Auto-generated method stub
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy/MM/dd HH");
        //获取连接
        Connection con = jdbc.con();
        //查询语句编译
        String sql = "select figs from nrfigs where id=?";
        PreparedStatement per = con.prepareStatement(sql);
        per.setString(1,id);
        //executeQuery()方法查询到数据库的相应结果存放到ResultSet
        ResultSet rest = per.executeQuery();
        //rest.next()方法将指针下移直到没下一行返回FALSE。
        String result="";
        while (rest.next()) {
            //将查询结果依次储存

            result = rest.getString("figs");


        }

        return result;
    }
}
