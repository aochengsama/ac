package com.hohai.aocheng.dao;

import com.hohai.aocheng.pojo.Data_op;
import com.hohai.aocheng.pojo.Plan;
import com.hohai.aocheng.utils.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Data_opDap implements Data_opDaoimpl{
    @Override
    public List<Data_op> getdatabyiddate(Date date1, Date date2) throws SQLException {
        // TODO Auto-generated method stub
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy/MM/dd HH");
        //获取连接
        Connection con = jdbc.con();
        //查询语句编译
        String sql = "select * from nr_optimized_data2 where time between ? and  ? order by time";
        PreparedStatement per = con.prepareStatement(sql);

        per.setString(1,simpleDateFormat.format(date1));
        per.setString(2, simpleDateFormat.format(date2));
        //executeQuery()方法查询到数据库的相应结果存放到ResultSet
        ResultSet rest = per.executeQuery();
        //建立list集合存放输出查询结果
        List<Data_op> data_ops = new ArrayList<Data_op>();
        //rest.next()方法将指针下移直到没下一行返回FALSE。
        while (rest.next()) {
            //将查询结果依次储存
            int id1 = rest.getInt("id");
            String time = rest.getString("time");
            Double power = rest.getDouble("power");
            Double qishui = rest.getDouble("qishui");
            Double ruku = rest.getDouble("ruku");
            Double chuku = rest.getDouble("chuku");
            Double sjpower = rest.getDouble("sjpower");
            Double whead = rest.getDouble("whead");

            //建立一个新对象放置结果
            Data_op plan1 = new Data_op(id1, time, power,qishui, ruku, chuku,sjpower,whead);
            //将结果增加到list集合中
            data_ops.add(plan1);
        }

        return data_ops;

    }
}
