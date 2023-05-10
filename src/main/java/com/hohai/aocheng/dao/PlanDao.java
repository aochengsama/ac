package com.hohai.aocheng.dao;

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

public class PlanDao implements PlanDaoimpl{
    @Override
    public List<Plan> getdatabyiddate(int id, Date date1, Date date2) throws SQLException {
        // TODO Auto-generated method stub
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy/MM/dd HH");
        //获取连接
        Connection con = jdbc.con();
        //查询语句编译
        String sql = "select * from plan where id=? and time between ? and  ? order by time";
        PreparedStatement per = con.prepareStatement(sql);
        per.setInt(1,id);
        per.setString(2,simpleDateFormat.format(date1));
        per.setString(3, simpleDateFormat.format(date2));
        //executeQuery()方法查询到数据库的相应结果存放到ResultSet
        ResultSet rest = per.executeQuery();
        //建立list集合存放输出查询结果
        List<Plan> plans = new ArrayList<Plan>();
        //rest.next()方法将指针下移直到没下一行返回FALSE。
        while (rest.next()) {
            //将查询结果依次储存
            int id1 = rest.getInt("id");
            Date time = rest.getDate("time");
            Double power = rest.getDouble("power");
            Double out = rest.getDouble("out");
            Double fin = rest.getDouble("fin");
            Double rin = rest.getDouble("rin");

            //建立一个新对象放置结果
            Plan plan1 = new Plan(id1, time, power,out, fin, rin);
            //将结果增加到list集合中
            plans.add(plan1);
        }

        return plans;

    }
}
